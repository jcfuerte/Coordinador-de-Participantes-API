import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

val ktor_version: String by project
val logback_version: String by project
buildscript {

    repositories {
        mavenLocal()
        mavenCentral()
        jcenter()
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }

    dependencies {
        classpath("com.github.jengelman.gradle.plugins:shadow:6.1.0")
    }
}



repositories {
    mavenCentral()
    jcenter()
    maven { url = uri("https://kotlin.bintray.com/ktor") }
}


plugins {
    application
    kotlin("jvm") version "1.4.10"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.4.10"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
    group = "net.unir.proyecto"
    version = "0.0.1"
    mainClassName = "io.ktor.server.netty.EngineMain"

}

sourceSets.getByName("main") {
    java.srcDir("src/main/kotlin")
}
sourceSets.getByName("main") {
    java.srcDir("src/main/resources")
}
sourceSets.getByName("test") {
    java.srcDir("src/test/kotlin")
}

val shadowJar by tasks.getting(ShadowJar::class) {
    manifest {
        attributes["Main-Class"] = "net.unir.proyecto.ApplicationKt"
        archiveBaseName.set("coordinador-de-participantes-application")
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor_version")
    implementation("io.ktor:ktor-serialization:$ktor_version")
    implementation("io.ktor:ktor-html-builder:$ktor_version")
    implementation("io.ktor:ktor-server-tomcat:$ktor_version")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests:$ktor_version")
}