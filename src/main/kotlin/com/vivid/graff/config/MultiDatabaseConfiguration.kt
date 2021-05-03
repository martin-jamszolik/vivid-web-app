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
import org.springframework.aop.framework.ProxyFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.transaction.TransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource


@Configuration
@EnableTransactionManagement
class MultiDatabaseConfiguration {

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    @Primary
    fun getDataSource(ds: MultiDataSource): DataSource {
        val proxy = ProxyFactoryBean()
        proxy.targetSource = ds
        proxy.setProxyInterfaces(arrayOf(javax.sql.DataSource::class.java))
        return proxy.getObject() as DataSource
    }

    @Bean
    fun transactionManager(dataSource: DataSource): TransactionManager {
        return DataSourceTransactionManager(dataSource)
    }

}


