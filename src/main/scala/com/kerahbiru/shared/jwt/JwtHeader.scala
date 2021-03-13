package com.kerahbiru.shared.jwt

import com.kerahbiru.shared.jwt.Alg.RS256
import com.kerahbiru.shared.jwt.Typ.JWT
import enumeratum.{CirceEnum, Enum, EnumEntry}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder}

final case class JwtHeader(alg: Alg, typ: Typ)

sealed trait Alg extends EnumEntry
object Alg extends Enum[Alg] with CirceEnum[Alg] {
  val values = findValues
  case object RS256 extends Alg
}

sealed trait Typ extends EnumEntry
case object Typ extends Enum[Typ] with CirceEnum[Typ] {
  val values = findValues
  case object JWT extends Typ
}

object JwtHeader {

  def apply: JwtHeader = new JwtHeader(RS256, JWT)

  implicit val enc: Encoder[JwtHeader] = deriveEncoder
  implicit val dec: Decoder[JwtHeader] = deriveDecoder

}
