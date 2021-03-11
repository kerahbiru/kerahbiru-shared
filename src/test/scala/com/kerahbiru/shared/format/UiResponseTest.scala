package com.kerahbiru.shared.format

import org.scalatest.flatspec.AnyFlatSpec
import io.circe.syntax._

class ResponseTest extends AnyFlatSpec {

  behavior of "Response"

  it should "ok encoding with any A" in {
    val data = List("hello")

    val x = UiResponse.ok[List[String]](data).asJson.noSpaces
    assert(x.contains("hello"))
  }

  it should "ok encoding error" in {
    val x = UiResponse.error[List[String]]("something bad").asJson.noSpaces
    assert(x.contains("bad"))
  }

  it should "ok encoding OneMesage" in {
    val x = UiResponse.just("success").asJson.noSpaces
    assert(x.contains("success"))
  }

  it should "ok encoding error with OneMessage type" in {
    val x = UiResponse.error1M("something bad").asJson.noSpaces
    assert(x.contains("bad"))
  }

}