package com.kerahbiru.shared.jwt

import cats.Monad
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID
import scala.concurrent.Future

class JwtContentTest extends AsyncFlatSpec {
  implicit override def executionContext = scala.concurrent.ExecutionContext.Implicits.global

  behavior of "Token"

  val sig = "abc"

  case class ABC() extends Token[Future] {

    override def F: Monad[Future]                      = Monad[Future]
    override def sign(content: String): Future[String] = Future.successful(sig)
  }

  it should "ok creating token with Token typeclass" in {
    val x = JwtPayload(
      iat = System.currentTimeMillis() / 1000,
      exp = System.currentTimeMillis() / 1000 + 1000,
      sub = UUID.randomUUID(),
      alias = UUID.randomUUID().toString,
      role = Role.org,
      country = Country.ID
    )

    ABC().create(x).map(p => assert(p.contains(sig)))
  }

}
