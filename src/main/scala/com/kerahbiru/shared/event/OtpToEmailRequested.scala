package com.kerahbiru.shared.event

import com.kerahbiru.shared.event.OtpToEmailRequested.Data
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import OtpToEmailRequested.Data
import com.kerahbiru.shared.jwt.{Country, Role}
import com.kerahbiru.shared.util.UUID5

import java.util.UUID
import scala.util.Try

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

  val namespace = "4c1ca482-689b-4228-8d6d-a24a4678a59d"

  def apply(email: String, version: Int, iat: Long, key: UUID, otp: String, exp: Long, role: Role, country: Country)
      : Event = {
    val uuid = UUID.fromString(UUID5.v5(email, namespace))
    OtpToEmailRequested(uuid, version, iat, uuid, Data(email, iat, key, otp, exp, role, country))
  }

  final case class Data(email: String, iat: Long, key: UUID, otp: String, exp: Long, role: Role, country: Country)

  object Data {

    implicit val dec: Decoder[Data] = deriveDecoder
    implicit val enc: Encoder[Data] = deriveEncoder

    implicit val decodeUuidKey: KeyDecoder[UUID] =
      KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)

    implicit val encodeUuidKey: KeyEncoder[UUID] =
      KeyEncoder.instance(_.toString)
  }

}
