package com.kerahbiru.shared.event

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

//final case class OrgUpdatedData(
//    name: String,
//    location: String,
//    description: Option[String]
//) extends EventData
//
//object OrgUpdatedData extends Meta {
//  override val aggregateName: String = "org"
//
//  implicit val dec: Decoder[OrgUpdatedData] = deriveDecoder
//  implicit val enc: Encoder[OrgUpdatedData] = deriveEncoder
//}
