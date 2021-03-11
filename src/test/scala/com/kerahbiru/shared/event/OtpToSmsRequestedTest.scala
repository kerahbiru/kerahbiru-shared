package com.kerahbiru.shared.event

import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class OtpToSmsRequestedTest extends AnyFlatSpec {

  behavior of "OtpToSmsRequested"

  val id: UUID    = UUID.randomUUID()
  val phone       = "+442071838750"
  val countryCode = "uk"
  val otp         = "12345"

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val otpToSmsRequested = OtpToSmsRequested(id, phone, countryCode, otp)
    val json              = otpToSmsRequested.asJson.noSpaces
    val event             = decode[Event](json).toOption.get
    assert(event.id === id)
    assert(event.name === EventName.OtpToSmsRequested)
    val data = decode[OtpToSmsRequested.Data](event.data).toOption.get
    assert(data.phone === phone)
    assert(data.countryCode === countryCode)
    assert(data.otp === otp)
  }

}
