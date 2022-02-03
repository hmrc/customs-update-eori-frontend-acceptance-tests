/*
 * Copyright 2022 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.acceptance

import org.scalatest.concurrent.Eventually
import org.scalatest._
import uk.gov.hmrc.webdriver.SingletonDriver
import org.scalatest.selenium.WebBrowser
import uk.gov.hmrc.test.common.stubs.UpdateEoriStubs
import uk.gov.hmrc.test.common.support.{StartUpTearDown}

import scala.util.Try

trait BaseSpec
  extends FeatureSpec
    with GivenWhenThen
    with BeforeAndAfterAll
    with Matchers
    with WebBrowser
    with Eventually
    with StartUpTearDown {

  override def beforeAll() {
    UpdateEoriStubs.insertEnrolment
    UpdateEoriStubs.insertKnownFacts
  }

  override def afterAll() {
    Try(SingletonDriver.closeInstance)
    UpdateEoriStubs.deleteEnrolmentsData
    UpdateEoriStubs.deleteEnrolmentKnownFacts
  }

  override def withFixture(test: NoArgTest): Outcome = {
    val fixture = super.withFixture(test)
    if (!fixture.isSucceeded) {
      val screenshotName = test.name.replaceAll(" ", "_").replaceAll(":", "") + ".png"
      setCaptureDir("./target/test-reports/html-report/screenshots/")
      capture to screenshotName
      markup(s"<img src='screenshots/$screenshotName' />")
    }
    fixture
  }
}
