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

import org.apache.commons.codec.digest.DigestUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VividApplication

fun main(args: Array<String>) {
    runApplication<VividApplication>(*args)
}

object SubdomainUtil {
    private val regex = Regex("(\\w+)\\.(\\w+)\\.")

    fun subdomain(requestServer: String): String {
        val result = regex.find(requestServer).runCatching { -> this?.groupValues!![1] }
        return result.getOrDefault("invalid")
    }
}

fun <R : Any> R.logger(): Lazy<Logger> {
    return lazy { LoggerFactory.getLogger(getClassName(this.javaClass)) }
}

fun <T : Any> getClassName(clazz: Class<T>): String {
    return clazz.name.removeSuffix("\$Companion")
}


val String.md5: String
    get() {
        return DigestUtils.md5Hex(this)
    }


val String.sha512: String
    get() {
        return DigestUtils.sha512_224Hex(this)
    }