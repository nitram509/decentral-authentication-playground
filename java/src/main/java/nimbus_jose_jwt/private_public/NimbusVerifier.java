package nimbus_jose_jwt.private_public;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import example_keys.Alice;

import java.security.interfaces.RSAPublicKey;
import java.text.ParseException;

public class NimbusVerifier {

  public static void main(String[] args) {
    try {
      String token = new NimbusProvider().create();
      NimbusVerifier verifier = new NimbusVerifier();
      verifier.verify(token, Alice.getPublicKey());
      System.out.println("-------");
      verifier.verify(tokenFromPhpExampleCode(), RsaPublicKey_from_PHP_counterpart.createPublicKey());
    } catch (ParseException | JOSEException e) {
      throw new RuntimeException(e);
    }
  }

  public void verify(String token, RSAPublicKey publicKey) throws ParseException, JOSEException {
    SignedJWT signedJWT = SignedJWT.parse(token);
    System.out.println("Used KeyID: " + signedJWT.getHeader().getKeyID());

    JWSVerifier verifier = new RSASSAVerifier(publicKey);
    boolean verified = signedJWT.verify(verifier);
    System.out.println("JWS Signature is valid: " + verified);

    System.out.println("JWS payload, subject: " + signedJWT.getJWTClaimsSet().getSubject());
  }

  private static String tokenFromPhpExampleCode() {
    return "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImlkLTA4MTUifQ.eyJzdWIiOiJzb21lb25lQGV4YW1wbGUuY29tIiwiZXhwIjoxNDE0MjMzODg4fQ.WHw6fYPcdJsn2qmBF8OEqLm8zABhyWXY3H9a6CM-hWgvgD7PwxSDBahz-0-OrqTeBF_6afD63PTGLMAEGKKbmZbPxMorRqZ9e4tSqyR9v2rRBlqeSuP0kNUySEeDmPskNyc8oh_bcI-xU5XodMsqYqk-H_38cymDclWTEB9tUYI";
  }

}
