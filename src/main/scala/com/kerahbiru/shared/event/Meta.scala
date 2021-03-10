package com.kerahbiru.shared.event

trait Meta {

  def aggregateName: String

  def eventName: EventName
}
