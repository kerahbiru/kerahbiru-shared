package com.kerahbiru.shared.event

import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

final case class OrgUpdatedData(
    name: String,
    location: String,
    description: Option[String]
) extends EventData

object OrgUpdatedData extends AggregateMeta {
  override val aggregateName: String = "org"

  implicit val enc: Decoder[OrgUpdatedData] = deriveDecoder
  implicit val dec: Encoder[OrgUpdatedData] = deriveEncoder
}
