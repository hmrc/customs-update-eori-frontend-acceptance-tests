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

import sbt._

object Dependencies {

  val test = Seq(
    "uk.gov.hmrc"            %% "webdriver-factory"  % "0.25.0"  % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2"   % Test,
    "org.scalaj"             %% "scalaj-http"        % "2.4.2"   % Test,
    "org.pegdown"            % "pegdown"             % "1.6.0"   % Test,
    "org.scalacheck"         %% "scalacheck"         % "1.14.0"  % Test,
    "org.jsoup"              % "jsoup"               % "1.11.3"  % Test,
    "us.codecraft"           % "xsoup"               % "0.3.1"   % Test,
    "org.mockito"            % "mockito-core"        % "3.7.7"   % Test,
    "com.typesafe.play"      %% "play-test"          % "2.8.13"  % Test
  )
}
