package com.kerahbiru.shared.jwt

import io.circe.parser.decode
import io.circe.syntax.EncoderOps

import java.nio.charset.StandardCharsets
import java.util.Base64
import scala.scalajs.js.typedarray.{Int8Array, byteArray2Int8Array}
import scala.util.{Failure, Success, Try}

case class JwtContent(jwtHeader: JwtHeader, jwtPayload: JwtPayload, msg: String, sig: Int8Array)

object JwtContent {

  private def decodeBase64(encoded: String) = new String(Base64.getUrlDecoder.decode(encoded), StandardCharsets.UTF_8)

  def parseToken(s: String): Either[Throwable, JwtContent] =
    Try {
      val x = s.split("[.]")
      if (x.size != 3) throw new Exception() //Scalajs doesn't catch fatal exceptions
      val msg        = s"${x(0)}.${x(1)}"
      val sig        = byteArray2Int8Array(Base64.getDecoder.decode(x(2)))
      val jwtHeader  = decode[JwtHeader](decodeBase64(x(0))).toOption.get
      val jwtPayload = decode[JwtPayload](decodeBase64(x(1))).toOption.get
      JwtContent(jwtHeader, jwtPayload, msg, sig)
    } match {
      case Success(value) => Right(value)
      case Failure(e)     => Left(e)
    }

  def encodeJwtHeaderPayload(jwtPayload: JwtPayload, jwtHeader: JwtHeader): String =
    Seq(jwtHeader.asJson.noSpaces, jwtPayload.asJson.noSpaces)
      .map(_.getBytes(StandardCharsets.UTF_8))
      .map(Base64.getUrlEncoder.withoutPadding().encodeToString)
      .mkString(".")
}
