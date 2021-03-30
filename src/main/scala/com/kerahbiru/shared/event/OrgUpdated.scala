package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OrgUpdated.Data
import io.circe.syntax.EncoderOps
import io.circe.generic.auto._

import java.util.UUID

final case class OrgUpdated(
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
      OrgUpdated.aggregateName,
      OrgUpdated.eventName,
      x.asJson.noSpaces
    )

object OrgUpdated extends Meta {
  override val aggregateName: String = "org"

  override val eventName: EventName = EventName.OrgUpdated

  def apply(orgId: UUID, version: Int, name: String, location: String, description: Option[String]): Event =
    OrgUpdated(orgId, version, Event.nowUtc, orgId, Data(name, location, description))

  final case class Data(
      name: String,
      location: String,
      description: Option[String]
  )

}
