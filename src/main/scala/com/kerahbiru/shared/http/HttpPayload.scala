package com.kerahbiru.shared.http

import com.kerahbiru.shared.jwt.JwtContent

final case class HttpPayload(
    path: String,
    pathParameters: Map[String, String],
    body: String,
    token: Option[JwtContent]
)

object HttpPayload {

  def apply(path: String, pathParameters: Map[String, String], body: String, tokenHeader: Option[String]): HttpPayload =
    HttpPayload(path, pathParameters, body, tokenHeader.flatMap(JwtContent.parseToken))

}

sealed trait AccessLevel
object open   extends AccessLevel
object secure extends AccessLevel
