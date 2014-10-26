package nimbus_jose_jwt.private_public;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import example_keys.Alice;

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
    JWSSigner signer = new RSASSASigner(Alice.getPrivateKey());

// Prepare JWT with claims set
    JWTClaimsSet claimsSet = new JWTClaimsSet();
    claimsSet.setSubject("alice");
    claimsSet.setIssueTime(new Date());
    claimsSet.setIssuer("http://example.com");

    String keyId = "id-0815";
    JWSHeader header = new JWSHeader(JWSAlgorithm.RS256, null, null, null, null, null, null, null, null, null, keyId, null, null);
    SignedJWT signedJWT = new SignedJWT(header, claimsSet);

    signedJWT.sign(signer);

    return signedJWT.serialize();
  }

}
