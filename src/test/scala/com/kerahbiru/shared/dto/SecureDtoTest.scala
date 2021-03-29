package com.kerahbiru.shared.dto

import io.circe.parser.decode
import io.circe.syntax.EncoderOps
import org.scalatest.flatspec.AnyFlatSpec

class SecureDtoTest extends AnyFlatSpec {

  behavior of "SecureDto"

  val data = "abc"
  it should "ok serializing deserializing" in {
    val x = SecureDto(data)
    val y = x.asJson.noSpaces
    val z = decode[SecureDto](y).toOption.get
    assert(z.cipherText === data)
  }

}
