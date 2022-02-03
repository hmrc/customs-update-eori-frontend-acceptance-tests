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

import org.openqa.selenium.{UnexpectedAlertBehaviour, WebDriver}
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.{CapabilityType, DesiredCapabilities}
import uk.gov.hmrc.webdriver.SingletonDriver

object Driver {

  Option(System.getProperty("browser")) match {
    case Some("remote-chrome") => {
      val options = new ChromeOptions
      val capabilities = DesiredCapabilities.chrome();
      capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.IGNORE)
      capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true)
      capabilities.setCapability(ChromeOptions.CAPABILITY, options)
    }
    case _ => System.setProperty("browser", "chrome")
  }

  val instance: WebDriver = chromeDriver()

  def chromeDriver(): WebDriver = {
    val driver = SingletonDriver.getInstance()
    driver
  }
  sys.addShutdownHook(SingletonDriver.closeInstance())
}
