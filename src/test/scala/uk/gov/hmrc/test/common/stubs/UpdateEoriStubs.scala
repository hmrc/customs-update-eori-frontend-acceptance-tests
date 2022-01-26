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

package uk.gov.hmrc.test.common.stubs

import org.scalatest.Matchers.convertToAnyShouldWrapper
import play.api.test.Helpers._
import uk.gov.hmrc.test.common.support.TestConfiguration.testConfig

object UpdateEoriStubs extends TestClient {

  def insertEnrolment() {
    val req: String =
      """
        |{
        |	"groupId": "90ccf333-65d2-4bf2-a008-efg34566",
        |	"affinityGroup": "Organisation",
        |	"users": [
        |		{
        |			"credId": "0012236669",
        |			"name": "Default User",
        |			"email": "adminone@example.com",
        |			"credentialRole": "Admin",
        |			"description": "User Description"
        |		}
        |	],
        |	"enrolments": [
        |		{
        |			"serviceName": "HMRC-CUS-ORG",
        |			"identifiers": [
        |				{
        |					"key": "EORINumber",
        |					"value": "GB333456788899"
        |				}
        |			],
        |			"enrolmentFriendlyName": "Customs Enrolment",
        |			"assignedUserCreds": [
        |				"0012236669"
        |			],
        |			"state": "Activated",
        |			"enrolmentType": "principal",
        |			"assignedToAll": false
        |		}
        |	]
        |}
      """.stripMargin
    val result = postRequest(s"${testConfig.enrolmentStoreStubUrl}/data", req, CONTENT_TYPE -> validHeader)
    result.code shouldBe NO_CONTENT
  }

  def insertKnownFacts() {
    val req: String =
      """
        |{
        |    "service": "HMRC-CUS-ORG",
        |    "knownFacts": [
        |        {
        |            "key": "EORINumber",
        |            "value": "GB333456788899",
        |            "kfType": "identifier"
        |        },
        |        {
        |            "key": "DateOfEstablishment",
        |            "value": "08/07/1990",
        |            "kfType": "verifier"
        |        }
        |    ]
        |}
      """.stripMargin
      val result = postRequest(s"${testConfig.enrolmentStoreStubUrl}/known-facts", req, CONTENT_TYPE -> validHeader)
      result.code shouldBe CREATED
    }

  def deleteEnrolmentsData() {
    val result = deleteRequest(s"${testConfig.enrolmentStoreStubUrl}/data")
    result.code shouldBe NO_CONTENT
  }

  def deleteEnrolmentKnownFacts() {
    val result = deleteRequest(s"${testConfig.enrolmentStoreStubUrl}/known-facts")
    result.code shouldBe NO_CONTENT
  }
}
