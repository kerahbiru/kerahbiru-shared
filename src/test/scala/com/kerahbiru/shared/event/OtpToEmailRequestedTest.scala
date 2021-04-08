package com.kerahbiru.shared.event

import com.kerahbiru.shared.jwt.{Country, Role}
import io.circe.parser.decode
import io.circe.syntax._
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class OtpToEmailRequestedTest extends AnyFlatSpec {

  behavior of "OtpToEmailRequested"

  val key: UUID = UUID.randomUUID()
  val email     = "a@b.com"
  val otp       = "12345"
  val country   = "ID"
  val iat       = System.currentTimeMillis() / 1000
  val exp       = iat + 3600 * 24;
  val id        = UUID.randomUUID()

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val otpToEmailRequestedEvent =
      OtpToEmailRequested(id, email, 1, iat, key, otp, exp, Role.org, Country.withName(country))
    val json  = otpToEmailRequestedEvent.asJson.noSpaces
    val event = decode[Event](json).toOption.get
    assert(event.name === EventName.OtpToEmailRequested)
    val data = decode[OtpToEmailRequested.Data](event.data).toOption.get
    assert(data.email === email)
    assert(data.otp === otp)
  }

}
