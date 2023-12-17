plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
}

repositories {
    mavenCentral()
    maven("https://maven.enginehub.org/repo/")
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    implementation("org.jetbrains.kotlin", "kotlin-stdlib", "1.8.21")
    implementation(fileTree(mapOf("dir" to "../Gamesys/build/libs", "include" to listOf("*.jar"))))
    compileOnly("io.papermc.paper", "paper-api", "1.20.2-R0.1-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks.withType<Jar> {
    version = "1.0-SNAPSHOT"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}
