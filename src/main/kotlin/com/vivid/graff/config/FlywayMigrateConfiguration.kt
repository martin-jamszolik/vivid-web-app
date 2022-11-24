/*
 * Copyright (c) 2021 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and limitations under the License.
 *
 */

package com.vivid.graff.config

import com.vivid.graff.MultiDataSource
import com.vivid.graff.logger
import jakarta.annotation.PostConstruct
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.Location
import org.flywaydb.core.api.output.MigrateResult
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.core.env.Environment

@Configuration
@Profile("!test")
class FlywayMigrateConfiguration(
    private val env: Environment,
    private val databases: MultiDataSource
) {

    private val logger by logger()

    @PostConstruct
    fun flywayMigration(): Unit {
        logger.info("Flyway migration execution for Env {}",env.activeProfiles)
        val results: List<MigrateResult> = databases.getSourceMap().map { sr ->
            Flyway(Flyway.configure()
                .dataSource(sr.value)
                .baselineVersion("0.1")
                .baselineOnMigrate(true)
                .locations( Location("/db/migration"),
                            Location("/db/schemas/${sr.key}") )
            ).migrate()
        }

        results.forEach {
            logger.info(
                "Schema: {} Migration Version: {}",
                it.schemaName, it.targetSchemaVersion
            )
        }

    }
}
