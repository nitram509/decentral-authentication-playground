package auth0;

import com.auth0.jwt.JWTVerifier;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

/**
 * Strange!!!!!
 * (1) dieses Tool schlägt auch fehl: http://jwt.1blau.com/#/
 * (2) dieses Tool funzt: http://jwt.io/
 */
public class JwtAuthVerifier {

  public static void main(String[] args) {
    try {
      Map<String, Object> payload = verify(new JwtAuthProvider().create());

      // Get custom fields from decoded Payload
      System.out.println(payload.get("name"));
    } catch (SignatureException e) {
      e.printStackTrace();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static Map<String, Object> verify(String token) throws NoSuchAlgorithmException, InvalidKeyException, IOException, SignatureException {
    final String secret = "This is a secret";
    return new JWTVerifier(secret)
        .verify(token);
  }
}
