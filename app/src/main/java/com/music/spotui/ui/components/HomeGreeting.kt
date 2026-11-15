// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import app.myspoti.engine.R
import java.time.LocalTime

/** A localized greeting that uses the signed-in Spotify profile name when it is available. */
@Composable
fun homeGreeting(profileName: String?): String {
    val hour = LocalTime.now().hour
    val base = when (hour) {
        in 5..11 -> stringResource(R.string.home_greeting_morning)
        in 12..17 -> stringResource(R.string.home_greeting_afternoon)
        else -> stringResource(R.string.home_greeting_evening)
    }
    val name = profileName?.trim().orEmpty()
    return if (name.isBlank()) base else stringResource(R.string.home_greeting_named, base, name)
}
