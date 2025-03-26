// Project-level build.gradle.kts
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.23-1.0.19" apply false // Updated KSP version
}

// In your build.gradle.kts (Kotlin DSL) file

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

