package com.kerahbiru.shared.jwt

import com.kerahbiru.shared.jwt.Role.findValues
import enumeratum._
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}

import java.util.UUID
import scala.util.Try

final case class JwtPayload(
    iss: String = "https://www.kerahbiru.com",
    iat: Long,
    exp: Long,
    sub: UUID,
    alias: String,
    role: Role,
    country: Country
)

sealed trait Role extends EnumEntry
object Role extends Enum[Role] with CirceEnum[Role] {
  val values = findValues
  case object worker extends Role
  case object org    extends Role
}

sealed trait Country extends EnumEntry
object Country extends Enum[Country] with CirceEnum[Country] {

  val values = findValues
  case object ID extends Country
  case object PH extends Country
  case object VN extends Country
  case object SG extends Country
  case object MY extends Country
  case object TH extends Country
  case object KH extends Country
  case object LA extends Country
  case object MM extends Country
  case object BN extends Country
  case object JP extends Country
  case object TW extends Country
  case object KR extends Country
  case object HK extends Country
}

object JwtPayload {

  implicit val decodeUuidKey: KeyDecoder[UUID] =
    KeyDecoder.instance(s => Try(UUID.fromString(s)).toOption)

  implicit val encodeUuidKey: KeyEncoder[UUID] =
    KeyEncoder.instance(_.toString)

  implicit val enc: Decoder[JwtPayload] = deriveDecoder
  implicit val dec: Encoder[JwtPayload] = deriveEncoder

//  implicit val decodeFoo: Decoder[JwtPayload] = new Decoder[JwtPayload] {
//
//    final def apply(c: HCursor) =
//      for {
//        iss <- c.downField("iss").as[String]
//        iat <- c.downField("iat").as[Long]
//        exp <- c.downField("exp").as[Long]
//        x   <- c.downField("sub").as[String]
//        sub = UUID.fromString(x)
//        alias <- c.downField("alias").as[String]
//        role  <- c.downField("role").as[Role]
//        plan  <- c.downField("plan").as[Plan]
//
//      } yield {
//        new JwtPayload(iss, iat, exp, sub, alias, role, plan)
//      }
//  }

}
