<?php
require_once __DIR__ . '/../vendor/autoload.php';

$key = file_get_contents(__DIR__ . '/rsa_publickey.pem');

$jwsString = 'eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6ImlkLTA4MTUifQ.eyJzdWIiOiJzb21lb25lQGV4YW1wbGUuY29tIiwiZXhwIjoxNDE0MjMzODg4fQ.WHw6fYPcdJsn2qmBF8OEqLm8zABhyWXY3H9a6CM-hWgvgD7PwxSDBahz-0-OrqTeBF_6afD63PTGLMAEGKKbmZbPxMorRqZ9e4tSqyR9v2rRBlqeSuP0kNUySEeDmPskNyc8oh_bcI-xU5XodMsqYqk-H_38cymDclWTEB9tUYI';

try {
  $jws = new \Gamegos\JWS\JWS();
  $token = $jws->decode($jwsString, $key);
  print_r('>> Token:');
  print_r($token);
} catch (\Gamegos\JWT\Exception\JWTException $e) {
  //var_dump($e->getToken());
}
