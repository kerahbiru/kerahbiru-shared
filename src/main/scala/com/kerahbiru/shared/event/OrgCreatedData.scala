package com.kerahbiru.shared.event

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

//final case class OrgCreatedData(
//    name: String,
//    location: String,
//    description: Option[String]
//) extends EventData
//
//object OrgCreatedData extends Meta {
//
//  override val aggregateName: String = "org"
//
//  implicit val dec: Decoder[OrgCreatedData] = deriveDecoder
//  implicit val enc: Encoder[OrgCreatedData] = deriveEncoder
//
//}
