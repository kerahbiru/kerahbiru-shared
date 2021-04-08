package com.kerahbiru.shared.jwt

import io.circe.parser.decode
import org.scalatest.flatspec.AnyFlatSpec

class JwtHeaderTest extends AnyFlatSpec {

  behavior of "JwtHeader"

  it should "ok decoding with the only possible json" in {
    val x =
      """
        |{
        |"alg": "RS256",
        |"typ": "JWT"
        |}
        |""".stripMargin

    val z = decode[JwtHeader](x)
    assert(z.isRight)
    z.map(_.alg === Alg.RS256)
    z.map(_.typ === Typ.JWT)
  }

  it should "ko decoding with any other input" in {
    val x =
      """
        |{
        |"alg": "HS384",
        |"typ": "JWT"
        |}
        |""".stripMargin

    val z = decode[JwtHeader](x)
    assert(z.isLeft)
  }

}
