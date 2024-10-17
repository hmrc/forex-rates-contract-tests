# forex-rates-contract-tests
API test suite for the `forex-rates` service using ScalaTest and [play-ws](https://github.com/playframework/play-ws) client.  

## Running the tests

Scheduled job on QA:

These tests were developed to run against QA once a week on a Monday at 6.30am to check that the RSS feed is still 
returning XML in the expected format. This is configured via the build-jobs repo.

Ad-hoc running on QA:

This test can also be ran via the forex-rates-contract-tests job in jenkins on an ad-hoc basis.

If this needs to be tested locally, please use the following steps:

Service Manager:
```
sm --start FOREX_RATES_ALL -r
```

Execute the `run_tests.sh` script:

`./run_tests.sh <environment>`

The tests default to the `local` environment.  For a complete list of supported param values, see:
- `src/test/resources/application.conf` for **environment**

#### Running the tests against a test environment

To run the tests against an environment set the corresponding `host` environment property as specified under
`<env>.host.services` in the [application.conf](src/test/resources/application.conf).

### Scalafmt
This repository uses [Scalafmt](https://scalameta.org/scalafmt/), a code formatter for Scala. The formatting rules configured for this repository are defined within [.scalafmt.conf](.scalafmt.conf).

To apply formatting to this repository using the configured rules in [.scalafmt.conf](.scalafmt.conf) execute:

 ```
 sbt scalafmtAll scalafmtSbt
 ```

To check files have been formatted as expected execute:

 ```
 sbt scalafmtCheckAll scalafmtSbtCheck
 ```

[Visit the official Scalafmt documentation to view a complete list of tasks which can be run.](https://scalameta.org/scalafmt/docs/installation.html#task-keys)