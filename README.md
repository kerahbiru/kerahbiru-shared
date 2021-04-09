# Kerahbiru Shared 

## Event
|id|version|vhash|iat|user|aggregate|name|data|
|---|---|---|---|---|---|---|---|
|UUID|Int|String|Long|UUID|AggragateName|EventName|String|
|eventId|event version|obfuscated version|ts(second) issuance|user responsible for creation|aggregate name|event name|data in json|

## Circe Json decoding/encoding
Use `import io.circe.generic.auto._` wherever there is json encoding/decoding

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