package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OtpToSmsRequested.Data
import com.kerahbiru.shared.jwt.{Country, Role}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder}

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

  def apply(
      id: UUID,
      phone: String,
      version: Int,
      iat: Long,
      key: UUID,
      otp: String,
      exp: Long,
      role: Role,
      country: Country
  ): Event =
    OtpToSmsRequested(id, version, iat, id, Data(phone, iat, key, otp, exp, role, country))

  override val aggregateName: AggregateName = AggregateName.otp

  override val eventName: EventName = EventName.OtpToSmsRequested

  final case class Data(phone: String, iat: Long, key: UUID, otp: String, exp: Long, role: Role, country: Country)

  object Data {
    implicit val dec: Decoder[Data] = deriveDecoder
    implicit val enc: Encoder[Data] = deriveEncoder
  }
}
