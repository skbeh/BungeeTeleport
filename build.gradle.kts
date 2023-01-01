java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

plugins {
    java
    id("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    compileOnly("org.jetbrains:annotations:23.1.0")
    compileOnly("org.spigotmc:spigot-api:1.19.3-R0.1-SNAPSHOT")
}

tasks.withType<AbstractArchiveTask>().configureEach {
    isPreserveFileTimestamps = false
    isReproducibleFileOrder = true
}
