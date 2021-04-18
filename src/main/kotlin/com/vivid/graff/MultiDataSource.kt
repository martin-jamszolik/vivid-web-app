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

package com.vivid.graff

import com.vivid.graff.security.User
import org.springframework.aop.TargetSource
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import javax.sql.DataSource
import kotlin.collections.LinkedHashMap


class MultiDataSource : TargetSource, ApplicationListener<ContextRefreshedEvent> {

    private val logger by logger()
    private val dataSourceMap = LinkedHashMap<String, DataSource>()
    private var defaultDs: Optional<DataSource> = Optional.empty()

    fun getSourceMap(): MutableMap<String, DataSource> = this.dataSourceMap

    fun default(ds:DataSource) {
        this.defaultDs = Optional.of(ds)
    }

    fun addDS(key: String, ds: DataSource) {
        dataSourceMap[key] = ds
    }

    @Throws(Exception::class)
    override fun getTarget(): Any? {

        val principal = SecurityContextHolder.getContext()?.authentication?.principal

        if (principal is User) {
            if (dataSourceMap.containsKey(principal.client)) {
                return dataSourceMap[principal.client]
            } else {
                throw BadCredentialsException("Data source is not configured for ${principal.client} database.")

            }
        }
        if( logger.isWarnEnabled ){
            logger.warn("tenant database not found. Trying default if exists")
        }
        return defaultDs.orElseGet { null }
    }


    override fun getTargetClass(): Class<DataSource> {
        return DataSource::class.java
    }

    override fun isStatic(): Boolean {
        return false
    }

    @Throws(Exception::class)
    override fun releaseTarget(arg0: Any) {
        // Nothing to do
    }

    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        logger.info("Context Event for MultiDataSource occurred event: {}", event.toString())
    }
}

