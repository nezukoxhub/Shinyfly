// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
// The Myspoti API contract: models and interfaces only, no Android, no Spotify details.
// The UI depends on this module alone.
plugins {
    alias(libs.plugins.kotlin.jvm)
}

kotlin {
    jvmToolchain(17)
}

dependencies {
    api(libs.coroutines.core)
}
