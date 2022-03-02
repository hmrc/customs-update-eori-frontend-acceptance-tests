# customs-update-eori-acceptance-test

This service updates an EORI and corresponding enrolment.

## Development

You'll need [Service Manager](https://github.com/hmrc/service-manager) to run the tests locally.

#### Service Manager Commands

To check what's running:

    sm -s

Start the required development services (make sure your service-manager-config folder is up to date)

    sm --start CUSTOMS_UPDATE_EORI -r

Stop all running services

    sm --stop CUSTOMS_UPDATE_EORI

#### Running `customs-update-eori` locally
    1. Go to customs-update-eori project in your intellij : https://github.com/hmrc/customs-update-eori
    2. launch `customs-update-eori` via sbt using `sbt run` command
    3. navigate to `http://localhost:9000/customs-update-eori` to make sure the setup is working fine
    4. enter any String for `PID` field and enter `update-enrolment-eori` in `Roles` text-box

To run the tests locally
`sbt -Dbrowser=chrome -Denvironment=local test`

#### Set up enrolments using stub:

curl --location --request POST 'http://localhost:9595/enrolment-store-stub/data' \
--header 'Content-Type: application/json' \
--data-raw '{
 "groupId": "90ccf333-65d2-4bf2-a008-abc23783",
 "affinityGroup": "Organisation",
 "users": [
  {
   "credId": "0012236665",
   "name": "Default User",
   "email": "default@example.com",
   "credentialRole": "Admin",
   "description": "User Description"
  }
 ],
 "enrolments": [
  {
   "serviceName": "HMRC-CUS-ORG",
   "identifiers": [
    {
     "key": "EORINumber",
     "value": "GB123456789000"
    }
   ],
   "enrolmentFriendlyName": "Customs Enrolment",
   "assignedUserCreds": [
    "0012236665"
   ],
   "state": "Activated",
   "enrolmentType": "principal",
   "assignedToAll": false
  }
 ]
}`

#### Set up known facts using stub:

`curl --location --request POST 'http://localhost:9595/enrolment-store-stub/known-facts' \
--header 'Content-Type: application/json' \
--data-raw '{
  "service": "HMRC-CUS-ORG",
  "knownFacts": [
    {
      "key": "EORINumber",
      "value": "GB123456789000",
      "kfType": "identifier"
    },
    {
    "key": "DateOfEstablishment",
    "value": "03/11/1997",
    "kfType": "verifier"
    }
  ]
}`

#### Note: `The EORI Number should be same in all the bodies and unique for resetting data`

#### Jenkins job curl to stub data in development:

`Url- https://orchestrator.tools.development.tax.service.gov.uk/job/curl-microservice/`
