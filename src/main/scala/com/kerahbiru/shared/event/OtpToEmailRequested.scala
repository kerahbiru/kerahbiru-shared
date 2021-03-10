package com.kerahbiru.shared.event

import io.circe
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser.decode
import io.circe.syntax.EncoderOps

import java.util.UUID

case class OtpToEmailRequested(
    override val id: UUID,
    override val version: Int,
    override val user: UUID,
    x: OtpToEmailRequestedData
) extends Event(
      id,
      version,
      Event.nowUtc,
      user,
      OtpToEmailRequested.aggregateName,
      EventName.OtpToEmailRequested,
      x.asJson.noSpaces
    )

object OtpToEmailRequested extends Meta {
  override val aggregateName: String = "authenticate"
  override val eventName: EventName  = EventName.OtpToEmailRequested

  def apply(id: UUID, email: String, otp: String): Event =
    OtpToEmailRequested(id, 0, id, OtpToEmailRequestedData(email, otp))

}

final case class OtpToEmailRequestedData(email: String, otp: String) extends EventData

object OtpToEmailRequestedData {

  implicit val dec: Decoder[OtpToEmailRequestedData] = deriveDecoder
  implicit val enc: Encoder[OtpToEmailRequestedData] = deriveEncoder

}
