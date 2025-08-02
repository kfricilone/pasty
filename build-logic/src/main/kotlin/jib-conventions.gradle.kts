plugins {
    alias(libs.plugins.jib)
}

jib {
    from {
        image = "bellsoft/liberica-runtime-container:jre-${libs.versions.java.get()}-musl"
    }
    to {
        image = rootProject.name
        tags = setOf("${rootProject.version}")
    }
    container {
        creationTime.set("USE_CURRENT_TIMESTAMP")
    }
}
