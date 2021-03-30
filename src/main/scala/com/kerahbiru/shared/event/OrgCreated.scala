package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OrgCreated.Data
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}
import io.circe.syntax.EncoderOps
import io.circe.generic.auto._

import java.util.UUID

final case class OrgCreated(
    override val id: UUID,
    override val version: Int,
    override val iat: Long,
    override val user: UUID,
    x: Data
) extends Event(
      id,
      version,
      iat,
      user,
      OrgCreated.aggregateName,
      OrgCreated.eventName,
      x.asJson.noSpaces
    )

object OrgCreated extends Meta {
  override val aggregateName: String = "org"

  override val eventName: EventName = EventName.OrgCreated

  def apply(orgId: UUID, name: String, location: String, description: Option[String]): Event =
    OrgCreated(orgId, 0, Event.nowUtc, orgId, Data(name, location, description))

  final case class Data(
      name: String,
      location: String,
      description: Option[String]
  )

}
