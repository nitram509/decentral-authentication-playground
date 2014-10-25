package jose4j.asym;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.lang.JoseException;

import java.security.PrivateKey;

public class Jose4jProvider {

  public static void main(String[] args) {
    String token = new Jose4jProvider().create();
    System.out.println("Token:" + token);
  }

  public String create() {
    String examplePayload = "{\"message\":\"This is some JSON payload to be signed\"}";

    JsonWebSignature jws = new JsonWebSignature();

    jws.setPayload(examplePayload);
    jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA384);

    PrivateKey privateKey = ExampleRsaKeyPair.createPrivateKey();
    jws.setKey(privateKey);

    // Sign the JWS and produce the compact serialization or complete JWS representation, which
    // is a string consisting of three dot ('.') separated base64url-encoded
    // parts in the form Header.Payload.Signature
    String jwsCompactSerialization = null;
    try {
      jwsCompactSerialization = jws.getCompactSerialization();
    } catch (JoseException e) {
      throw new RuntimeException(e);
    }

    return jwsCompactSerialization;
  }

}
