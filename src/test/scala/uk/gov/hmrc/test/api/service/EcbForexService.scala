/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.api.service

import play.api.libs.ws.StandaloneWSRequest
import uk.gov.hmrc.test.api.client.HttpClient
import uk.gov.hmrc.test.api.conf.TestConfiguration

import scala.concurrent.Await
import scala.concurrent.duration._

class EcbForexService extends HttpClient {
  private val ecbHost: String       = TestConfiguration.url("ecb-forex")
  private val ecbRssFeedURL: String = s"$ecbHost/rss/fxref-gbp.html"

  def getEcbForexRssFeed =
    Await.result(
      get(ecbRssFeedURL),
      10.seconds
    )
}
