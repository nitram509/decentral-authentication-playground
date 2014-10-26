package jmacaroons.private_public;

import com.github.nitram509.jmacaroons.Macaroon;
import com.github.nitram509.jmacaroons.MacaroonsVerifier;
import com.github.nitram509.jmacaroons.verifier.TimestampCaveatVerifier;
import example_keys.Alice;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;

import static java.lang.Character.digit;

public class MacaroonAuthVerifier {

  public static final Charset ISO_8859_1 = Charset.forName("ISO8859-1");

  public static void main(String[] args) {
    Macaroon macaroon = null;
    try {
      macaroon = new MacaroonAuthProvider().create();
      MacaroonAuthVerifier verifier = new MacaroonAuthVerifier();
      verifier.verify(macaroon);
    } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | InvalidKeyException | BadPaddingException | URISyntaxException e) {
      throw new RuntimeException(e);
    }
  }

  public void verify(Macaroon macaroon) throws URISyntaxException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

    MacaroonsVerifier verifier = new MacaroonsVerifier(macaroon)
        .satisfyExcact("authority = DEV_TOOLS_AVAILABLE")
        .satisfyGeneral(new TimestampCaveatVerifier());

    byte[] publicSessionChallenge = hex2bin(new URI(macaroon.location).getFragment());
    byte[] sessionKey = decryptSessionKey(Alice.getPublicKey(), publicSessionChallenge);
    String secret = new String(sessionKey, ISO_8859_1);

    System.out.println("Valid=" + verifier.isValid(secret));
    verifier.assertIsValid(secret);
  }

  static byte[] decryptSessionKey(PublicKey publicKey, byte[] publicSessionChallenge) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Cipher rsa = Cipher.getInstance("RSA");
    rsa.init(Cipher.DECRYPT_MODE, publicKey);

    rsa.update(publicSessionChallenge);
    return rsa.doFinal();
  }

  public static byte[] hex2bin(String hexString) {
    if (hexString == null) return null;
    int len = hexString.length();
    byte[] bin = new byte[len >> 1];
    for (int i = 0; i < len; i += 2) {
      bin[i >> 1] = (byte) ((digit((int) hexString.charAt(i), 16) << 4) + digit((int) hexString.charAt(i + 1), 16));
    }
    return bin;
  }

}
