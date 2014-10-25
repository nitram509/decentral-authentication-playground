package de.otto;

import de.otto.hmac.authentication.RequestSigningUtil;

public class HmacAuthProvider {

  public static void main(String[] args) {
    String token = new HmacAuthProvider().create();
    System.out.println("Token:" + token);
  }

  public String create() {
    String method = "PUT";
    String dateHeader = "" + System.currentTimeMillis();
    String requestUri = "/test?foo=bar";
    String body = "<html>" +
        "<body>" +
        "<h1>Hello World</h1>" +
        "</body>" +
        "</html>";
    String secret = "geheim";
    String signature = RequestSigningUtil.createRequestSignature(method, dateHeader, requestUri, body, secret);
    return signature;
  }

}
