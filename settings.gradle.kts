rootProject.name = "solana-programs"

pluginManagement {
  repositories {
    mavenLocal()
    gradlePluginPortal()
    mavenCentral()
    val gprUser = providers.gradleProperty("savaGithubPackagesUsername")
      .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_savaGithubPackagesUsername"))
      .orElse(providers.environmentVariable("GITHUB_ACTOR"))
      .orNull
    val gprToken = providers.gradleProperty("savaGithubPackagesPassword")
      .orElse(providers.environmentVariable("ORG_GRADLE_PROJECT_savaGithubPackagesPassword"))
      .orElse(providers.environmentVariable("GITHUB_TOKEN"))
      .orNull
    if (gprUser != null && gprToken != null) {
      maven {
        url = uri("https://maven.pkg.github.com/sava-software/sava-build")
        credentials {
          username = gprUser
          password = gprToken
        }
      }
    }
//  includeBuild("../sava-build")
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
  id("software.sava.build") version "21.3.8-j17-1"
}

javaModules {
  directory(".") {
    group = "software.sava"
    plugin("software.sava.build.java-module")
  }
}
