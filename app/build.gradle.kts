plugins {
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    // Guava for application
    implementation(libs.guava)
}

testing {
    suites {
        val test = getByName<JvmTestSuite>("test") {
            // Use JUnit 5 (Jupiter)
            useJUnitJupiter()
        }
    }
}

// Correcting the Java toolchain type
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    // Fix the type mismatch for the main class
    mainClass.set("org.example.App")
}
