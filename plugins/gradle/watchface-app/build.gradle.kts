plugins {
    id("com.gradle.plugin-publish") version "0.20.0"
    `java-gradle-plugin`
    `maven-publish`
    signing
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create(project.name) {
            id = "org.splitties.wff.watchface-app"
            displayName = "TK"
            description = "TK"
            implementationClass = "splitties.wff.WatchfaceAppPlugin"
        }
    }
}

pluginBundle {
    website = ""
    vcsUrl = ""
    tags = listOf("wear-os", "kotlin", "kotlin-dsl")
}

signing {
    useInMemoryPgpKeys(
        propertyOrEnvOrNull("GPG_key_id"),
        propertyOrEnvOrNull("GPG_private_key") ?: return@signing,
        propertyOrEnv("GPG_private_password")
    )
    sign(publishing.publications)
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation("com.android.application:com.android.application.gradle.plugin:8.1.2")
}

kotlin {
    jvmToolchain(17)
}

java {
    withSourcesJar()
}

fun Project.propertyOrEnv(key: String): String = propertyOrEnvOrNull(key)
    ?: error("Didn't find any value for the key \"$key\" in Project properties or environment variables.")

fun Project.propertyOrEnvOrNull(key: String): String? {
    return findProperty(key) as String? ?: System.getenv(key)
}