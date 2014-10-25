package jwt.auth0;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.ClaimSet;
import com.auth0.jwt.JwtProxy;
import com.auth0.jwt.impl.BasicPayloadHandler;
import com.auth0.jwt.impl.JwtProxyImpl;

public class JwtAuthProvider {

  public static void main(String[] args) {
    try {
      JwtAuthProvider provider = new JwtAuthProvider();
      String token = provider.create();
      System.out.println("Token = " + token);
      String payload = provider.decode(token);
      System.out.println("Payload = " + payload);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String create() throws Exception {
    final String secret = "This is a secret";
    final Algorithm algorithm = Algorithm.HS256;

    MyUser myUser = new MyUser();
    myUser.setUsername("jwt");
    myUser.setPassword("mypassword");

    JwtProxy proxy = new JwtProxyImpl();
    proxy.setPayloadHandler(new BasicPayloadHandler());

    ClaimSet claimSet = new ClaimSet();
    claimSet.setExp((24 * 60 * 60)); // expire in 24 hours
    String token = proxy.encode(algorithm, myUser, secret, claimSet);
    return token;
  }

  private String decode(String token) throws Exception {
    final String secret = "This is a secret";
    final Algorithm algorithm = Algorithm.HS256;

    JwtProxy proxy = new JwtProxyImpl();
    proxy.setPayloadHandler(new BasicPayloadHandler());

    Object payload = proxy.decode(algorithm, token, secret);
    return payload.toString();
  }
}
