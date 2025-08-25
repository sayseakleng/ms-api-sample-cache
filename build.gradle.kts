plugins {
    id("java")
    id("eclipse")
    id("com.diffplug.eclipse.apt") version "4.3.0"
    id("org.springframework.boot") version "3.5.5"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.owasp.dependencycheck") version "12.1.3"
    id("com.diffplug.spotless") version "7.2.1"
    id("com.github.jk1.dependency-license-report") version "2.9"
    id("com.github.ben-manes.versions") version "0.52.0"
}

group = "kh.com.foss"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

eclipse.project {
    natures("org.eclipse.buildship.core.gradleprojectnature")
}

val lombokVersion = "1.18.38"
var springCloudVersion = "2025.0.0"
var mapstructVersion = "1.6.3";
var commonsLang3Version = "3.18.0";

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("redis.clients:jedis")

    // Spring Cloud
    implementation("io.micrometer:micrometer-tracing-bridge-brave")

    // Apache library
    implementation("org.apache.commons:commons-lang3:$commonsLang3Version")

    // DB
    runtimeOnly("com.h2database:h2")

    // Lombok
    compileOnly("org.projectlombok:lombok:$lombokVersion")

    // Mapstruct
    implementation("org.mapstruct:mapstruct:$mapstructVersion")

    // Annotation Processor
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    annotationProcessor("org.mapstruct:mapstruct-processor:$mapstructVersion")

    // Test
    testCompileOnly("org.projectlombok:lombok:$lombokVersion")
    testAnnotationProcessor("org.projectlombok:lombok:$lombokVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

configurations.all {
    // exclude other log implementations
    exclude(group = "log4j", module = "log4j")
    exclude(group = "org.slf4j", module = "slf4j-log4j12")
    exclude(group = "org.slf4j", module = "jul-to-slf4j")
    exclude(group = "org.slf4j", module = "jcl-over-slf4j")
    exclude(group = "commons-logging", module = "commons-logging")
    exclude(group = "org.apache.logging.log4j", module = "log4j-api")
    exclude(group = "org.apache.logging.log4j", module = "log4j-to-slf4j")
    exclude(group = "com.vaadin.external.google", module = "android-json")
}

spotless {
    java {
        palantirJavaFormat()
        importOrder()
        removeUnusedImports()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

dependencyCheck {
    nvd {
        delay = 30_000
        // register your account @ https://nvd.nist.gov/developers/request-an-api-key
        apiKey = "100d524f-b6cd-4d9e-8087-fde26dbe1ac4"
    }
}

licenseReport {
    renderers = arrayOf(
        com.github.jk1.license.render.CsvReportRenderer()
    )
}
