import com.github.gradle.node.npm.task.NpmTask


plugins {
  kotlin("jvm")
  id("com.github.node-gradle.node") version "3.5.0"
}

repositories {
  mavenCentral()
}

group = "com.vivid"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val buildAngular = tasks.register<NpmTask>("buildAngular") {
  onlyIf {  !project.hasProperty("skipAngular") }
  dependsOn(tasks.npmInstall)
  npmCommand.set(listOf("run", "build"))
  ignoreExitValue.set(false)
  workingDir.set(projectDir)
  outputs.dir("${project.buildDir}/resources/main")
}

tasks.npmInstall {
  onlyIf { !project.hasProperty("skipAngular") }
}
tasks.npmSetup {
  onlyIf { !project.hasProperty("skipAngular") }
}

tasks.jar {
  dependsOn(buildAngular)
}

node {
  download.set(false)
  version.set("16.15.1")
  npmVersion.set("8.11.0")
  // Set the work directory for unpacking node
  workDir.set(file("${project.buildDir}/nodejs"))
  // Set the work directory for NPM
  npmWorkDir.set(file("${project.buildDir}/npm"))
}

