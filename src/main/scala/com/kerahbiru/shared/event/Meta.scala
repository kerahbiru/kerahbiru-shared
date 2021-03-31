package com.kerahbiru.shared.event

import com.kerahbiru.shared.aggmeta.AggregateName

trait Meta {

  def aggregateName: AggregateName

  def eventName: EventName
}
