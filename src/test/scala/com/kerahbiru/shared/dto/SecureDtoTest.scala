package com.kerahbiru.shared.dto

import com.kerahbiru.shared.dto.SecureDto.Salted
import com.kerahbiru.shared.jwt.Role
import io.circe.{Decoder, Encoder, KeyDecoder, KeyEncoder}
import io.circe.generic.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec
import io.circe.generic.auto._

import java.util.UUID
import scala.util.Try

class SecureDtoTest extends AnyFlatSpec {

  behavior of "SecureDto"

  val salt = "blabla"
  case class Abc(i: Int, s: String, u: UUID, r: Role, salt: String = salt) extends Salted

  it should "ok serializing deserializing salted object" in {
    val uuid = UUID.randomUUID()
    val role = Role.org
    val i    = 1
    val s    = "a"
    val x    = Abc(i, s, uuid, role)
    val y    = x.asJson.noSpaces
    val z    = decode[Abc](y).toOption.get
    assert(z.i === i)
    assert(z.u === uuid)
    assert(z.salt === salt)
  }
}
