package com.kerahbiru.shared.event

import com.kerahbiru.shared.aggmeta.AggregateName
import com.kerahbiru.shared.event.OrgCreated.Data
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps

import java.util.UUID
import scala.util.Try

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
  override val aggregateName: AggregateName = AggregateName.org

  override val eventName: EventName = EventName.OrgCreated

  def apply(orgId: UUID, name: String, location: String, description: Option[String]): Event =
    OrgCreated(orgId, 0, Event.nowUtc, orgId, Data(name, location, description))

  final case class Data(
      name: String,
      location: String,
      description: Option[String]
  )

  object Data {
    implicit val dec: Decoder[Data] = deriveDecoder
    implicit val enc: Encoder[Data] = deriveEncoder

//    implicit val decodeUuidKey: KeyDecoder[UUID] =
//      KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)
//
//    implicit val encodeUuidKey: KeyEncoder[UUID] =
//      KeyEncoder.instance(_.toString)
  }

}
