import com.github.gradle.node.npm.task.NpmTask


plugins {
  kotlin("jvm")
  id("com.github.node-gradle.node") version "3.1.0"
}

val buildAngular = tasks.register<NpmTask>("buildAngular") {
  dependsOn(tasks.npmInstall)
  npmCommand.set(listOf("run", "build"))
  ignoreExitValue.set(false)
  workingDir.set(projectDir)
  outputs.dir("${project.buildDir}/webapp")
}

buildAngular {
  onlyIf { !project.hasProperty("skipAngular") }
}

sourceSets {
  kotlin {
    main {
      resources {
        srcDir(buildAngular)
      }
    }
  }
}

node {
  download.set(false)
  version.set("14.15.0")
  npmVersion.set("6.14.10")
  // Set the work directory for unpacking node
  workDir.set(file("${project.buildDir}/nodejs"))
  // Set the work directory for NPM
  npmWorkDir.set(file("${project.buildDir}/npm"))
}

