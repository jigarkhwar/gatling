/*
 * Copyright 2011-2020 GatlingCorp (https://gatling.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.gatling.http.check.body

import io.gatling.core.check.{ CheckMaterializer, Preparer }
import io.gatling.core.json.JsonParsers
import io.gatling.http.check.{ HttpCheck, HttpCheckMaterializer }
import io.gatling.http.check.HttpCheckScope.Body
import io.gatling.http.response.Response
import io.gatling.core.check.jmespath.JmesPathCheckType

import com.fasterxml.jackson.databind.JsonNode

object HttpBodyJmesPathCheckMaterializer {

  def instance(jsonParsers: JsonParsers): CheckMaterializer[JmesPathCheckType, HttpCheck, Response, JsonNode] = {
    val preparer: Preparer[Response, JsonNode] = response => jsonParsers.safeParse(response.body.stream, response.body.charset)

    new HttpCheckMaterializer[JmesPathCheckType, JsonNode](Body, preparer)
  }
}
