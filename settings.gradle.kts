import java.net.URI
import java.util.Properties

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = URI("https://jitpack.io")
        }
        maven {
            // Important note:
            // To authenticate with GitHub Packages, you need to generate a fine-grained personal access token
            // The token needs access only to [Public repositories]
            // Add it as gpr.token=$token in local.properties file in the root of the project
            // For more details, refer to the documentation:
            // https://github.com/settings/personal-access-tokens/new
            // https://docs.github.com/en/packages/working-with-a-github-packages-registry/working-with-the-gradle-registry#authenticating-with-a-personal-access-token

            val localProps = getLocalProps()

            name = "GitHubPackages"
            url = URI("https://maven.pkg.github.com/ARK-Builders/arklib-android")
            credentials {
                username = "token"
                password = localProps.getProperty("gpr.token") ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }

    versionCatalogs {
        create("libraries") {
            from(files("./gradle/libs.versions.toml"))
        }
    }
}

fun getLocalProps(): Properties {
    val props = Properties()
    val localPropsFile = File(rootDir, "local.properties")

    if (localPropsFile.exists()) {
        localPropsFile.inputStream().use { stream ->
            props.load(stream)
        }
    }

    return props
}

rootProject.name = "Ark Components"
include(":scorewidget")
include(":tagselector")
include(":folderstree")
include(":utils")
include(":filepicker")
include(":sample")
include(":about")
