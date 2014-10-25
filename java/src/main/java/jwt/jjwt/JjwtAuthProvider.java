package jwt.jjwt;

import io.jsonwebtoken.Jwts;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Random;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

public class JjwtAuthProvider {

  public static void main(String[] args) {

    JjwtAuthProvider provider = new JjwtAuthProvider();
    String token = provider.create();
    System.out.println("Token = " + token);
  }

  public String create() {
//    String secret = createSecret();
    String secret = "m99WO+09Cr67yVF0I5kfErKQPz4ndqKaHQVKaYPhui4kxcwO7OcS2ft/KUgGglCKa7ncp2yKccQsJRTH4LBQyw=="; //createSecret();
    String compact = Jwts.builder().setSubject("Joe").signWith(HS256, secret).compact();
    return compact;
  }

  private String createSecret() {
    Random random = new SecureRandom();
    byte[] key = new byte[64];
    random.nextBytes(key);
    return Base64.getEncoder().encodeToString(key);
  }

}
