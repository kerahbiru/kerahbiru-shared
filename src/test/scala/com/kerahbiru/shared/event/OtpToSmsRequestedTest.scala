package com.kerahbiru.shared.event

import com.kerahbiru.shared.jwt.{Country, Role}
import io.circe.generic.auto._
import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class OtpToSmsRequestedTest extends AnyFlatSpec {

  behavior of "OtpToSmsRequested"

  val key: UUID = UUID.randomUUID()
  val phone     = "+442071838750"
  val country   = "PH"
  val otp       = "12345"
  val iat       = System.currentTimeMillis() / 1000
  val exp       = iat + 3600 * 24;
  val id        = UUID.randomUUID()

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val otpToSmsRequested = OtpToSmsRequested(id, phone, 1, iat, key, otp, exp, Role.org, Country.withName(country))
    val json              = otpToSmsRequested.asJson.noSpaces
    val event             = decode[Event](json).toOption.get
    assert(event.name === EventName.OtpToSmsRequested)
    val data = decode[OtpToSmsRequested.Data](event.data).toOption.get
    assert(data.phone === phone)
    assert(data.country === Country.withName(country))
    assert(data.otp === otp)
  }

}
