// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.data.entity

@kotlinx.serialization.Serializable
data class ArtistsModel(
    val name : String,
    val coverUri : String,
    // Real Spotify artist id — open the EXACT artist (so "RAM" doesn't resolve to
    // "Rammstein" via a fuzzy name search). Empty when unknown.
    val id : String = "",
){
    constructor() : this("", "", "")
}
