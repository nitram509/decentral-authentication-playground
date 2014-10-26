package nimbus_jose_jwt.private_public;

import org.jose4j.base64url.SimplePEMEncoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

class RsaPublicKey_from_PHP_counterpart {

  static RSAPublicKey createPublicKey() {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(deserializePublicKey());
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return (RSAPublicKey) kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
      throw new RuntimeException(e);
    }
  }

  static byte[] deserializePublicKey() throws IOException {
    File pemFile = new File("php/src/rsa_publickey.pem");
    assert pemFile.exists();
    BufferedReader reader = new BufferedReader(new FileReader(pemFile));
    StringBuilder sb = new StringBuilder();
    for (String s; (s = reader.readLine()) != null; ) {
      if (s.trim().startsWith("-----BEGIN")) continue;
      if (s.trim().startsWith("-----END")) continue;
      sb.append(s).append('\n');
    }
    return SimplePEMEncoder.decode(sb.toString());
  }
}
