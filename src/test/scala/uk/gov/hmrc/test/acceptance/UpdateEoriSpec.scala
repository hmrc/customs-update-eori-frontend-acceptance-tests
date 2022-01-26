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

import org.scalatest.prop.{TableDrivenPropertyChecks, TableFor7}
import uk.gov.hmrc.test.common.pages.UpdateEoriPage.on
import uk.gov.hmrc.test.common.pages._

class UpdateEoriSpec extends BaseSpec with TableDrivenPropertyChecks {

  val updateEoriScenarioData: TableFor7[String, String, String, String, String, String, String] =

    Table(
      ("Scenario",      "OldEori",        "DateOfEstablishment",    "MonthOfEstablishment", "YearOfEstablishment", "NewEori", "StatusMessage"),
      ("Update EORI(Replace the Old EORI with the New EORI)",   "GB333456788899","08","07","1990","GB12345660005", "Status: Successfully updated: GB333456788899 ==> GB12345660005"),
      ("Verify correct EORI is being updated",  "GB333456788899",  "01",  "02",  "1999",   "GB1234567890003", "Status: Update failed - The date you have entered does not match our records, please try again")
    )

  feature("Update EORI") {

    forAll(updateEoriScenarioData) { (Scenario: String, oldEori:String, dateOfEstablishment: String, monthOfEstablishment: String, yearOfEstablishment: String, newEori:String, statusMessage:String) =>

      scenario(s"$Scenario") {

        Given("a user navigates to the Update EORI page")
        go to StrideIdpLoginPage
        StrideIdpLoginPage.loginToCustomsUpdateEoriPage()
        on(UpdateEoriPage)

        When("the user updates the old EORI number with the new EORI number")
        UpdateEoriPage.submitRequestToUpdateEoriTwo(oldEori, dateOfEstablishment, monthOfEstablishment, yearOfEstablishment, newEori)

        Then("user gets the success or error message displayed on the screen")
        UpdateEoriPage.updateStatusId shouldBe s"$statusMessage"
      }
    }
  }
}