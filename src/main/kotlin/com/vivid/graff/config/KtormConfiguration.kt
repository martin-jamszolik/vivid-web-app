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

import org.ktorm.database.Database
import org.ktorm.jackson.KtormModule
import org.ktorm.logging.Slf4jLoggerAdapter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class KtormConfiguration(val dataSource: DataSource) {

    @Bean
    fun database(): Database {
        return Database.connectWithSpringSupport(dataSource,
            logger = Slf4jLoggerAdapter( "org.ktorm" ))
    }

    /**
     * Register Ktorm's Jackson extension to the Spring's container
     * so that we can serialize/deserialize Ktorm entities.
     */
    @Bean
    fun ktormModule(): KtormModule {
        return KtormModule()
    }

}