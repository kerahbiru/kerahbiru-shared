package com.kerahbiru.shared.event

import io.circe.syntax.EncoderOps

import java.time.{OffsetDateTime, ZoneOffset}
import java.util.UUID

case class Event(
    id: UUID,
    version: Int,
    ts: OffsetDateTime,
    user: UUID,
    aggregate: String,
    name: EventName,
    data: String
)

object Event {

  def nowUtc: OffsetDateTime = OffsetDateTime.now(ZoneOffset.UTC)

  def apply(id: UUID, version: Int, user: UUID, eventData: EventData): Event =
    eventData match {
      case data: OrgCreatedData =>
        Event(id, version, nowUtc, user, OrgCreatedData.aggregateName, EventName.OrgCreated, data.asJson.noSpaces)
      case data: OrgUpdatedData =>
        Event(id, version, nowUtc, user, OrgUpdatedData.aggregateName, EventName.OrgUpdated, data.asJson.noSpaces)

      case data: OtpToEmailRequestedData =>
        Event(
          id,
          version,
          nowUtc,
          user,
          OtpToEmailRequestedData.aggregateName,
          EventName.OtpToEmailRequested,
          data.asJson.noSpaces
        )

      case data: OtpToSmsRequestedData =>
        Event(
          id,
          version,
          nowUtc,
          user,
          OtpToSmsRequestedData.aggregateName,
          EventName.OtpToSmsRequested,
          data.asJson.noSpaces
        )

    }
}
