plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
    id("org.jetbrains.kotlinx.kover")
    id("org.jmailen.kotlinter")
    id("io.gitlab.arturbosch.detekt")
    id("org.jetbrains.dokka")
    id("com.google.cloud.tools.jib")
    application
}

dependencies {
    implementation(kotlin("stdlib", libs.versions.kotlin.get()))
    implementation(libs.bundles.ktor)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)
    implementation(libs.aws.s3)
    implementation(libs.kmongo)
    implementation(libs.bundles.exposed)
    implementation(libs.postgresql)
    implementation(libs.lettuce.core)
    implementation(libs.kotlin.result)
    implementation(libs.kotlin.retry)
    implementation(projects.frontend)
}

kotlin {
    explicitApi()
}

application {
    mainClass.set("me.kfricilone.pasty.ServerKt")
}

jib {
    from {
        image = "bellsoft/liberica-openjre-alpine:11"
    }
    to {
        image = rootProject.name
        tags = setOf("${rootProject.version}")
    }
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
        ports = listOf("8080")
    }
}
