# Kerahbiru Shared 

Sharable components.
- events
- jwt
- format

## Format
### UiResponse
Response format for UI.
``` 
{
  isError: boolean,
  error: string | null,
  data: List[A]
}

```
- `ok` : ok with data type A 
- `error` : error with error message
- `just` : ok with a single string message

## Event processing
- Producer should create an event from its subclass (OtpToEmailRequested, etc)
- Consumer should decode the json to Event. 
- Using EventName the data in Event should be decoded to the corresponding data type

## Events
|Name|Aggregate|What|
|---|---|-------|
|OrgCreated|org|organization creation|
|OrgUpdated|org|organization info update|
|OtpToEmailRequested|authenticate (unused)|authentication requesting OTP to email |
|OtpToSmsRequested|authenticate (unused)|authentication requesting OTP to sms |