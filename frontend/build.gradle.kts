plugins {
    alias(libs.plugins.webjar)
}

webjar {
    distDir = "build/dist"
    webjarDir = "META-INF/resources/webjars/${rootProject.name}/${project.version}"
}

node {
    version.set(libs.versions.node)
    npmVersion.set(libs.versions.npm)
}

tasks.npmSetup {
    args.add("--silent")
}

tasks.webjarInit {
    args.add("--silent")
}

tasks.webjarInstall {
    args.add("--silent")
}

tasks.webjarLint {
    args.add("--silent")
}

tasks.webjarBuild {
    args.add("--silent")
}

tasks.webjarClean {
    args.add("--silent")
}

tasks.jar {
    isPreserveFileTimestamps = true
    isReproducibleFileOrder = true
}

val move by tasks.registering {
    doLast {
        file("build/resources/main/CaskaydiaCove.ttf")
            .renameTo(
                file("build/resources/main/META-INF/resources/webjars/${rootProject.name}/${project.version}/CaskaydiaCove.ttf")
            )
    }
}

tasks.processResources {
    finalizedBy(move)
}
