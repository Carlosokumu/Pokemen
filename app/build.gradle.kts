

plugins {
   // id 'com.android.application'
   // id 'org.jetbrains.kotlin.android'
    id(libs.plugins.android.application.get().pluginId)
    id(libs.plugins.kotlin.android.get().pluginId)
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
//        minSdk 21
//        targetSdk 32
//        versionCode 1
//        versionName "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        vectorDrawables {
//            useSupportLibrary true
//        }
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


    //Unit test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso)
    androidTestImplementation(libs.androidx.compose.junit)
    androidTestImplementation(libs.androidx.compose.tooling)
    androidTestImplementation(libs.androidx.compose.manifest)




   // implementation 'androidx.core:core-ktx:1.7.0'
   // implementation "androidx.compose.ui:ui:$compose_version"
   // implementation "androidx.compose.material:material:$compose_version"
    //implementation "androidx.compose.ui:ui-tooling-preview:$compose_version"
    //implementation 'androidx.lifecycle:lifecycle-runtime-ktx:2.3.1'
    //implementation 'androidx.activity:activity-compose:1.3.1'
    //testImplementation 'junit:junit:4.13.2'
   // androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    //androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
    //androidTestImplementation "androidx.compose.ui:ui-test-junit4:$compose_version"
    //debugImplementation "androidx.compose.ui:ui-tooling:$compose_version"
    //debugImplementation "androidx.compose.ui:ui-test-manifest:$compose_version"
}