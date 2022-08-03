plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-parcelize")
    kotlin("kapt")
    kotlin("plugin.serialization")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.currency_converter_app"
        minSdk = 22
        targetSdk = 32
        versionCode = 1
        versionName = "1.0.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
        getByName("debug") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0-rc01"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // AndroidX
    implementation(dependencyNotation = "androidx.core:core-ktx:1.8.0")
    implementation(dependencyNotation = "androidx.appcompat:appcompat:1.4.2")
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Material
    implementation(dependencyNotation = "com.google.android.material:material:1.6.1")

    // Compose
    implementation(dependencyNotation = "androidx.compose.compiler:compiler:1.3.0-rc01")
    implementation(dependencyNotation = "androidx.compose.runtime:runtime:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.compose.ui:ui:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.compose.ui:ui-tooling-preview:1.3.0-alpha02")
    implementation(dependencyNotation = "androidx.constraintlayout:constraintlayout-compose:1.0.1")
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)
    implementation(dependencyNotation = "androidx.compose.foundation:foundation:1.3.0-alpha02")
    // Material Design, Icons
    implementation(dependencyNotation = "androidx.compose.material:material:1.2.0")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-core:1.2.0")
    implementation(dependencyNotation = "androidx.compose.material:material-icons-extended:1.2.0")
    // Integration with activities
    implementation(dependencyNotation = "androidx.activity:activity-compose:1.5.1")
    // Integration with ViewModels
    implementation(dependencyNotation = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")
    // Navigation Hilt
    implementation(dependencyNotation = "androidx.hilt:hilt-navigation-compose:1.0.0")
    // Navigation Compose
    implementation(dependencyNotation = "androidx.navigation:navigation-compose:2.5.1")
    // Accompanist system ui controller
    implementation(dependencyNotation = "com.google.accompanist:accompanist-systemuicontroller:0.24.3-alpha")
    // Accompanist Navigation Compose Animations
    implementation(dependencyNotation = "com.google.accompanist:accompanist-navigation-animation:0.24.3-alpha")

    // Koin
    implementation(dependencyNotation = "io.insert-koin:koin-android:3.1.4")

    // Kotlin Coroutines
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.1")

    // Kotlin Serialization
    implementation(dependencyNotation = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // Network
    implementation(dependencyNotation = "io.ktor:ktor-client-core:2.0.1")
    implementation(dependencyNotation = "io.ktor:ktor-client-android:2.0.1")
    implementation(dependencyNotation = "io.ktor:ktor-client-content-negotiation:2.0.1")
    implementation(dependencyNotation = "io.ktor:ktor-client-logging:2.0.1")
    implementation(dependencyNotation = "io.ktor:ktor-serialization-kotlinx-json:2.0.1")

    // Database
    implementation(dependencyNotation = "androidx.datastore:datastore-preferences:1.0.0")
    implementation(dependencyNotation = "com.google.code.gson:gson:2.8.9")

    // Timber
    implementation(dependencyNotation = "com.jakewharton.timber:timber:5.0.1")

    // Test
    testImplementation(dependencyNotation = "junit:junit:4.13.2")
    testImplementation(dependencyNotation = "org.mockito.kotlin:mockito-kotlin:4.0.0")
    androidTestImplementation(dependencyNotation = "androidx.test.ext:junit:1.1.3")
    androidTestImplementation(dependencyNotation = "androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation(dependencyNotation = "androidx.compose.ui:ui-test-junit4:1.3.0-alpha02")
    debugImplementation(dependencyNotation = "androidx.compose.ui:ui-tooling:1.3.0-alpha02")
}