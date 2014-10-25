package jjwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.security.SignatureException;

/**
 * @see <a href="https://github.com/jwtk/jjwt">https://github.com/jwtk/jjwt</a>
 */
public class JjwtAuthVerifier {

  public static void main(String[] args) {
    try {

      String token = new JjwtAuthProvider().create();
      boolean valid = verify(token);

      System.out.println("Valid (subject=Joe) : " + valid);
    } catch (SignatureException e) {
      e.printStackTrace();
    }
  }

  private static boolean verify(String token) throws SignatureException {
    final String secret = "m99WO+09Cr67yVF0I5kfErKQPz4ndqKaHQVKaYPhui4kxcwO7OcS2ft/KUgGglCKa7ncp2yKccQsJRTH4LBQyw==";
    Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    return claims.getSubject().equals("Joe");
  }
}
