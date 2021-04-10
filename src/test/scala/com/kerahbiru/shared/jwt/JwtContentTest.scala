package com.kerahbiru.shared.jwt

import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID
import scala.concurrent.Future

class JwtContentTest extends AsyncFlatSpec {

  implicit override def executionContext = scala.concurrent.ExecutionContext.Implicits.global // NEEDED !
  behavior of "Token"

  it should "ok creating new string token" in {
    val sig               = "abc"
    def signer(s: String) = Future(sig)
    val x = JwtPayload(
      iat = System.currentTimeMillis() / 1000,
      exp = System.currentTimeMillis() / 1000 + 1000,
      sub = UUID.randomUUID(),
      alias = UUID.randomUUID().toString,
      role = Role.org,
      country = Country.ID
    )
    JwtContent
      .create(signer, x)
      .map(p => assert(p.contains(sig)))
  }

}
