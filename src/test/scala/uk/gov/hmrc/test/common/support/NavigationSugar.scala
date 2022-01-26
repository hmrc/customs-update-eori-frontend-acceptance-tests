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

package uk.gov.hmrc.test.common.support

import play.api.Logger
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest._
import org.scalatest.concurrent.{Eventually, IntegrationPatience}
import org.scalatest.selenium.WebBrowser
import uk.gov.hmrc.test.common.pages.WebPage

trait NavigationSugar extends WebBrowser with Eventually with Assertions with MustMatchers with IntegrationPatience {

  def on(page: WebPage)(implicit webDriver: WebDriver): Unit = {
    new WebDriverWait(webDriver, 20).until(ExpectedConditions.urlContains(page.url))
    Logger("NavigationSugar").info(page.url)
    assert(page.isCurrentPage, s"Page title: '$pageTitle' not as expected")
  }
}
