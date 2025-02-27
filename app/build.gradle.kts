plugins {
    application
    id("com.github.johnrengelman.shadow") version "7.1.2" // ✅ Shadow JAR Plugin for packaging dependencies
}

repositories {
    mavenCentral()
}

dependencies {
    val javaFxVersion = "17"  // ✅ Use JavaFX version that matches your JDK

    implementation("org.openjfx:javafx-controls:$javaFxVersion")
    implementation("org.openjfx:javafx-fxml:$javaFxVersion")
    implementation("org.openjfx:javafx-base:$javaFxVersion")
    implementation("org.openjfx:javafx-graphics:$javaFxVersion")

    // ✅ JUnit 5 dependencies for testing
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.3")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.3")
}


// ✅ Ensure Java 21 is used
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

// ✅ Set `Launcher` as the main entry point
application {
    mainClass.set("plato.Launcher")  // 🔥 Ensure this matches your actual package structure
}

// ✅ Ensure JAR has the correct MANIFEST.MF
tasks.jar {
    manifest {
        attributes["Main-Class"] = "plato.Launcher"  // 🔥 Ensure Shadow JAR also uses Launcher
    }
}

// ✅ Configure Shadow JAR for packaging dependencies
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    archiveBaseName.set("PlatoApp")
    archiveClassifier.set("")
    manifest {
        attributes["Main-Class"] = "plato.Launcher"
    }
    mergeServiceFiles()
}

// ✅ Ensure JavaFX modules are correctly included when running the application
tasks.withType<JavaExec> {
    jvmArgs = listOf(
        "--module-path", classpath.asPath,
        "--add-modules", "javafx.controls,javafx.fxml,javafx.application"
    )
}

// ✅ Ensure `shadowJar` is only executed when needed
tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    dependsOn("classes")  // Instead of `build`
}

// ✅ Remove `shadowJar` dependency from `distZip`, `distTar`, and `startScripts`
tasks.named("distZip").configure { mustRunAfter("shadowJar") }
tasks.named("distTar").configure { mustRunAfter("shadowJar") }
tasks.named("startScripts").configure { mustRunAfter("shadowJar") }

// ✅ Ensure `startShadowScripts` runs AFTER `jar` but does not depend on it
tasks.named("startShadowScripts").configure { mustRunAfter("shadowJar") }


// ✅ Set up testing with JUnit 5
testing {
    suites {
        val test = getByName<JvmTestSuite>("test") {
            useJUnitJupiter()
        }
    }
}
