package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OtpToEmailRequested.Data
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder}
import OtpToEmailRequested.Data

import java.util.UUID

final case class OtpToEmailRequested(
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
      OtpToEmailRequested.aggregateName,
      OtpToEmailRequested.eventName,
      x.asJson.noSpaces
    )

object OtpToEmailRequested extends Meta {
  override val aggregateName: String = "authenticate"
  override val eventName: EventName  = EventName.OtpToEmailRequested

  def apply(id: UUID, email: String, country: String, otp: String): Event =
    OtpToEmailRequested(id, 0, Event.nowUtc, id, Data(email, country, otp))

  final case class Data(email: String, country: String, otp: String)

  object Data {

    implicit val dec: Decoder[Data] = deriveDecoder
    implicit val enc: Encoder[Data] = deriveEncoder

  }

}
