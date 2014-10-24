package nimbus_jose_jwt.asym;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jwt.SignedJWT;
import jose4j.asym.Jose4jProvider;
import org.jose4j.lang.JoseException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
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

    JWSVerifier verifier = new RSASSAVerifier(createPublicKey());
    boolean verified = signedJWT.verify(verifier);
    System.out.println("JWS Signature is valid: " + verified);

    System.out.println("JWS payload, subject: " + signedJWT.getJWTClaimsSet().getSubject());
  }

  private RSAPublicKey createPublicKey() {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(ExampleRsaKeyPair.publicKey());
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return (RSAPublicKey) kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }


}
