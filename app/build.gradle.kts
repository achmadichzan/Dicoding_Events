import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlinx.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.achmadichzan.dicodingevents"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.achmadichzan.dicodingevents"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            val properties = Properties()
            project.rootProject.file("local.properties").inputStream()
                .use { properties.load(it) }
            buildConfigField(
                "String",
                "BASE_URL",
                "\"${properties.getProperty("BASE_URL")}\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {

    // Coil
    implementation(libs.coil3.compose)
    implementation(libs.coil3.ktor)

    // Lifecycle
    implementation(libs.bundles.lifecycle)

    implementation(libs.kotlinx.serialization.json)

    // Ktor
    implementation(libs.bundles.ktor)

    // Hilt (dagger/hilt)
    implementation(libs.bundles.hilt)
    ksp(libs.bundles.hilt.ksp)

    // Navigation
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigation.compose)

    // Room
    implementation(libs.bundles.room)
    ksp(libs.room.compiler)

    // Data Store
    implementation(libs.androidx.datastore.preferences)

    // Work Manager
    implementation(libs.androidx.work.runtime.ktx)

    // Splash Screen
    implementation(libs.androidx.core.splashscreen)

    // Timber
    implementation(libs.timber)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}