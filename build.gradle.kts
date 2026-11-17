plugins {
    id("java-library")
    id("maven-publish")
}


repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.evokerking.dev/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.11-R0.1-SNAPSHOT")
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
                username = findProperty("evokerRepoUsername") as String?
                    ?: System.getenv("EVOKER_REPO_USERNAME")
                password = findProperty("evokerRepoPassword") as String?
                    ?: System.getenv("EVOKER_REPO_PASSWORD")
            }
        }
    }
}

tasks {
    processResources {
        val props = mapOf("version" to version, "description" to project.description)
        filesMatching("paper-plugin.yml") {
            expand(props)
        }
    }
}
