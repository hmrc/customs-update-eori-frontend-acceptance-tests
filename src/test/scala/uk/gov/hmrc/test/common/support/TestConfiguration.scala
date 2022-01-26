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

case class TestConfig(customsUpdateEoriUrl: String,
                      enrolmentStoreStubUrl: String,
                      strideIdpLoginUrl: String)

object TestConfiguration {

  lazy val environment: String = Option(System.getProperty("environment")).getOrElse("dev").toLowerCase

  lazy val testConfig: TestConfig = {
    environment match {
      case "local" =>
        TestConfig(
          customsUpdateEoriUrl        = "http://localhost:9000/customs-update-eori",
          enrolmentStoreStubUrl       = "http://localhost:9595/enrolment-store-stub",
          strideIdpLoginUrl           = "http://localhost:9043/stride-idp-stub/auth-request?" + "SAMLRequest=fZFBT4MwGIbv%2FgrSeykwZNgMFiIuLtmMYeDBW4VvjgRa7FcW%2Ffd2zCXusmPb9%2Fne9uli%2Bd13zhE0tkomxHc94oCsVdPKz4RU5Y"+
            "rGZJneLVD0XTDwbDQHWcDXCGicDBG0sdyjkjj2oHegj20NVbFJyMGYATljnapFd1BoGBrdNsCEHUE14GAhIE5uJ7VSmKn%2BAu3RhV7Uxp65"+
            "teqZaPbIOmTEWecJ2eblKw0bAQKiOX0IwhkNvVlI42bu0%2Fv5B8QiCqImDm0ccYS1RCOkSUjgBQH1fBp4pe%2FxcMbDyLX4O3HeLgKCkwC"+
            "rRCI%2FPzkho5ZcCWyRS9EDclPzXbbdcBvlg1ZG1aoj6dkQnwq1s1K6F%2BY2e9ppG7qfohykac3PVfdtXFzsk7QqXvgkZVcW6%2FyJZ"+
            "lX5vGD%2FL5T%2BLa9%2FMP0F&RelayState=successURL%3Dhttp%253A%252F%252Flocalhost%253A9000%252Fcustoms-update-eori%26failureURL%3D%252Fstride%252Ffailure%253FcontinueURL" +
            "%253Dhttp%2525253A%2525252F%2525252Flocalhost%2525253A9000%2525252Fcustoms-update-eori"
        )

      case "dev" =>
        val devUrl = "https://admin.development.tax.service.gov.uk"
        TestConfig(
          customsUpdateEoriUrl        = s"$devUrl/customs-update-eori",
          enrolmentStoreStubUrl       = s"$devUrl/enrolment-store-stub",
          strideIdpLoginUrl           = s"$devUrl/stride-idp-stub/auth-request?"+"SAMLRequest=fZFPb4JAEMXv%2FRRk78s%2Fq%2BBGMKTU1MQ2DUIPvS0w6qawS3cWY799EWtSLx" +
            "5n5r03k98slqe2sY6gUSgZEc92iQWyUrWQ%2B4gU%2BYqGZBk%2FLJC3jd%2BxpDcHmcF3D2isBBG0GXxPSmLfgt6CPooKimwTkYMxHTLH" +
            "4XUrpG34ycbL1N6ro91%2FOWi0qMHhQyLVgN2QAcRKh2AhuRmvuWbs0D60uro6eb1Dp0GHWOs0Iq9p%2Fk4nUHpBE" +
            "FYUqjCkj8BrWs78klbzcu7y6dTnXjDIEXtYSzRcmoj4ru9T16P%2BLHdDNp2wiWvPAu%2BTWB9XHP4ZxwBIIrsAiEivJVMc" +
            "BTLJW0BmKrZNXjdskLJOK6Mq1ZD4wouNC7W1Urrl5r733BE13Y1SBtII83Oz%2B76dX39B4iJ7YyOUbZ6t02eaFPnLwvl%2FUPxX3v4z%" +
            "2FgU%3D&RelayState=successURL%3D%252Fcustoms-update-eori%26failureURL%3D%252Fstride%252Ffailure%253" +
            "FcontinueURL%253D%2525252Fcustoms-update-eori"
        )

      case "qa" =>
        val qaUrl = "https://www.qa.tax.service.gov.uk"
        TestConfig(
          customsUpdateEoriUrl        = s"$qaUrl/customs-update-eori",
          enrolmentStoreStubUrl       = s"$qaUrl/enrolment-store-stub",
          strideIdpLoginUrl           = s"$qaUrl/stride-idp-stub/auth-request?SAMLRequest=fZFPb4JAEMXv%2FRRk78s%2Fq%2BBGMKTU1MQ2DUIPvS0w6qawS3cW" +
            s"Y799EWtSLx5n5r03k98slqe2sY6gUSgZEc92iQWyUrWQ%2B4gU%2BYqGZBk%2FLJC3jd%2BxpDcHmcF3D2isBBG0GXxPS" +
            s"mLfgt6CPooKimwTkYMxHTLH4XUrpG34ycbL1N6ro91%2FOWi0qMHhQyLVgN2QAcRKh2AhuRmvuWbs0D60uro6eb1Dp0G" +
            s"HWOs0Iq9p%2Fk4nUHpBEFYUqjCkj8BrWs78klbzcu7y6dTnXjDIEXtYSzRcmoj4ru9T16P%2BLHdDNp2wiWvPAu%2BTWB9XHP" +
            s"4ZxwBIIrsAiEivJVMcBTLJW0BmKrZNXjdskLJOK6Mq1ZD4wouNC7W1Urrl5r733BE13Y1SBtII83Oz%2B76dX39B4iJ7YyOUbZ6" +
            s"t02eaFPnLwvl%2FUPxX3v4z%2FgU%3D&RelayState=successURL%3D%252Fcustoms-update-eori%26failureURL%3D%252F" +
            s"stride%252Ffailure%253FcontinueURL%253D%2525252Fcustoms-update-eori"
        )

      case _ => throw new IllegalArgumentException(s"Environment '$environment' not known")
    }
  }
}
