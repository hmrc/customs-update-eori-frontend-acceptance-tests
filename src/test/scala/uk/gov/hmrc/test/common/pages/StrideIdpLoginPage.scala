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

object StrideIdpLoginPage extends WebPage {

  override val url: String = testConfig.strideIdpStubUrl

  override val title = "Stride IdP Login"

  def pidField: TextField = textField("pid")

  def rolesField: TextArea = textArea("roles")

  def submitButton: Element = find(id("continue-button")).get

  def loginToCustomsUpdateEoriPage() {
    pidField.value = "123456"
    rolesField.value = "update-enrolment-eori"
    click on submitButton
  }

}