package com.kerahbiru.shared.aggmeta

import enumeratum._

sealed trait AggregateName extends EnumEntry

object AggregateName extends Enum[AggregateName] with CirceEnum[AggregateName] {

  val values: IndexedSeq[AggregateName] = findValues

  case object org  extends AggregateName
  case object otp  extends AggregateName
  case object user extends AggregateName

}

case class EsTable(aggregateName: AggregateName) {

  def name: String = s"es-${aggregateName.entryName}"
}

case class QueryTable(aggregateName: AggregateName) {
  def name: String = s"query-${aggregateName.entryName}"
}
