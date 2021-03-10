package com.kerahbiru.shared.event

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.syntax.EncoderOps
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}

import java.util.UUID
import scala.util.Try

case class Event(
    id: UUID,
    version: Int,
    ts: Long,
    user: UUID,
    aggregate: String,
    name: EventName,
    data: String
)

object Event {

  def nowUtc: Long = System.currentTimeMillis() / 1000

  implicit val dec: Decoder[Event] = deriveDecoder
  implicit val enc: Encoder[Event] = deriveEncoder

  implicit val decodeUuidKey: KeyDecoder[UUID] =
    KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)

  implicit val encodeUuidKey: KeyEncoder[UUID] =
    KeyEncoder.instance(_.toString)
}
