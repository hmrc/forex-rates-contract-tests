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

package uk.gov.hmrc.test.api.specs

import org.scalatest.Assertion

import scala.xml.{Node, XML}

class GetRatesSpec extends BaseSpec {

  Feature("Retrieving Forex Rates") {

    Scenario("Call the ECB endpoint for today") {

      When("The RSS feed has been called today")

      val response =
        ecbForexService.getEcbForexRssFeed

      Then("The rates are returned in the correct format")

      val loadedXml = XML.loadString(response.body)

      val listOfItems = loadedXml \\ "RDF" \\ "item"

      listOfItems.size shouldBe 5

      listOfItems.foreach(singleItem => checkForSingleExchangeRate(singleItem))

    }

  }

  def checkForSingleExchangeRate(baseElem: Node): Assertion = {
    (baseElem \\ "date").exists(x => x.text.nonEmpty) shouldBe true

    val exchangeRateElem = baseElem \\ "statistics" \\ "exchangeRate"

    (exchangeRateElem \\ "value").exists(x => x.text.nonEmpty)          shouldBe true
    (exchangeRateElem \\ "baseCurrency").exists(x => x.text.nonEmpty)   shouldBe true
    (exchangeRateElem \\ "targetCurrency").exists(x => x.text.nonEmpty) shouldBe true

  }
}
