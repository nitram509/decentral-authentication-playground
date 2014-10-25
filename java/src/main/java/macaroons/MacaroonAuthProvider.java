package macaroons;

import com.github.nitram509.jmacaroons.Macaroon;
import com.github.nitram509.jmacaroons.MacaroonsBuilder;

public class MacaroonAuthProvider {

  public static void main(String[] args) {
    Macaroon macaroon = new MacaroonAuthProvider().create();
    System.out.println("Details:\n" + macaroon.inspect());
    System.out.println("Token:" + macaroon.serialize());
  }

  public Macaroon create() {
    String location = "http://www.example.org";
    String secret = "geheim";
    String identifier = "this is an example macaroon";
    Macaroon macaroon = new MacaroonsBuilder(location, secret, identifier)
        .add_first_party_caveat("time < 2014-12-01")
        .add_first_party_caveat("authority = DEV_TOOLS_AVAILABLE")
        .getMacaroon();

    return macaroon;
  }

}
