package com.kerahbiru.shared.event

import enumeratum._

sealed trait EventName extends EnumEntry

object EventName extends Enum[EventName] with CirceEnum[EventName] {

  val values = findValues

  // org
  case object OrgCreated extends EventName
  case object OrgUpdated extends EventName

  // authenticate
  case object OtpToEmailRequested extends EventName
  case object OtpToSmsRequested   extends EventName
}
