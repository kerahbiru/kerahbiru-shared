package com.kerahbiru.shared.event

import io.circe.{Decoder, Encoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}

//final case class OtpToSmsRequestedData(phone: String, countryCode: String, otp: String) extends EventData
//
//object OtpToSmsRequestedData extends Meta {
//  override val aggregateName: String = "authenticate"
//
//  implicit val dec: Decoder[OtpToSmsRequestedData] = deriveDecoder
//  implicit val enc: Encoder[OtpToSmsRequestedData] = deriveEncoder
//
//}
