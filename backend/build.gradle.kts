import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import org.jetbrains.kotlin.resolve.calls.model.ResolvedCallArgument.DefaultArgument.arguments

buildscript {
    val kotlinVersion = "1.2.71"
    extra["kotlinVersion"] = kotlinVersion

    val springBootVersion = "2.1.5.RELEASE"
    extra["springBootVersion"] = springBootVersion

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
    }
}

apply {
    plugin("idea")
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-kapt")
    plugin("org.springframework.boot")
    plugin("io.spring.dependency-management")
}


plugins {
    idea
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
    kotlin("kapt") version "1.2.71"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

val processResources by tasks.existing(ProcessResources::class)
val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks

processResources.get().destinationDir = compileJava.destinationDir
compileKotlin.dependsOn(processResources)

kapt {
    arguments {
        arg("doma.resources.dir", processResources.get().destinationDir)
    }
}

idea {
    module {
        inheritOutputDirs = false
        outputDir = file("$buildDir/classes/kotlin/main")
    }
}


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    compile("mysql:mysql-connector-java")

    implementation("org.seasar.doma.boot:doma-spring-boot-starter:1.1.1")
    implementation("org.seasar.doma:doma:2.19.3")
    kapt("org.seasar.doma:doma:2.19.3")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
