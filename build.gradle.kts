
buildscript {

    val compose_version by extra("1.1.0-beta01")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradlePlugin)
    }
}