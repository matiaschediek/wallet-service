plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "mchediek"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("software.amazon.awssdk:sns:2.28.11")
	implementation("ch.qos.logback:logback-classic")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:testcontainers")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:localstack")
	testImplementation("org.testcontainers:mongodb")
	testImplementation(platform("io.cucumber:cucumber-bom:7.11.2"))
	testImplementation("io.cucumber:cucumber-java")
	testImplementation("io.cucumber:cucumber-junit-platform-engine")
	testImplementation("org.junit.platform:junit-platform-suite")
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation(platform("org.junit:junit-bom:5.9.2"))
}

tasks.test {
	useJUnitPlatform()
}
