package jmacaroons.private_public;

import com.github.nitram509.jmacaroons.Macaroon;
import com.github.nitram509.jmacaroons.MacaroonsBuilder;
import com.github.nitram509.jmacaroons.util.BinHex;
import example_keys.Alice;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;

public class MacaroonAuthProvider {

  public static final Charset ISO8859_1 = Charset.forName("ISO-8859-1");

  public static void main(String[] args) {
    Macaroon macaroon = null;
    try {
      macaroon = new MacaroonAuthProvider().create();
    } catch (NoSuchPaddingException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Details:\n" + macaroon.inspect());
    System.out.println("Token:" + macaroon.serialize());
  }

  public Macaroon create() throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, IllegalBlockSizeException, InvalidKeyException {

    RSAPrivateKey alicePrivateKey = Alice.getPrivateKey();
    MacaroonKeys macaroonKeys = createMacaroonKeys(alicePrivateKey);

    String location = "http://www.example.org#" + BinHex.bin2hex(macaroonKeys.publicSessionChallenge);
    String secret = new String(macaroonKeys.secretSessionKey, ISO8859_1); // dirty hack, as long as jmacaroons API doesn't provide raw byte keys
    String identifier = "this is an example macaroon";
    Macaroon macaroon = new MacaroonsBuilder(location, secret, identifier)
        .add_first_party_caveat("time < 2014-12-01")
        .add_first_party_caveat("authority = DEV_TOOLS_AVAILABLE")
        .getMacaroon();
    return macaroon;
  }

  static MacaroonKeys createMacaroonKeys(PrivateKey privateKey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
    Cipher rsa = Cipher.getInstance("RSA");
    rsa.init(Cipher.ENCRYPT_MODE, privateKey);

    SecureRandom random = SecureRandom.getInstanceStrong();
    random.setSeed(System.currentTimeMillis());
    byte[] secretSessionKey = new byte[rsa.getBlockSize()];
    random.nextBytes(secretSessionKey);

    rsa.update(secretSessionKey);
    byte[] publicSessionChallenge = rsa.doFinal();

    return new MacaroonKeys(secretSessionKey, publicSessionChallenge);
  }

  static class MacaroonKeys {
    byte[] secretSessionKey;
    byte[] publicSessionChallenge;

    public MacaroonKeys(byte[] secretSessionKey, byte[] publicSessionChallenge) {
      this.secretSessionKey = secretSessionKey;
      this.publicSessionChallenge = publicSessionChallenge;
    }
  }

}
