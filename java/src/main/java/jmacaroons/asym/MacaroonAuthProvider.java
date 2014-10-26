package jmacaroons.asym;

import com.github.nitram509.jmacaroons.Macaroon;
import com.github.nitram509.jmacaroons.MacaroonsBuilder;
import example_keys.Alice;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;

public class MacaroonAuthProvider {

  public static void main(String[] args) {
    Macaroon macaroon = null;
    try {
      macaroon = new MacaroonAuthProvider().create();
    } catch (NoSuchPaddingException | NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    System.out.println("Details:\n" + macaroon.inspect());
    System.out.println("Token:" + macaroon.serialize());
  }

  public Macaroon create() throws NoSuchPaddingException, NoSuchAlgorithmException {

    RSAPrivateKey privateKey = Alice.createPrivateKey();
    Cipher rsa = Cipher.getInstance("RSA");

    SecureRandom random = SecureRandom.getInstanceStrong();
    random.setSeed(System.currentTimeMillis());
    byte[] tempKey = new byte[rsa.getBlockSize()];
    random.nextBytes(tempKey);


    String location = "http://www.example.org";
    String secret = "geheim";
    String identifier = "this is an example macaroon";
    Macaroon macaroon = new MacaroonsBuilder(location, secret, identifier)
        .add_first_party_caveat("time < 2014-12-01")
        .add_first_party_caveat("authority = DEV_TOOLS_AVAILABLE")
        .getMacaroon();

    return macaroon;
  }

}
