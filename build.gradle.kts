plugins {
    id("java")
    id("io.freefair.lombok") version "8.4"
}

group = "com.factoriojustforfun"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:2.16.1")
    implementation("org.springframework.boot:spring-boot-starter-validation:2.4.0")
    implementation("com.networknt:json-schema-validator:1.3.1")
}
