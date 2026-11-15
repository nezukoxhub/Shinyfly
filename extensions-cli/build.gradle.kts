// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
// `myspoti-ext`: build, check and try Myspoti extensions on a computer. With --cdp it also
// reaches a real Spotify account through a desktop Chrome (:host-cdp), which :extensions itself
// never depends on.
//   ./gradlew :extensions-cli:run --args="run path/to/extension lyrics ..."
plugins {
    alias(libs.plugins.kotlin.jvm)
    application
}

kotlin {
    jvmToolchain(17)
}

application {
    mainClass.set("app.myspoti.extensions.cli.RunKt")
    applicationName = "myspoti-ext"
}

dependencies {
    implementation(project(":extensions"))
    implementation(project(":host-cdp"))
    implementation(libs.serialization.json)
    implementation(libs.coroutines.core)
    testImplementation("junit:junit:4.13.2")
}
