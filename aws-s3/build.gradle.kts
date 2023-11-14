plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.kotlinCocoapods)
    alias(libs.plugins.androidLibrary)
    id("javadoc-stub-convention")
    id("publication-convention")
}


@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        publishAllLibraryVariants()
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    jvm()

    cocoapods {
        ios.deploymentTarget = "11.0"
        framework {
            baseName = "aws-s3"
        }
        noPodspec()

        pod("AWSCore")
        pod("AWSS3")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(projects.awsCommon)
                implementation(libs.kotlinx.datetime)
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.amazonaws:aws-android-sdk-s3:2.73.0")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation(projects.awsCommon)
                implementation(project.dependencies.platform("com.amazonaws:aws-java-sdk-bom:1.12.529"))
                implementation("com.amazonaws:aws-java-sdk-s3")
            }
        }
    }
}

android {
    namespace = "com.estivensh4.aws_s3"
    compileSdk = 34
    defaultConfig {
        minSdk = 26
    }
}