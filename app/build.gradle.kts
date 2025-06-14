plugins {
    java
    application
    kotlin("jvm") version "2.1.20"
    id("org.openjfx.javafxplugin").version("0.0.13")

}

group = "edu.austral.dissis.chess"
version = "1.0.0"

repositories {
//    mavenLocal()
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/chess-ui")
        credentials {
            username = System.getenv("GITHUB_USER")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/simple-client-server")
        credentials {
            username = System.getenv("GITHUB_USER")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
    maven {
        url = uri("https://maven.pkg.github.com/austral-ingsis/test-framework")
        credentials {
            username = System.getenv("GITHUB_USER")
            password = System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("edu.austral.dissis.chess:chess-ui:2.0.1")
    implementation("edu.austral.dissis.chess:simple-client-server:1.2.0")
    testImplementation("edu.austral.dissis.chess:test-framework:1.4.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

javafx {
    version = "18"
    modules = listOf("javafx.graphics")
}

application {
    // Define the main class for the application.
    mainClass.set("edu.austral.dissis.app.ClientAppKt")
}

tasks.register("runClient", JavaExec::class) {
    classpath = project.tasks.getAt(JavaPlugin.JAR_TASK_NAME).outputs.files.plus(project.configurations.getByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME))
    mainClass.set("edu.austral.dissis.app.ClientAppKt")
}

tasks.register("runServer", JavaExec::class) {
    classpath = project.tasks.getAt(JavaPlugin.JAR_TASK_NAME).outputs.files.plus(project.configurations.getByName(JavaPlugin.RUNTIME_CLASSPATH_CONFIGURATION_NAME))
    mainClass.set("edu.austral.dissis.app.ServerAppKt")
}