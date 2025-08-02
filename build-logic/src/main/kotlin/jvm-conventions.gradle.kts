plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    explicitApi()

    jvmToolchain(libs.versions.java.get().toInt())

    compilerOptions {
        optIn.add("io.lettuce.core.ExperimentalLettuceCoroutinesApi")
    }

    dependencies {
        implementation(libs.kotlin.logging)

        testImplementation(kotlin("test-junit"))
    }
}
