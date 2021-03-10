package com.kerahbiru.shared.event

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

final case class OtpToEmailRequestedData(email: String, otp: String) extends EventData

object OtpToEmailRequestedData extends AggregateMeta {
  override val aggregateName: String = "authenticate"

  implicit val enc: Decoder[OtpToEmailRequestedData] = deriveDecoder
  implicit val dec: Encoder[OtpToEmailRequestedData] = deriveEncoder

}
