# Kerahbiru Shared 

Sharable components.
- events
- jwt

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