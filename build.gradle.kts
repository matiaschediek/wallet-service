plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "mchediek"
version = "1.0.0"

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
	implementation("ch.qos.logback:logback-classic")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")

	// Testing dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation(platform("io.cucumber:cucumber-bom:7.11.2"))
	testImplementation("io.cucumber:cucumber-java")
	testImplementation("io.cucumber:cucumber-junit-platform-engine")
	testImplementation("io.cucumber:cucumber-spring")
	testImplementation(platform("org.junit:junit-bom:5.9.2"))
	testImplementation("org.junit.jupiter:junit-jupiter")
	testImplementation("org.junit.platform:junit-platform-suite")
	testImplementation("org.testcontainers:mongodb")
	testImplementation("org.testcontainers:junit-jupiter")
}

tasks.test {
	useJUnitPlatform()
}
