package com.kerahbiru.shared.event

import com.kerahbiru.shared.aggmeta.AggregateName

trait Meta {

  val aggregateName: AggregateName

  val eventName: EventName
}
