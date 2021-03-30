package com.kerahbiru.shared.dto

case class SecureDto(cipherText: String, isSecure: Boolean = true)

object SecureDto {

  def apply(cipherText: String): SecureDto = new SecureDto(cipherText)

  abstract class Salted {
    val salt: String
  }

}
