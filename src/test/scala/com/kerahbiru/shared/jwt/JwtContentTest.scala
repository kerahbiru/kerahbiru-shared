package com.kerahbiru.shared.jwt

import monix.eval.Task
import org.scalatest.flatspec.AsyncFlatSpec

import java.util.UUID

class JwtContentTest extends AsyncFlatSpec {
//  import monix.execution.Scheduler.Implicits.global
//  implicit override def executionContext = scala.concurrent.ExecutionContext.Implicits.global
//
//  behavior of "Token"
//
//  it should "ok creating new string token" in {
//    val sig               = "abc"
//    def signer(s: String) = Task(sig)
//    val x = JwtPayload(
//      iat = System.currentTimeMillis() / 1000,
//      exp = System.currentTimeMillis() / 1000 + 1000,
//      sub = UUID.randomUUID(),
//      alias = UUID.randomUUID().toString,
//      role = Role.org,
//      country = Country.ID
//    )
//    JwtContent
//      .create(signer, x)
//      .runToFuture
//      .map(p => assert(p.contains(sig)))
//  }

}
