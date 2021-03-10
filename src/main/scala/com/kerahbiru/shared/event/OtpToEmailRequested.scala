package com.kerahbiru.shared.event

import io.circe
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser.decode
import io.circe.syntax.EncoderOps

import java.util.UUID

//case class OtpToEmailRequested(
//    override val id: UUID,
//    override val version: Int,
//    override val user: UUID,
//    x: OtpToEmailRequestedData
//) extends Event(
//      id,
//      version,
//      Event.nowUtc,
//      user,
//      OtpToEmailRequestedData.aggregateName,
//      EventName.OrgCreated,
//      x.asJson.noSpaces
//    ) {}

object OtpToEmailRequested extends Meta {
  override val aggregateName: String = "authenticate"
  override val eventName: EventName  = EventName.OtpToEmailRequested
  def create(id: UUID, email: String, otp: String): Event =
    Event(id, 0, Event.nowUtc, id, aggregateName, eventName, OtpToEmailRequestedData(email, otp).asJson.noSpaces)

//  def fromJson(json: String): Either[circe.Error, OtpToEmailRequested] = decode[OtpToEmailRequested](json)
//
//  def toJson(o: OtpToEmailRequested): String = o.asJson.noSpaces
//
//  implicit val dec: Decoder[OtpToEmailRequested] = deriveDecoder
//  implicit val enc: Encoder[OtpToEmailRequested] = deriveEncoder
}

final case class OtpToEmailRequestedData(email: String, otp: String) extends EventData

object OtpToEmailRequestedData {

  implicit val dec: Decoder[OtpToEmailRequestedData] = deriveDecoder
  implicit val enc: Encoder[OtpToEmailRequestedData] = deriveEncoder

}
