package com.kerahbiru.shared.jwt

import scala.scalajs.js.typedarray.Int8Array

case class JwtContent(jwtHeader: JwtHeader, jwtPayload: JwtPayload, msg: String, sig: Int8Array)
