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

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.moowork.gradle.node.npm.NpmTask



plugins {
    id("org.springframework.boot") version "2.4.2"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.github.node-gradle.node") version "2.2.4"
    kotlin("jvm") version "1.4.21"
    kotlin("plugin.spring") version "1.4.21"
}

group = "com.vivid"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_15

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(module="spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    implementation("org.springframework.boot:spring-boot-starter-jdbc")
    {
        exclude(group="com.zaxxer",module="HikariCP")
    }
    implementation("org.vibur:vibur-dbcp:25.0")
    runtimeOnly("com.h2database:h2")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("commons-codec:commons-codec:1.14")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
        useIR = true
    }
}


tasks.register<NpmTask>("appNpmInstall") {
    description = "Installs all dependencies from package.json"
    setWorkingDir(file("${project.projectDir}/webapp"))
    setArgs(listOf("install"))
}

tasks.register<NpmTask>("appNpmBuild") {
    dependsOn("appNpmInstall")
    description = "Builds project"
    setWorkingDir(file("${project.projectDir}/webapp"))
    setArgs(listOf("run", "build"))
}


tasks.register<Copy>("copyAngularNg") {
    dependsOn("appNpmBuild")
    description = "Copies built Angular Project to static resource folder"
    from("webapp/build/static")
    into("$buildDir/resources/main/static/")

}

node {
    download = true
    version = "14.15.0"
    npmVersion = "6.14.10"
    // Set the work directory for unpacking node
    workDir = file("${project.buildDir}/nodejs")
    // Set the work directory for NPM
    npmWorkDir = file("${project.buildDir}/npm")
}