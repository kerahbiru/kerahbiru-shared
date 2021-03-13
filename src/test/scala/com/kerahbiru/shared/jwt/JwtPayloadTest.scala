package com.kerahbiru.shared.jwt

import com.kerahbiru.shared.jwt.Country.ID
import io.circe.parser.decode
import javafx.concurrent.Worker
import org.scalatest.flatspec.AnyFlatSpec

import java.util.UUID

class JwtPayloadTest extends AnyFlatSpec {

  behavior of "JwtPayload"

  val uuid = UUID.randomUUID()
  def json(uuidString: String, role: String, country: String) =
    s"""
      |{
      |  "iss": "https://www.kerahbiru.com",
      |  "iat": "1614903762",
      |  "exp": "1835782484",
      |  "sub": "$uuidString",
      |  "alias": "mbcu",
      |  "role": "$role",
      |  "country": "$country"
      |}
      |""".stripMargin

  it should "ok decoding with correctly formatted json" in {
    val x = decode[JwtPayload](json(uuid.toString, Role.worker.entryName, ID.entryName))
    assert(x.isRight)
    x.map(_.sub === uuid)
  }

  it should "ko decoding with unknown role" in {
    val x = decode[JwtPayload](json(uuid.toString, "zyx", ID.entryName))
    assert(x.isLeft)
  }

  it should "ko decoding with unknown country" in {
    val x = decode[JwtPayload](json(uuid.toString, Role.worker.entryName, "XX"))
    assert(x.isLeft)
  }

}
