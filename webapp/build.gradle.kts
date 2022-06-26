import com.github.gradle.node.npm.task.NpmTask


plugins {
  kotlin("jvm")
  id("com.github.node-gradle.node") version "3.3.0"
}

repositories {
  mavenCentral()
}

dependencies {
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
}

val buildAngular = tasks.register<NpmTask>("buildAngular") {
  dependsOn(tasks.npmInstall)
  npmCommand.set(listOf("run", "build"))
  ignoreExitValue.set(false)
  workingDir.set(projectDir)
  outputs.dir("${project.buildDir}/resources/main")
}

buildAngular {
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

