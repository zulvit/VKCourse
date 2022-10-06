plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

//Tasks API: tasks. TaskContainer,create,register,find,replace

//region recommended
tasks.register("hello").configure {
    group = "spec"
    //configure,

    doFirst {
        print("FIRST: hello\n")
    }

    doLast {
        print("LAST: task")
    }
}

//OR
abstract class HelloTask : DefaultTask() {
    @TaskAction
    fun hello() {
        println("hello world!")
    }
}

tasks.register<HelloTask>("hello2")
//endregion

//region not recommended
tasks.create("hello1") {
    group = "spec"
    doFirst {
        print("FIRST: hello\n")
    }
    doLast {
        print("LAST: task")
    }
}
//endregion

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}