/*
 * Copyright 2020 HM Revenue & Customs
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

package unit.config

import org.scalatest.TryValues

import cats.syntax.either._
import org.scalatestplus.play.PlaySpec
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import uk.gov.hmrc.accessibilitystatementfrontend.config.AppConfig
import uk.gov.hmrc.accessibilitystatementfrontend.parsers.{AccessibilityStatementParser, AccessibilityStatementsParser}

import scala.util.Try

class ServicesSpec extends PlaySpec with GuiceOneAppPerSuite with TryValues {
  private val statementsParser = new AccessibilityStatementsParser
  private val statementParser  = new AccessibilityStatementParser
  private val appConfig        = app.injector.instanceOf[AppConfig]

  private val services: Seq[String] =
    statementsParser.parseFromSource(appConfig.statementsSource).valueOr(throw _).services

  "the services yaml file" should {
    "exist" in {
      val sourceTry = Try(appConfig.statementsSource)

      sourceTry must be a 'success
    }
  }

  "the configuration files" should {
    services.foreach { (service: String) =>
      s"include $service's accessibility statement yaml file" in {
        val sourceTry = Try(appConfig.statementSource(service))

        sourceTry must be a 'success
      }
    }
  }

  "the configuration files" should {
    services.foreach { (service: String) =>
      s"include a correctly formatted accessibility statement yaml file for $service" in {
        val source = appConfig.statementSource(service)

        statementParser.parseFromSource(source).valueOr(throw _)
      }
    }
  }
}
