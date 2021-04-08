package com.kerahbiru.shared.event

import com.kerahbiru.shared.aggmeta.AggregateName
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

import java.util.UUID
import scala.util.Try

class Event(
    val id: UUID,
    val version: Int,
    val iat: Long,
    val user: UUID,
    val aggregate: AggregateName,
    val name: EventName,
    val data: String
)

object Event {

  def nowUtc: Long = System.currentTimeMillis() / 1000

  implicit val dec: Decoder[Event] = deriveDecoder
  implicit val enc: Encoder[Event] = deriveEncoder

//  implicit val decodeUuidKey: KeyDecoder[UUID] =
//    KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)
//
//  implicit val encodeUuidKey: KeyEncoder[UUID] =
//    KeyEncoder.instance(_.toString)
}
