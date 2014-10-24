package nimbus_jose_jwt.asym;

import com.nimbusds.jose.jwk.*;

import java.util.List;

public class KeySelector {
  public static void main(String[] args) {
    KeySelector keySelector = new KeySelector();
    keySelector.printJsonWebKey();
    keySelector.selectKeyFromKeyset();
  }

  private void printJsonWebKey() {
    RSAKey rsaKey = new RSAKey.Builder(ExampleRsaKeyPair.createPublicKey())
        .privateKey(ExampleRsaKeyPair.createPrivateKey())
        .keyID("id-0815")
        .keyUse(KeyUse.SIGNATURE)
        .build();

    System.out.println("JWK private + public: " + rsaKey.toJSONString());
    System.out.println("JWK      only public: " + rsaKey.toPublicJWK().toJSONString());
  }

  private void selectKeyFromKeyset() {
    JWKSet jwkSet = new JWKSet();
    jwkSet.getKeys().add(createPublicKey("id-0815"));
    jwkSet.getKeys().add(createPublicKey("foobar"));
    jwkSet.getKeys().add(createPublicKey("id-42-nonsense"));

    JWKSelector selector = new JWKSelector();
    selector.setKeyID("foobar");
    selector.setKeyType(KeyType.RSA);
    selector.setKeyUses(KeyUse.SIGNATURE);
    List<JWK> select = selector.select(jwkSet);
    System.out.println("Selected keyId: " + select.get(0).getKeyID());
  }

  private RSAKey createPublicKey(String keyId) {
    return new RSAKey.Builder(ExampleRsaKeyPair.createPublicKey())
        .keyID(keyId)
        .keyUse(KeyUse.SIGNATURE)
        .build();
  }

}
