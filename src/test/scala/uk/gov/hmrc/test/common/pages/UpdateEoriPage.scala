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

import uk.gov.hmrc.test.common.support.TestConfiguration.testConfig

object UpdateEoriPage extends WebPage {

  override val url: String = s"${testConfig.customsUpdateEoriUrl}/customs-update-eori"

  override val title = "Update EORI"

  def existingEoriField: TextField = textField("existing-eori")

  def dateOfEstablishmentField: TextField = textField("date-of-establishment.day")

  def monthOfEstablishmentField: TextField = textField("date-of-establishment.month")

  def yearOfEstablishmentField: TextField = textField("date-of-establishment.year")

  def newEoriField: TextField = textField("new-eori")

  def updateStatusId: String = find(id("update-status")).get.text

  def submitRequestToUpdateEori() {
    existingEoriField.value = "GB333456788899"
    dateOfEstablishmentField.value = "08"
    monthOfEstablishmentField.value = "07"
    yearOfEstablishmentField.value = "1990"
    newEoriField.value = "GB12345684584545"
    submit()
  }

  def submitRequestToUpdateEoriTwo(existingEori:String, dateOfEstablishment:String, monthOfEstablishment:String, yearOfEstablishment:String, newEori:String) {
    existingEoriField.value = existingEori
    dateOfEstablishmentField.value = dateOfEstablishment
    monthOfEstablishmentField.value = monthOfEstablishment
    yearOfEstablishmentField.value = yearOfEstablishment
    newEoriField.value = newEori
    submit()
  }

}
