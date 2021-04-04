package com.kerahbiru.shared.format

import io.circe.Encoder
import io.circe.generic.auto._

final case class UiResponse[A](isError: Boolean, isEncrypted: Boolean, error: Option[String], data: A)

object UiResponse {

  def ok[A](data: A): UiResponse[A] = UiResponse(isError = false, isEncrypted = false, None, data)

  def ok[A](data: A, isEncrypted: Boolean): UiResponse[A] = UiResponse(isError = false, isEncrypted = true, None, data)

  def error(error: String): UiResponse[Empty] =
    UiResponse[Empty](isError = true, isEncrypted = false, Some(error), Empty())

  def just(message: String): UiResponse[OneMessage] = UiResponse.ok[OneMessage](new OneMessage(message))

//  implicit def encoder[T: Encoder]: Encoder[UiResponse[T]] = deriveEncoder

  abstract class Salted {
    val salt: String
  }

}

final case class Empty()
//
//object Empty {
//
//  implicit val enc: Encoder[Empty] = deriveEncoder
//
//}

final case class OneMessage(message: String)
//object OneMessage {
//
//  implicit val enc: Encoder[OneMessage] = deriveEncoder
//
//}
