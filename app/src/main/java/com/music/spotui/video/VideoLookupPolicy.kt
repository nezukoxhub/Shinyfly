// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.video

/** Saver skips automatic app-resume lookup; a visible player or video intent still requests it. */
internal fun videoLookupOnResumeAllowed(
    allowAutomatic: Boolean,
    wantVideo: Boolean,
    videoOn: Boolean,
    playerVisible: Boolean,
): Boolean = allowAutomatic || wantVideo || videoOn || playerVisible
