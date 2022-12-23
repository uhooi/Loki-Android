import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.theuhooi.totonoi"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.theuhooi.totonoi"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }
    }
}


dependencies {
    implementation(libs.androidxCoreKtx)
    implementation(libs.androidxLifecycleLifecycleRuntimeKtx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.androidxTestEspressoEspressoCore)
    androidTestImplementation(libs.composeUiTestJunit4)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)

    // Compose
    implementation(libs.androidxActivityCompose)
    implementation(libs.composeUi)
    implementation(libs.composeUiToolingPreview)
    implementation(libs.composeFoundation)
    implementation(libs.androidxNavigationCompose)
    implementation(libs.composeMaterial3)
    implementation(libs.composeLifecycleRuntime)

    // Hilt
    implementation(libs.daggerHiltAndroid)
    kapt(libs.daggerHiltAndroidCompiler)
    implementation(libs.hiltNavigationCompose)

    // Material Design
    implementation(libs.material3)

    // Room
    implementation(libs.roomKtx)
    kapt(libs.roomCompiler)

    // Logger
    implementation(libs.timberLogger)
}

kapt {
    correctErrorTypes = true
}