package com.kerahbiru.shared.event

import java.util.UUID

class Event(
    val id: UUID,
    val version: Int,
    val iat: Long,
    val user: UUID,
    val aggregate: String,
    val name: EventName,
    val data: String
)

object Event {

  def nowUtc: Long = System.currentTimeMillis() / 1000

//  implicit val dec: Decoder[Event] = deriveDecoder
//  implicit val enc: Encoder[Event] = deriveEncoder
//
//  implicit val decodeUuidKey: KeyDecoder[UUID] =
//    KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)
//
//  implicit val encodeUuidKey: KeyEncoder[UUID] =
//    KeyEncoder.instance(_.toString)
}
