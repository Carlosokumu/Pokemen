import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    compileSdk = 32

    defaultConfig {
        minSdk = 21
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {



    implementation(project(":core-model"))
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.5.1")
    implementation("com.google.android.material:material:1.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

//    implementation(libs.androidx.room)
//    kapt(libs.androidx.room.compiler)
//    implementation(libs.androidx.room.extensions)
    val roomVersion = "2.3.0-alpha03"
    implementation("androidx.room:room-runtime:2.5.0-beta01")
    //kapt ("androidx.room:room-runtime:$roomVersion"
    kapt("androidx.room:room-compiler:2.5.0-beta01")
    implementation("androidx.room:room-ktx:2.5.0-beta01")
    implementation("androidx.room:room-paging:2.5.0-beta01")


    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.paging)
    implementation(libs.coroutines)
    implementation(libs.androidx.paging.compose)
    implementation(libs.gson)
    //testImplementation(libs.robolectric)
    testImplementation (libs.robolectric)
    testImplementation(libs.androidx.test.core)

}