package example_keys;

import org.jose4j.base64url.SimplePEMEncoder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Bob {

  static RSAPrivateKey createPrivateKey() {
    try {
      KeySpec ks = new PKCS8EncodedKeySpec(deserializePrivateKey());
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return (RSAPrivateKey) keyFactory.generatePrivate(ks);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  static RSAPublicKey createPublicKey() {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(deserializePublicKey());
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return (RSAPublicKey) kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  static byte[] deserializePrivateKey() {
    /*
     * openssl genrsa 2048 > rsa.private
     * openssl pkcs8 -topk8 -inform PEM -outform PEM -in rsa.private  -nocrypt > privatekey.pem
     */
    return SimplePEMEncoder.decode(
          /*-----BEGIN PRIVATE KEY-----*/
        "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDt8XXYxcsOiojz\n" +
            "Leb2hATx8JCuFjShgwQZKFOr0vqyu5K2jNQvY9flvrkY4tj0Ek44+0qmGVYHyQxl\n" +
            "t+3Xc465DuWRAwOMbtC3pxzEvsoiTOcrmf62LhUrXAx6u96WP7g0B06XmKRwykyr\n" +
            "qEAf/uOJoh8X6MzP6z5Q2X2QK0g/TMLo2c2iAk97l+kJS1WJ6pdinM5zpXXxM0/7\n" +
            "2iB7wNbc8yZpoVrdLZ5tcOCwX7pBZicczKa5ZtSK7315ZjXPbcLJ11Gb260h5yLw\n" +
            "3q/mIK9T+Y4Vg1rBIO5nLw2Kx5ZbLJ3TcAZ43IEINIcOlbdgZax1ADuQ0V8fBaHC\n" +
            "BtHJ6MdLAgMBAAECggEALFZ2EzKoJ8mAw2+mPgFTupGNJCNXI1F6jgWJsdbaiDuT\n" +
            "lxie4UXFkTX837pFmxVeJhgNTO4jrce0269uTltmGO3BsUfSfXSYx+AH6FF08PdL\n" +
            "jEBhyijEIRRB7wza6TxuYqOJ7Y2v9qR7rPDpIg0o8RiYmqfyoju/nOkqEJdAGysi\n" +
            "mpGu1u1RzZwm0LgiL8TT6N8bde3CGqa2IsIVb2J6Pby8mpsFxJbDZuc1N0tfQ9X3\n" +
            "EFUGvi5SknQJMLMtBUx06r1whqS7rIP9BPGG5ANm0rQp3Kq1BeeR/DmI7aynkCRI\n" +
            "4mnBTLai7gG8tVZsRQBLrodVRixoGiUax65iC+5qgQKBgQD5UVTnVS9M2nORe9w9\n" +
            "zR/KgcuCRT3O/O32my4RAlmR7xKckcJIYkrOa79T5nXEye4Z3HnxCetDpjmgGDj7\n" +
            "s8UXh+BqFId3H9VAgo4XwSfYdMPw/V83+YTnyV/0lN3zDQ8fJHKEUBl8deMm5vAB\n" +
            "4FoTuBd5HdWnf81hPH9O1wsZWwKBgQD0UhVuS5AqOuKtUJRYjpxE6ybnSSDL7J+O\n" +
            "rTluR65XdLuCushXKbTHKs9mFJ0YyPJ/qThQ1AURQST537ObB+w1CxNt8Qo0ePqg\n" +
            "FaFDa/JSws4LbRI9bovYQCgKTZausTs9pEkmHzQ5uF9n435uvb+cyZdtZEYhTkb9\n" +
            "l12MoIN80QKBgQCXvzIPL2D/xQKMbTgLAZu09TU50A0ju/zWmXL7dVOxKDNKysjW\n" +
            "p8TiSOCGH4JUyZc7dF9gH2prnXP7hmLAiJWQkeo8hsxA7EYu4oe725v1+nP8bSsU\n" +
            "dbG4JnBZ/HXu5mbqzJNLBznL8DDWz78LVVNeJzFaTZdqQLZv1ZOjF+4wewKBgA6/\n" +
            "dbO2s/kYnN5BYvMFiMFQQITtkSE+I1OFeVYC1a9JyJfXlohMaDEtkF9lFyaL2reC\n" +
            "C3g1lCi6efTrX/gXvlZlWMLmLeuE8fv/0kAzn+kHAVEHQHac2awwEzqr/FCHOkA6\n" +
            "ToASmiBx1LB8twEQSyS3toBP8TvJcdeU73yxo75BAoGAYHFILOki4WuVGrmaZrmN\n" +
            "8JwWTSn1WcFtNkTV1Gl7DgVoGa8NdKMa/UqRZtv6YpZfQRiq8U/k9xdlVxK3jzu9\n" +
            "7Wrw8tfepSLahlLFPaZC6T28cm9eUW9hLPiC7KD2yHhCaAKCYTXmel7AwCk+x9jJ\n" +
            "M4a4QS4K5poFtd9ndeXrde4="
          /*-----END PRIVATE KEY-----*/
    );
  }

  static byte[] deserializePublicKey() {
    /*
     * openssl genrsa 2048 > rsa.private     // same file as from above deserializePrivateKey !!!
     * openssl rsa -inform PEM -in rsa.private -pubout
     */
    return SimplePEMEncoder.decode(
          /*-----BEGIN PUBLIC KEY-----*/
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7fF12MXLDoqI8y3m9oQE\n" +
            "8fCQrhY0oYMEGShTq9L6sruStozUL2PX5b65GOLY9BJOOPtKphlWB8kMZbft13OO\n" +
            "uQ7lkQMDjG7Qt6ccxL7KIkznK5n+ti4VK1wMervelj+4NAdOl5ikcMpMq6hAH/7j\n" +
            "iaIfF+jMz+s+UNl9kCtIP0zC6NnNogJPe5fpCUtVieqXYpzOc6V18TNP+9oge8DW\n" +
            "3PMmaaFa3S2ebXDgsF+6QWYnHMymuWbUiu99eWY1z23CyddRm9utIeci8N6v5iCv\n" +
            "U/mOFYNawSDuZy8NiseWWyyd03AGeNyBCDSHDpW3YGWsdQA7kNFfHwWhwgbRyejH\n" +
            "SwIDAQAB"
          /*-----END PUBLIC KEY-----*/
    );
  }
}
