pluginManagement {
  repositories {
    mavenLocal()
    gradlePluginPortal()
    maven {
      name = "savaGithubPackages"
      url = uri("https://maven.pkg.github.com/sava-software/sava-build")
      credentials(PasswordCredentials::class)
    }
    maven {
      url = uri("https://software.r3.com/artifactory/corda-dependencies")
      credentials {
        username = System.getenv("CORDA_ARTIFACTORY_USERNAME")
        password = System.getenv("CORDA_ARTIFACTORY_PASSWORD")
      }
    }
  }
}

plugins {
  id("software.sava.build") version "21.3.3-j17-1"
}

rootProject.name = "solana-programs"

javaModules {
  directory(".") {
    group = "software.sava"
    plugin("software.sava.build.java-module")
  }
}
