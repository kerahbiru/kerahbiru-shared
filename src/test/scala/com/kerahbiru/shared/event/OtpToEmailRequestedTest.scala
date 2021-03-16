package com.kerahbiru.shared.event

import com.kerahbiru.shared.jwt.Country
import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import io.circe.syntax._
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class OtpToEmailRequestedTest extends AnyFlatSpec {

  behavior of "OtpToEmailRequested"

  val id: UUID = UUID.randomUUID()
  val email    = "a@b.com"
  val otp      = "12345"
  val country  = "ID"

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val otpToEmailRequestedEvent = OtpToEmailRequested(id, email, Country.withName(country), otp)
    val json                     = otpToEmailRequestedEvent.asJson.noSpaces
    val event                    = decode[Event](json).toOption.get
    assert(event.id === id)
    assert(event.name === EventName.OtpToEmailRequested)
    val data = decode[OtpToEmailRequested.Data](event.data).toOption.get
    assert(data.email === email)
    assert(data.otp === otp)
  }

}
