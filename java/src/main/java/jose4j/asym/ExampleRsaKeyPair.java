package jose4j.asym;

import org.jose4j.base64url.SimplePEMEncoder;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

class ExampleRsaKeyPair {

  static RSAPrivateKey createPrivateKey() {
    try {
      KeySpec ks = new PKCS8EncodedKeySpec(privateKey());
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      return (RSAPrivateKey) keyFactory.generatePrivate(ks);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  static RSAPublicKey createPublicKey() {
    try {
      X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKey());
      KeyFactory kf = KeyFactory.getInstance("RSA");
      return (RSAPublicKey) kf.generatePublic(spec);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new RuntimeException(e);
    }
  }

  static byte[] privateKey() {
    /*
     * openssl genrsa 2048 > rsa.private
     * openssl pkcs8 -topk8 -inform PEM -outform PEM -in rsa.private  -nocrypt > privatekey.pem
     */
    return SimplePEMEncoder.decode(
          /*-----BEGIN PRIVATE KEY-----*/
        "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDRZegoPcpip2Ab\n" +
            "c6W5AgZn02QmGwn9vRSwFSDiV0bPv9ieM7Oq0O9Es4xeOEETflnUUbmqD7W5XWAS\n" +
            "0+JwHNXdm94BXPW2ae/xcncH3Pyyjj7fFEsi3kQKSFoKgNPI6zv6oFd8StDkMq7u\n" +
            "peJKBgRvqoAHb9OLsoGu8gkCrd6P3qEI5ADCWF6KO7Uwk7qkaiPvVksixYZLFZWM\n" +
            "g/3seBoMpiAY9xn3ccMYyTinSIqK3k2EWwCK5KQgi6WijrZY6rRbdeJloRNpma+e\n" +
            "tKHVKqiJzcKhFJoRmF9edeHJ5623BjvqMfbYMzVjSSsI2o37Ss/v8JmRQL7AHrLr\n" +
            "UZMHOb0zAgMBAAECggEBAJqV/wPmSgj2w67Ae7HPWuc/Wgn/oCqgcitkDbjK8c3h\n" +
            "NYvlY1hRkrJHUvMj2HcJqktiYgy4OFI2Kvz7S4m03/YQYZ7j/8nA/5BiDpnRfQRc\n" +
            "mvGZS7bcG6LzsvVaEf+cGdV2ld9+ZCRzfk1UTXCUbKX7WzUiSywwhiifKiO+NDZE\n" +
            "sDZpqCSnWpeWiWTSa521/3qokQf0MF0PDd4kTVW/N/iP+2kff9bre43V6Qd6lLPc\n" +
            "+d5PPWHsY2rYg9HvVyiUSSGam8zdeHF/DQ2H6aKP0H8EiHFzt4byh30u1Dcxq1e0\n" +
            "PTfpx26hBownHDZAYSOba4KFUQIH95Sy88xZFSncIrECgYEA+BRxBF+rZuietSTU\n" +
            "SWwEKZJBGJoi5WXcwtE1nAcvSJVwuUiO9ZaL7J3jkp4YFWu6k0C+VV5blVxv0Y+a\n" +
            "uY0nPw5aVhoN5lLmGSDbGI8NQL8i9aGr8yqehC3GDnJ4XkrRnfGo6uMDYGzTVQ0Z\n" +
            "fFGiSdlxJNxaekDlDP7LBzQnb20CgYEA2BVRpfJFhG0AgHI1Q9InHKhojpZzEb1y\n" +
            "FHPypVvF753ACCXzU/R5P7HCYh74U8Ykk1sxxDH/RGF7C7bTpEjnZdHVhDvy0Dj7\n" +
            "EcNyBewKPCqaKwMiCmqD+dwRYLOmnzJM86qszDT5tP9cqIZL5IzBMjYy0f6PCdsp\n" +
            "pRS+quEF2x8CgYAiR7DWSeIFdl936H7tJl/s3qjgHWx1Bn5a7U3bIaMHBJ9u+vfG\n" +
            "N+dd6vNnbzEkxJ4jeu7SHRfKq9tHufBHc5nYvML+4trI2OuyzTd5TUsnh0Htb0mz\n" +
            "cFA3M5wKowa/ANe2W45b5sKwsFkDh6vd4enrCoD39vsJqa38/bhQZoSMgQKBgCSV\n" +
            "t3U5R7vcGLGmpI4PDsAzY0CBCIqAYMhFuVd16lVfiBvZERGt2rmGBztFsxW7/oVU\n" +
            "c2CcdnPFbWfWVI8EKOl8VYJGBB5tB1VCmhOWm0UEkYWmyiSpVB8Bu49720Io6g53\n" +
            "XzpT3dNfSPjfhAn2Kt+tfX1AdEde6/YfRQj5gFxRAoGBAJ5aCBqf/uTqB8KRc+B1\n" +
            "ryi7ZPLiS1VqSpIxd/LLVKU3anO3yq8SOPxjpW8yt4wOBeDVFyT8EZGtUHFDZxzS\n" +
            "hI7ioQdh/HBt7L/oj4bde3HmRqPEGIcszbQiRFHsv1+Gwr31NuOwcNpWRdzvs/YB\n" +
            "aWb8QOH9zcxFzDyILtrWhHfi"
          /*-----END PRIVATE KEY-----*/
    );
  }

  static byte[] publicKey() {
    /*
     * openssl genrsa 2048 > rsa.private     // same file as from above privateKey !!!
     * openssl rsa -inform PEM -in rsa.private -pubout
     */
    return SimplePEMEncoder.decode(
          /*-----BEGIN PUBLIC KEY-----*/
        "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0WXoKD3KYqdgG3OluQIG\n" +
            "Z9NkJhsJ/b0UsBUg4ldGz7/YnjOzqtDvRLOMXjhBE35Z1FG5qg+1uV1gEtPicBzV\n" +
            "3ZveAVz1tmnv8XJ3B9z8so4+3xRLIt5ECkhaCoDTyOs7+qBXfErQ5DKu7qXiSgYE\n" +
            "b6qAB2/Ti7KBrvIJAq3ej96hCOQAwlheiju1MJO6pGoj71ZLIsWGSxWVjIP97Hga\n" +
            "DKYgGPcZ93HDGMk4p0iKit5NhFsAiuSkIIuloo62WOq0W3XiZaETaZmvnrSh1Sqo\n" +
            "ic3CoRSaEZhfXnXhyeettwY76jH22DM1Y0krCNqN+0rP7/CZkUC+wB6y61GTBzm9\n" +
            "MwIDAQAB"
          /*-----END PUBLIC KEY-----*/
    );
  }
}
