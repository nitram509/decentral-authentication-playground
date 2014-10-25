<?php
require_once __DIR__ . '/../vendor/autoload.php';

$key = file_get_contents(__DIR__ . '/rsa_privatekey.pem');

$token = new \Gamegos\JWT\Token();
$token->setClaim('sub', 'someone@example.com');
$token->setClaim('exp', time() + 60*5);
$token->setHeader('alg', 'RS256');
$token->setHeader('typ', 'JWT');
$token->setHeader('kid', 'id-0815');

$jws = new \Gamegos\JWS\JWS();
$jwsString = $jws->encode($token->getHeaders(), $token->getClaims(), $key);

printf("JWT TOKEN: %s\n", $jwsString);
print_r($token->getClaims());
print_r($token->getHeaders());
