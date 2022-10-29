import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
   // id 'com.android.application'
   // id 'org.jetbrains.kotlin.android'
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
    id(libs.plugins.kotlin.kapt.get().pluginId)
    id(libs.plugins.hilt.plugin.get().pluginId)
    id("com.google.devtools.ksp")  version "1.6.10-1.0.2"
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
        freeCompilerArgs = listOf("-Xjvm-default=all")
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

    applicationVariants.all {
        kotlin.sourceSets {
            getByName(name) {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }
}

dependencies {

    // modules
    implementation(project(":core-network"))

    implementation(project(":core-data"))
    implementation(project(":core-model"))
    implementation(project(":core-database"))


    // androidx
    implementation(libs.androidx.ktx)
    implementation(libs.androidx.material.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.lifecycle)
    implementation(libs.androidx.compose.tooling)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.paging)
    implementation(libs.coroutines)
    implementation(libs.androidx.paging.compose)
    implementation(libs.androidx.hilt.navigation)
    implementation(libs.toolbar)
    implementation(libs.motion)
    implementation(libs.segmented)

    implementation(libs.androidx.pallete)

    //Lottie
    implementation(libs.lottie)







    //Coil
    implementation(libs.coil)

    //di
    implementation(libs.hilt.android)
    implementation(libs.androidx.paging.ktx)
    kapt(libs.hilt.compiler)

    implementation(libs.retrofit)
    implementation(libs.okhttp.interceptor)
    implementation(libs.okhttp)

    implementation(libs.destination.ramcosta)
    ksp(libs.ksp.ramcosta)

    //Unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.androidx.compose.junit)
    androidTestImplementation(libs.androidx.compose.tooling)
    androidTestImplementation(libs.androidx.compose.manifest)


    //Room
    implementation(libs.androidx.room)
    annotationProcessor(libs.androidx.room.compiler)





}