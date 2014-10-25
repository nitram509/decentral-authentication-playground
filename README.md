
### A place to play around with decentral authentication mechanisms ...

### Current candidates


#### Java

* JSON Web Token (JWT), JSON Web Signature (JWS), JSON Web Encryption (JWE)
   * https://github.com/auth0/java-jwt
   * https://github.com/jwtk/jjwt
   * https://bitbucket.org/b_c/jose4j/wiki/Home
   * http://connect2id.com/products/nimbus-jose-jwt
* Macaroons
   * https://github.com/nitram509/jmacaroons
* Home made
   * https://github.com/otto-de/hmac-auth


#### PHP

* JSON Web Token (JWT), JSON Web Signature (JWS), JSON Web Encryption (JWE)
   * https://github.com/Gamegos/php-jwt


### My two cents: JW*

In general JWS and JWE are standardized JSON data structures,
where digital signatures or encryption can be applied.
Thus said, they are the nuts and bolts for assembling JWTs.
JSON Web Token (JWT) is a compact, URL-safe means of representing
claims to be transferred between two parties.

IMHO the JW* standard family is way to complicated to use,
because nearly all crypto building blocks are available and can be used.
The library support differs a lot.

Nimbus JOSE + JWT (by Connect2Id) seems to be the most sophisticated implementation.

### My two cents: Macaroons

An interesting candidate is Macaroons - Cookies with Contextual Caveats.
The implementation abstracts all the crypto foo and focuses on easy usage.
On the down-side, at the moment their is no support for public/private key based signatures.


