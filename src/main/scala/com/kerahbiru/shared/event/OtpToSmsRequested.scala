package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OtpToSmsRequested.Data
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import OtpToSmsRequested.Data
import com.kerahbiru.shared.jwt.Country

import java.util.UUID

final case class OtpToSmsRequested(
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
      OtpToSmsRequested.aggregateName,
      OtpToSmsRequested.eventName,
      x.asJson.noSpaces
    )

object OtpToSmsRequested extends Meta {
  def apply(id: UUID, phone: String, country: Country, otp: String): Event =
    OtpToSmsRequested(id, 0, Event.nowUtc, id, Data(phone, country, otp))

  override val aggregateName: String = "authenticate"

  override val eventName: EventName = EventName.OtpToSmsRequested

  final case class Data(phone: String, country: Country, otp: String)

  object Data {
    implicit val dec: Decoder[Data] = deriveDecoder
    implicit val enc: Encoder[Data] = deriveEncoder
  }

}
