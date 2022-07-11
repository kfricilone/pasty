plugins {
    id("com.coditory.webjar")
}

webjar {
    distDir = "build/dist"
    webjarDir = "META-INF/resources/webjars/pasty/${project.version}"
}

node {
    version.set(libs.versions.node)
    npmVersion.set(libs.versions.npm)
}

tasks.jar {
    isPreserveFileTimestamps = true
    isReproducibleFileOrder = true
}

val move by tasks.registering {
    doLast {
        file("build/resources/main/CaskaydiaCove.ttf")
            .renameTo(
                file("build/resources/main/META-INF/resources/webjars/pasty/${project.version}/CaskaydiaCove.ttf")
            )
    }
}

tasks.named("processResources") {
    finalizedBy(move)
}
