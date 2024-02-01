plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("kotlin-kapt")
    //id("com.google.devtools.ksp")
    id("dagger.hilt.android.plugin")
}

val activityComposeVersion = "1.8.2"
val composeVersion = "1.6.0"
val coilVersion = "2.5.0"
val coreVersion = "1.12.0"
val pagingVersion = "3.2.1"
val pagingComposeVersion = "1.0.0-alpha16"
val navComposeVersion = "2.7.6"
val accompanistVersion = "0.18.0"
val constraintLayoutVersion = "1.0.0-beta02"
val workVersion = "2.8.0-alpha01"

val koinVersion = "3.1.2"
val hiltJetpackVersion = "1.1.0"
val hiltVersion = "2.50"

val lifecycleVersion = "2.7.0"

val roomVersion = "2.6.1"

val retrofitVersion = "2.9.0"
val okhttpVersion = "4.12.0"

val junitVersion = "4.13.2"
val truthVersion = "1.1.3"
val androidxTestCoreVersion = "1.5.0"
val androidxTestRunnerVersion = "1.5.2"
val androidxTestExtJUnitVersion = "1.1.5"
val coreTestingVersion = "2.2.0"
val coroutinesVersion = "1.7.3"
val robolectricVersion = "4.6.1"

android {
    namespace = "com.example.diogopokemon"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.diogopokemon"
        minSdk = 29
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
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    // Compose dependencies
    implementation("androidx.compose.material3:material3:1.2.0-rc01")
    implementation("androidx.activity:activity-compose:$activityComposeVersion")
    implementation("androidx.compose.ui:ui:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.material:material:$composeVersion")
    implementation("androidx.compose.ui:ui-tooling-preview:$composeVersion")
    implementation("io.coil-kt:coil-compose:$coilVersion")
    implementation("androidx.core:core-ktx:$coreVersion")
    implementation("androidx.paging:paging-runtime-ktx:$pagingVersion")
    implementation("androidx.paging:paging-compose:$pagingComposeVersion")
    implementation("androidx.navigation:navigation-compose:$navComposeVersion")
    implementation("com.google.accompanist:accompanist-insets-ui:$accompanistVersion")
    implementation("com.google.accompanist:accompanist-navigation-animation:$accompanistVersion")

    // Hilt
    implementation("androidx.hilt:hilt-work:$hiltJetpackVersion")
    implementation("androidx.hilt:hilt-navigation-compose:$hiltJetpackVersion")
    implementation("androidx.palette:palette-ktx:1.0.0")
    debugImplementation("androidx.compose.ui:ui-tooling:1.6.0")
    kapt("androidx.hilt:hilt-compiler:$hiltJetpackVersion")
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    kapt("com.google.dagger:hilt-android-compiler:$hiltVersion")

    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-paging:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    //ksp("androidx.room:room-compiler:$roomVersion")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Unit tests
    testImplementation("io.mockk:mockk:1.12.2")
    testImplementation("junit:junit:$junitVersion")
    testImplementation("com.google.truth:truth:$truthVersion")
    testImplementation("androidx.test:core-ktx:$androidxTestCoreVersion")
    testImplementation("androidx.test:runner:$androidxTestRunnerVersion")
    testImplementation("androidx.test.ext:junit:$androidxTestExtJUnitVersion")
    testImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")
    testImplementation("androidx.arch.core:core-testing:$coreTestingVersion")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
    testImplementation("org.robolectric:robolectric:$robolectricVersion")

    // Android tests
    androidTestImplementation("junit:junit:$junitVersion")
    androidTestImplementation("io.mockk:mockk-android:1.12.2")
    androidTestImplementation("com.google.truth:truth:$truthVersion")
    androidTestImplementation("androidx.test:core-ktx:$androidxTestCoreVersion")
    androidTestImplementation("androidx.test:runner:$androidxTestRunnerVersion")
    androidTestImplementation("androidx.test.ext:junit:$androidxTestExtJUnitVersion")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$composeVersion")

}