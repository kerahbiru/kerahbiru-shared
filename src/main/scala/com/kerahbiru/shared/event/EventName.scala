package com.kerahbiru.shared.event

import enumeratum._

sealed trait EventName extends EnumEntry

object EventName extends Enum[EventName] {

  val values = findValues

  case object OrgCreated extends EventName
  case object OrgUpdated extends EventName

}
