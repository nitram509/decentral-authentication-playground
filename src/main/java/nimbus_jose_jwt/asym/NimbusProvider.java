package nimbus_jose_jwt.asym;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Date;

public class NimbusProvider {

  public static void main(String[] args) {
    String token = null;
    try {
      token = new NimbusProvider().create();
      System.out.println("Token: " + token);
    } catch (JOSEException e) {
      throw new RuntimeException(e);
    }
  }

  String create() throws JOSEException {

// Create RSA-signer with the private key
    JWSSigner signer = new RSASSASigner(createPrivateKey());

// Prepare JWT with claims set
    JWTClaimsSet claimsSet = new JWTClaimsSet();
    claimsSet.setSubject("alice");
    claimsSet.setIssueTime(new Date());
    claimsSet.setIssuer("https://c2id.com");

    SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.RS256), claimsSet);

// Compute the RSA signature
    signedJWT.sign(signer);

    return signedJWT.serialize();
  }

  private RSAPrivateKey createPrivateKey() {
    try {
      KeySpec ks = new PKCS8EncodedKeySpec(ExampleRsaKeyPair.privateKey());
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return (RSAPrivateKey) keyFactory.generatePrivate(ks);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }
}
