package jose4j.asym;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class Jose4jVerifier {

  public static void main(String[] args) {
    String token = new Jose4jProvider().create();
    try {
      new Jose4jVerifier().verify(token);
    } catch (JoseException e) {
      e.printStackTrace();
    }
  }

  public void verify(String token) throws JoseException {

    JsonWebSignature jws = new JsonWebSignature();
    jws.setCompactSerialization(token);

    PublicKey publicKey = createPublicKey();
    jws.setKey(publicKey);

    boolean signatureVerified = jws.verifySignature();

    System.out.println("JWS Signature is valid: " + signatureVerified);

    String payload = jws.getPayload();
    System.out.println("JWS payload: " + payload);
  }

  private PublicKey createPublicKey() {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(ExampleRsaKeyPair.publicKey());
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }


}
