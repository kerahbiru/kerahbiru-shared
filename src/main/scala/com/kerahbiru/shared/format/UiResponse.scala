package com.kerahbiru.shared.format

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

final case class UiResponse[A](isError: Boolean, error: Option[String], data: A)

object UiResponse {

  def ok[A](data: A): UiResponse[A] = UiResponse(isError = false, None, data)

  def error[A](error: String): UiResponse[List[A]] =
    UiResponse[List[A]](isError = true, Some(error), List.empty[A])

  implicit def encoder[T: Encoder]: Encoder[UiResponse[T]] = deriveEncoder

  def just(message: String): UiResponse[List[OneMessage]] =
    UiResponse.ok[List[OneMessage]](List(new OneMessage(message)))
}

final case class OneMessage(message: String)
object OneMessage {

  implicit val enc: Encoder[OneMessage] = deriveEncoder

  //  val z = UiResponse[List[OneMessage]](List(OneMessage("success"))).asJson.noSpaces

}
