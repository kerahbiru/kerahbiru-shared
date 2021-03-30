package com.kerahbiru.shared.dto

import com.kerahbiru.shared.event.Event
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

import java.util.UUID
import scala.util.Try

case class SecureDto(cipherText: String) {
  val isSecure: Boolean = true
}

object SecureDto {

  def apply(cipherText: String): SecureDto = new SecureDto(cipherText)

  implicit val dec: Decoder[SecureDto] = deriveDecoder
  implicit val enc: Encoder[SecureDto] = deriveEncoder

  implicit val decodeUuidKey: KeyDecoder[UUID] =
    KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)

  implicit val encodeUuidKey: KeyEncoder[UUID] =
    KeyEncoder.instance(_.toString)

  abstract class Salted {
    val salt: String
  }

}
