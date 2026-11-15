// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.data.entity

/** The logged-in Spotify account, shown in the Library settings menu. */
data class AccountModel(
    val name: String = "",
    val email: String = "",
    val imageUrl: String = "",
    val plan: String = "",
)
