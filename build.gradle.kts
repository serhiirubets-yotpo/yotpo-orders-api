plugins {
	java
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
}

group = "com.example"
version = "1.0"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.postgresql:postgresql")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	compileOnly("org.projectlombok:lombok:1.18.34")
	annotationProcessor("org.projectlombok:lombok:1.18.34")

	testCompileOnly("org.projectlombok:lombok:1.18.34")
	testAnnotationProcessor("org.projectlombok:lombok:1.18.34")

	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	implementation("org.apache.kafka:kafka-clients:3.7.1")

	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.17.2")
	implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.17.2")

	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	compileOnly("org.springframework.boot:spring-boot-devtools")


	testImplementation("org.springframework.boot:spring-boot-starter-test")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}


tasks.bootJar {
	archiveFileName.set("service.jar")
}