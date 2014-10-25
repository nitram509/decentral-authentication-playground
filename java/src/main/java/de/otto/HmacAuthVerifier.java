package de.otto;

import de.otto.hmac.authentication.RequestSigningUtil;
import de.otto.hmac.authentication.WrappedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

import static org.mockito.Mockito.mock;

public class HmacAuthVerifier {

  public static void main(String[] args) {
    String token = new HmacAuthProvider().create();
    try {
      new HmacAuthVerifier().verify(token);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void verify(String token) throws IOException {
    String secret = "geheim";
    WrappedRequest request = WrappedRequest.wrap(new HttpServletRequestWrapper(createServletRequestMock()));
    RequestSigningUtil.checkRequest(request, secret);
  }

  private HttpServletRequest createServletRequestMock() {
    HttpServletRequest mock = mock(HttpServletRequest.class);

    // todo need to mock a request with all details and headers and token

    return mock;
  }

}
