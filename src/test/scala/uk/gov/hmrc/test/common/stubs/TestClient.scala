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

import scalaj.http.{Http, HttpResponse}

trait TestClient {

  val validHeader = "application/json"

  def getRequest(url: String, headers: (String, String)*) : HttpResponse[String] = {
    val request = Http(url).headers(headers)
      .timeout(connTimeoutMs = 5000, readTimeoutMs = 5000).asString
    request
  }

  def postRequest(url: String, payload: String, headers: (String, String)*): HttpResponse[String] = {
    val request = Http(url).headers(headers).postData(payload)
      .timeout(connTimeoutMs = 5000, readTimeoutMs = 5000).asString
    request
  }

  def putRequest(url: String, payload: String, headers: (String, String)*): HttpResponse[String] = {
    val request = Http(url).headers(headers).postData(payload).method("PUT")
      .timeout(connTimeoutMs = 5000, readTimeoutMs = 5000)
      .asString
    request
  }

  def deleteRequest(url: String): HttpResponse[String] = {
    val request = Http(url).method("DELETE")
      .timeout(connTimeoutMs = 5000, readTimeoutMs = 5000)
      .asString
    request
  }
}
