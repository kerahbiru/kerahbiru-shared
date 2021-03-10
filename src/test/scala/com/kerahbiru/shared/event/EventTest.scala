package com.kerahbiru.shared.event

import io.circe.Decoder
import io.circe.generic.semiauto.deriveDecoder
import io.circe.parser.decode
import io.circe.syntax._
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class EventTest extends AnyFlatSpec {

  behavior of "Event"

  it should "ok encoding to json" in {

    val id    = UUID.randomUUID()
    val data  = OtpToEmailRequestedData("a@b.com", "12345")
    val event = OtpToEmailRequested.create(id, "a@b.com", "12345")

    val json                         = event.asJson.noSpaces
    implicit val dec: Decoder[Event] = deriveDecoder
    val x                            = decode[Event](json)
    assert(x.isRight)
    x.map(p => assert(p.id === id))

  }

}
