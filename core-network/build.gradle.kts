plugins {
    id(libs.plugins.android.library.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk

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

    implementation(libs.coroutines)
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.ktx)
    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)

    androidTestImplementation(libs.androidx.espresso)

    //Network
    implementation(libs.retrofit)
    implementation(libs.okhttp.interceptor)
    implementation(libs.gson)
    implementation(libs.okhttp)

    //Room
    implementation(libs.androidx.room)
    annotationProcessor(libs.androidx.room.compiler)

    implementation(libs.androidx.paging)
    implementation(libs.coroutines)
    implementation(libs.androidx.paging.compose)

}