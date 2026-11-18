import java.util.Properties

plugins {
    id("java-library")
    id("maven-publish")
}

val publishingCredentials = providers.fileContents(layout.projectDirectory.file(".credentials"))
    .asText
    .orElse("")
    .map { contents ->
        Properties().apply { load(contents.reader()) }
    }


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.evokerking.dev/repository/maven-public/")
    maven("https://repo.opencollab.dev/main/")

}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
    compileOnly("org.geysermc.geyser:api:2.11.2-SNAPSHOT")
}

java {
    toolchain.languageVersion = JavaLanguageVersion.of(21)
    
    withSourcesJar()
    withJavadocJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "evokergui"
            version = project.version.toString()

            pom {
                name.set("EvokerGUI")
                description.set(project.description)
                url.set("https://evokerengine.evokerkng.dev")

                developers {
                    developer {
                        id.set("Evokerking")
                        name.set("Evokerking")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "EvokerKing"

            url = uri(
                if (project.version.toString().endsWith("-SNAPSHOT")) {
                    "https://repo.evokerking.dev/repository/maven-snapshots/"
                } else {
                    "https://repo.evokerking.dev/repository/maven-releases/"
                }
            )

            credentials {
                username = publishingCredentials.map { it.getProperty("evokerRepoUsername") }.orNull
                    ?: findProperty("evokerRepoUsername") as String?
                    ?: System.getenv("EVOKER_REPO_USERNAME")
                password = publishingCredentials.map { it.getProperty("evokerRepoPassword") }.orNull
                    ?: findProperty("evokerRepoPassword") as String?
                    ?: System.getenv("EVOKER_REPO_PASSWORD")
            }
        }
    }
}

tasks {

}
