package com.kerahbiru.shared.event

import com.kerahbiru.shared.jwt.{Country, Role}
import com.kerahbiru.shared.util.UUID5
import io.circe.generic.auto._
import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class UserSignedInTest extends AnyFlatSpec {

  behavior of "UserSignedIn"

  val key: UUID = UUID.randomUUID()
  val email     = "a@b.com"
  val otp       = "12345"
  val country   = "ID"
  val iat       = System.currentTimeMillis() / 1000
  val exp       = iat + 3600 * 24;
  val id        = UUID5.v5(email)

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val x     = UserSignedIn(id, 1, iat, email, Role.org, Country.withName(country))
    val json  = x.asJson.noSpaces
    val event = decode[Event](json).toOption.get
    assert(event.name === EventName.UserSignedIn)
    val data = decode[UserSignedIn.Data](event.data).toOption.get
    assert(data.primary === email)
  }
}
