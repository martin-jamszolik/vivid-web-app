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
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.SimpleDriverDataSource
import org.springframework.jdbc.datasource.embedded.ConnectionProperties
import org.springframework.jdbc.datasource.embedded.DataSourceFactory
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.security.crypto.password.PasswordEncoder
import java.sql.Driver
import javax.sql.DataSource


@Configuration
class H2DatabaseConfiguration(val encoder: PasswordEncoder) {


    fun firstDataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setDataSourceFactory( VividDriverDataSourceFactory())
            .setName("first")
            .setType(EmbeddedDatabaseType.H2)
            .continueOnError(true)
            .addScript("classpath:templates/h2-ddl.sql")
            .addScript("classpath:templates/seed-h2-1.sql")
            .build();
    }


    fun secondDataSource(): DataSource {
        return EmbeddedDatabaseBuilder()
            .setDataSourceFactory( VividDriverDataSourceFactory())
            .setName("second")
            .continueOnError(true)
            .setType(EmbeddedDatabaseType.H2)
            .addScript("classpath:templates/h2-ddl.sql")
            .addScript("classpath:templates/seed-h2-2.sql")
            .build();
    }

    @Bean
    fun getMultiDataSource(): MultiDataSource {
        val ds = MultiDataSource()

        val ds1 = firstDataSource()
        val first = JdbcTemplate(ds1)
        first.execute("UPDATE users SET password='${encoder.encode("secret")}' WHERE id=1")

        val ds2 = secondDataSource()
        val second = JdbcTemplate(ds2)
        second.execute("UPDATE users SET password='${encoder.encode("secret")}' WHERE id=1")

        ds.addDS("first", ds1)
        ds.addDS("second", ds2)
        return ds
    }

}


internal class VividDriverDataSourceFactory : DataSourceFactory {
    private val dataSource = SimpleDriverDataSource()
    override fun getConnectionProperties(): ConnectionProperties {
        return object : ConnectionProperties {
            override fun setDriverClass(driverClass: Class<out Driver?>) {
                dataSource.setDriverClass(driverClass)
            }

            override fun setUrl(url: String) {
                dataSource.url = "$url;MODE=MYSQL;DATABASE_TO_LOWER=TRUE"
            }

            override fun setUsername(username: String) {
                dataSource.username = username
            }

            override fun setPassword(password: String) {
                dataSource.password = password
            }
        }
    }

    override fun getDataSource(): DataSource {
        return dataSource
    }
}