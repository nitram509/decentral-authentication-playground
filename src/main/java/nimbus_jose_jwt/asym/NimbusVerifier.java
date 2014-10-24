package nimbus_jose_jwt.asym;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public class NimbusVerifier {

  public static void main(String[] args) {
    try {
    String token = new NimbusProvider().create();
      new NimbusVerifier().verify(token);
    } catch (ParseException | JOSEException e) {
      throw new RuntimeException(e);
    }
  }

  public void verify(String token) throws ParseException, JOSEException {
    SignedJWT signedJWT = SignedJWT.parse(token);
    System.out.println("Used KeyID: " + signedJWT.getHeader().getKeyID());

    JWSVerifier verifier = new RSASSAVerifier(ExampleRsaKeyPair.createPublicKey());
    boolean verified = signedJWT.verify(verifier);
    System.out.println("JWS Signature is valid: " + verified);

    System.out.println("JWS payload, subject: " + signedJWT.getJWTClaimsSet().getSubject());
  }

}
