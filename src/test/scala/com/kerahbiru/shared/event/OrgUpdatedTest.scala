package com.kerahbiru.shared.event

import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class OrgUpdatedTest extends AnyFlatSpec {

  behavior of "OrgUpdated"

  val id: UUID    = UUID.randomUUID()
  val name        = "Mucoindo"
  val location    = "Tangerang Selatan"
  val description = None

  it should "ok in encoding to json, decoding as event, decoding the data" in {
    val request = OrgUpdated(id, name, location, description)
    val json    = request.asJson.noSpaces
    val event   = decode[Event](json).toOption.get
    assert(event.id === id)
    assert(event.name === EventName.OrgUpdated)
    val data = decode[OrgUpdated.Data](event.data).toOption.get
    assert(data.name === name)
    assert(data.location === location)
    assert(data.description === description)
  }
}
