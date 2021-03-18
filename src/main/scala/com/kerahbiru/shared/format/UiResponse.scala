package com.kerahbiru.shared.format

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

final case class UiResponse[A](isError: Boolean, error: Option[String], data: A)

object UiResponse {

  def ok[A](data: A): UiResponse[A] = UiResponse(isError = false, None, data)

//  def error[A](error: String): UiResponse[List[A]] =
//    UiResponse[List[A]](isError = true, Some(error), List.empty[A])

  def error(error: String): UiResponse[Empty] = UiResponse[Empty](isError = true, Some(error), Empty())

  def just(message: String): UiResponse[OneMessage] = UiResponse.ok[OneMessage](new OneMessage(message))

  implicit def encoder[T: Encoder]: Encoder[UiResponse[T]] = deriveEncoder

}

final case class Empty()

object Empty {

  implicit val enc: Encoder[Empty] = deriveEncoder

}

final case class OneMessage(message: String)
object OneMessage {

  implicit val enc: Encoder[OneMessage] = deriveEncoder

}
