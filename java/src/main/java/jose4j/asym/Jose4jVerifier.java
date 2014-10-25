package jose4j.asym;

import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PublicKey;

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

    PublicKey publicKey = ExampleRsaKeyPair.createPublicKey();
    jws.setKey(publicKey);

    boolean signatureVerified = jws.verifySignature();

    System.out.println("JWS Signature is valid: " + signatureVerified);

    String payload = jws.getPayload();
    System.out.println("JWS payload: " + payload);
  }
}
