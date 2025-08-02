plugins {
    id("application-conventions")
}

dependencies {
    implementation(libs.aws.s3)
    implementation(libs.bundles.exposed)
    implementation(libs.bundles.koin)
    implementation(libs.bundles.ktor)
    implementation(libs.kmongo)
    implementation(libs.kotlin.result)
    implementation(libs.kotlin.retry)
    implementation(libs.lettuce.core)
    implementation(libs.postgresql)
    implementation(projects.frontend)
}

application {
    mainClass.set("me.kfricilone.pasty.ServerKt")
}

jib {
    container {
        mainClass = "me.kfricilone.pasty.ServerKt"
        ports = listOf("8080")
    }
}
