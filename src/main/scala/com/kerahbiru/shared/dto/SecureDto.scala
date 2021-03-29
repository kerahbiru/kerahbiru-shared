package com.kerahbiru.shared.dto

import com.kerahbiru.shared.event.Event
import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

case class SecureDto(cipherText: String) {
  val isSecure: Boolean = true
}

object SecureDto {
  implicit val dec: Decoder[SecureDto] = deriveDecoder
  implicit val enc: Encoder[SecureDto] = deriveEncoder
}
