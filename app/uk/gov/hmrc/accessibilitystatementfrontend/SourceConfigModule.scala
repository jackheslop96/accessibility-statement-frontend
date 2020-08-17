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

package uk.gov.hmrc.accessibilitystatementfrontend

import com.google.inject.AbstractModule
import uk.gov.hmrc.accessibilitystatementfrontend.config.{ProductionSourceConfig, SourceConfig, TestOnlySourceConfig}

class SourceConfigModule extends AbstractModule {
  override def configure() =
    bind(classOf[SourceConfig]).to(classOf[ProductionSourceConfig])
}

class TestOnlySourceConfigModule extends AbstractModule {
  override def configure() =
    bind(classOf[SourceConfig]).to(classOf[TestOnlySourceConfig])
}