import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.21"
    kotlin("plugin.spring") version "1.8.21"
    id("com.thinkimi.gradle.MybatisGenerator") version "2.4.1"
}

group = "jp.ac.it_college.std.s22016"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.2")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //6.3.1
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.5.0")
    mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

mybatisGenerator {
    verbose = true
    configFile = "${projectDir}/src/main/resources/generatorConfig.xml"
}
