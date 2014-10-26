package jmacaroons.asym;

import com.github.nitram509.jmacaroons.Macaroon;
import com.github.nitram509.jmacaroons.MacaroonsVerifier;
import com.github.nitram509.jmacaroons.verifier.TimestampCaveatVerifier;

import javax.crypto.Cipher;
import java.security.interfaces.RSAPrivateKey;

public class MacaroonAuthVerifier {

  public static void main(String[] args) {
    Macaroon macaroon = new MacaroonAuthProvider().create();
    new MacaroonAuthVerifier().verify(macaroon);
  }

  public void verify(Macaroon macaroon) {

    String secret = "geheim";

    MacaroonsVerifier verifier = new MacaroonsVerifier(macaroon)
        .satisfyExcact("authority = DEV_TOOLS_AVAILABLE")
        .satisfyGeneral(new TimestampCaveatVerifier());

    System.out.println("Valid=" + verifier.isValid(secret));
    verifier.assertIsValid(secret);
  }

}
