# Kerahbiru Shared 


## Circe Json decoding/encoding
Use `import io.circe.generic.auto._` wherever there is json encoding/decoding

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
- `error` : error with data type A (A is unused but useful for coupling with `ok`)
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
|OtpToEmailRequested|otp|authentication requesting OTP to email |
|OtpToSmsRequested|otp|authentication requesting OTP to sms |
|UserSignedIn|authenticate|granting token to user|