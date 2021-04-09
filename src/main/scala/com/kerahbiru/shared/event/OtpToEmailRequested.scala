package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OtpToEmailRequested.Data
import com.kerahbiru.shared.jwt.{Country, Role}
import io.circe.generic.auto._
import io.circe.syntax.EncoderOps

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
      Event.vhash(version),
      iat,
      user,
      OtpToEmailRequested.aggregateName,
      OtpToEmailRequested.eventName,
      x.asJson.noSpaces
    )

object OtpToEmailRequested extends Meta {
  override val aggregateName: AggregateName = AggregateName.otp
  override val eventName: EventName         = EventName.OtpToEmailRequested

  def apply(
      id: UUID,
      email: String,
      version: Int,
      iat: Long,
      key: UUID,
      otp: String,
      exp: Long,
      role: Role,
      country: Country
  ): Event =
    OtpToEmailRequested(id, version, iat, id, Data(email, iat, key, otp, exp, role, country))

  final case class Data(email: String, iat: Long, key: UUID, otp: String, exp: Long, role: Role, country: Country)

}
