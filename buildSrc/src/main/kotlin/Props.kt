import org.gradle.api.JavaVersion

/**
 * Created by Kyle Fricilone on Oct 29, 2020.
 */
object Props {
    const val group = "me.kfricilone"
    const val version = "1.0.0-SNAPSHOT"

    val jvmVersion = JavaVersion.VERSION_11

    val commonArgs = listOf(
        "-opt-in=io.ktor.server.locations.KtorExperimentalLocationsAPI",
        "-opt-in=io.lettuce.core.ExperimentalLettuceCoroutinesApi",
        "-Xinline-classes"
    )

    val jvmArgs = commonArgs + listOf(
        "-Xjsr305=strict"
    )
}
