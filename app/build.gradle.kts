plugins {
    application
    id("com.github.johnrengelman.shadow") version "7.1.2" // ✅ Apply Shadow JAR Plugin
}

repositories {
    mavenCentral()
}

dependencies {
    // JUnit 5 dependencies
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")

    // ✅ Use explicit Guava version instead of `libs.guava`
    implementation("com.google.guava:guava:32.0.1-jre")
}

testing {
    suites {
        val test = getByName<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}

// ✅ Ensure Java 21 is used
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    mainClass.set("plato.Plato")  // ✅ Ensure this matches your package and class
}

// ✅ Ensure JAR has the correct MANIFEST.MF
tasks.jar {
    manifest {
        attributes["Main-Class"] = "plato.Plato"
    }
}

// ✅ Configure Shadow JAR for packaging dependencies
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("PlatoApp")
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "plato.Plato"
    }
}
