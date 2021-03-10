package com.kerahbiru.shared.jwt

import io.circe.parser.decode
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class JwtPayloadTest extends AnyFlatSpec{

  behavior of "JwtPayload"

  val uuid = UUID.randomUUID()
  def json(uuidString: String, role: String) =
    s"""
      |{
      |  "iss": "https://www.kerahbiru.com",
      |  "iat": "1614903762",
      |  "exp": "1835782484",
      |  "sub": "$uuidString",
      |  "alias": "mbcu",
      |  "role": "$role",
      |  "plan": "free"
      |}
      |""".stripMargin


  it should "ok decoding with correctly formatted json" in{
    val x = decode[JwtPayload](json(uuid.toString, "worker"))
    assert(x.isRight)
    x.map(_.sub === uuid)
  }

  it should "ko decoding with unknown role" in{
    val x = decode[JwtPayload](json(uuid.toString, "zyx"))
    assert(x.isLeft)
  }



}
