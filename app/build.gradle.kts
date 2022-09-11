import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
   // id 'com.android.application'
   // id 'org.jetbrains.kotlin.android'
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.plugin.get().pluginId)
}

android {
    compileSdk = Configuration.compileSdk

    defaultConfig {
        applicationId  = "com.carlos.pokemen"
        minSdk = Configuration.minSdk
        targetSdk = Configuration.targetSdk
        versionCode = Configuration.versionCode
        versionName = Configuration.versionName
        vectorDrawables.useSupportLibrary = true


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        //jvmTarget = '1.8'
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = rootProject.extra["compose_version"] as String
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    // androidx
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.material.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.activity)


    //di
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)


    //Unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.androidx.compose.junit)
    androidTestImplementation(libs.androidx.compose.tooling)
    androidTestImplementation(libs.androidx.compose.manifest)





}