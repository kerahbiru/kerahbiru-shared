package com.kerahbiru.shared.event

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class OtpToSmsRequestedData(phone: String, countryCode: String, otp: String)

object OtpToSmsRequestedData extends AggregateMeta {
  override val aggregateName: String = "authenticate"

  implicit val enc: Decoder[OtpToSmsRequestedData] = deriveDecoder
  implicit val dec: Encoder[OtpToSmsRequestedData] = deriveEncoder

}
