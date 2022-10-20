enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
        maven (url = "https://jitpack.io" )
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }


}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven (url = "https://jitpack.io" )
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}
rootProject.name = "Pokedex"
include(":app")
include(":core-network")
include(":core-database")
include(":core-data")
include(":core-model")
