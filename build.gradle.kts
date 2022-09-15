
buildscript {

    val compose_version by extra("1.1.0-beta01")
    val kotlin_version by extra("1.5.31")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }

    dependencies {
        classpath(libs.agp)
        classpath(libs.kotlin.gradlePlugin)
        classpath(libs.hilt.plugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
    }
}
