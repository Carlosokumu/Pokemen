import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

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

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.ktx)
   // implementation("androidx.core:core-ktx:1.7.0")
    //implementation("androidx.appcompat:appcompat:1.5.1")
    //implementation("com.google.android.material:material:1.6.1")
    testImplementation(libs.junit)
    //testImplementation("junit:junit:4.13.2")
    androidTestImplementation(libs.androidx.junit)
    //androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation(libs.androidx.espresso)
    //androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}