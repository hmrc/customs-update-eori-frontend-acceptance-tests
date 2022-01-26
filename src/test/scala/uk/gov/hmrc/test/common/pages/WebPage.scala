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

package uk.gov.hmrc.test.common.pages

import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.scalatest.MustMatchers
import org.scalatest.selenium.{Page, WebBrowser}
import uk.gov.hmrc.test.common.support.{NavigationSugar, StartUpTearDown}

trait WebPage extends Page with WebBrowser with MustMatchers with NavigationSugar with StartUpTearDown {

  private lazy val webdriverWait = new WebDriverWait(webDriver, 30)

  val title: String

  def isCurrentPage: Boolean = pageTitle startsWith title

  def goToPage(): Unit = {
    go to url
  }

  def getCurrentUrl() = webDriver.getCurrentUrl

  def elementText(selector: String): String = {
    try {
      find(cssSelector(selector)).get.text.trim
    } catch {
      case _: NoSuchElementException => fail(s"Selector $selector not found in page")
    }
  }

  protected def findElementById(id: String)(implicit webDriver: WebDriver): WebElement = {
    webDriver.findElement(By.id(id))
  }

  def enterValue(by: By, value: String): Unit = webDriver.findElement(by).sendKeys(value)

  def find(by: By): WebElement = webDriver.findElement(by)

  def clickOn(by: By): Unit = webDriver.findElement(by).click()

  def textFromElement(by: By): String = webDriver.findElement(by).getText

  def findElementByLabel(label: String): Option[Element] =
    find(xpath(s"//input[@id=(//label[contains(., '$label')]/@for)]"))

  def findElementById(id: String): Option[Element] = find(xpath(s"//*[@id='$id']"))

  def fillTextField(fieldName: String, value: String): Unit = textField(fieldName).value = value

  def selectRadioButtonWithLabel(label: String): Unit = findElementByLabel(label).get.underlying.click()

  def findLinkByLabel(label: String): WebElement = find(linkText(label)).get.underlying

  def elementValue(element: WebElement): String = element.getAttribute("value")

  def waitForPresenceOfElement(selector: By): WebElement =
    webdriverWait.until(ExpectedConditions.presenceOfElementLocated(selector))

  def waitForVisibilityOfElement(selector: By): WebElement =
    webdriverWait.until(ExpectedConditions.visibilityOfElementLocated(selector))

  def waitForElementToBeClickable(element: WebElement): WebElement =
    webdriverWait.until(ExpectedConditions.elementToBeClickable(element))

  def waitForElementToBeClickable(locator: By): WebElement =
    webdriverWait.until(ExpectedConditions.elementToBeClickable(locator))

}
