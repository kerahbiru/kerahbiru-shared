package com.kerahbiru.shared.event

trait Meta {

  val aggregateName: String

  val eventName: EventName
}
