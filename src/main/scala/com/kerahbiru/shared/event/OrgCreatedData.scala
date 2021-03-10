package com.kerahbiru.shared.event

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

final case class OrgCreatedData(
    name: String,
    location: String,
    description: Option[String]
) extends EventData

object OrgCreatedData extends AggregateMeta {

  override val aggregateName: String = "org"

  implicit val enc: Decoder[OrgCreatedData] = deriveDecoder
  implicit val dec: Encoder[OrgCreatedData] = deriveEncoder

}
