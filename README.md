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