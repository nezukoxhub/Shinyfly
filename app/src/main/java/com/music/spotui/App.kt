// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui

import com.music.spotui.ui.theme.tone
import com.music.spotui.ui.theme.AppText

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.heightIn
import com.music.spotui.ui.components.pressable
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.background
import android.annotation.SuppressLint
import android.os.Build
import androidx.compose.material3.Scaffold
import androidx.lifecycle.repeatOnLifecycle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.music.spotui.ui.navigation.MainBottomNavigation
import com.music.spotui.ui.navigation.MyNavHost
import com.music.spotui.ui.navigation.navigateSingleTop


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun App() {
    val navController = rememberNavController()

    // Debug and beta builds: `myspoti-cli nav <route>` (see CliReceiver).
    LaunchedEffect(Unit) { com.music.spotui.cli.CliReceiver.nav.collect { r -> runCatching { navController.navigate(r) } } }
    // The playlists at the top of the library, fetched ahead on Wi-Fi (see PagePrefetch).
    LaunchedEffect(Unit) { com.music.spotui.data.api.PagePrefetch.start() }

    // A collaborative-playlist invitation was opened (see InviteLinks): its playlist page offers "Join".
    val invite by com.music.spotui.data.api.InviteLinks.pending.collectAsState()
    LaunchedEffect(invite) {
        val i = invite?.takeIf { !it.opened } ?: return@LaunchedEffect
        app.myspoti.host.android.MyspotiRuntime.api.ready.first { it }
        i.opened = true
        navController.navigateSingleTop(com.music.spotui.ui.navigation.playlistRoute(i.playlistId))
    }

    // A Blend invitation was opened (see InviteLinks): who invites, and "Join", over the app.
    val blendLink by com.music.spotui.data.api.InviteLinks.blend.collectAsState()
    blendLink?.let { link ->
        com.music.spotui.ui.screens.BlendInviteSheet(link, navController) {
            if (com.music.spotui.data.api.InviteLinks.blend.value === link) com.music.spotui.data.api.InviteLinks.blend.value = null
        }
    }

    // Genius's explanations of the song playing, read while it starts: ready when the lyrics open.
    val geniusLang = com.music.spotui.ui.components.readerLanguage()
    val playingUri = com.music.spotui.di.SongPlayer.nowPlaying.value.uri
    LaunchedEffect(playingUri) {
        if (!playingUri.startsWith("spotify:track:")) return@LaunchedEffect
        kotlinx.coroutines.delay(2_000) // the song's own loads first
        val st = com.music.spotui.di.Graph.currentSongState
        com.music.spotui.data.api.GeniusMeaning.prefetch(playingUri.substringAfterLast(':'), st.title.value, st.singer.value, geniusLang)
    }
    // A shared link to a list / artist / podcast: its page, over whatever was open.
    val pageLink by com.music.spotui.data.api.InviteLinks.page.collectAsState()
    LaunchedEffect(pageLink) {
        val uri = pageLink ?: return@LaunchedEffect
        com.music.spotui.data.api.InviteLinks.page.value = null
        val id = uri.substringAfterLast(':')
        com.music.spotui.ui.navigation.PlayerSheet.close()
        runCatching {
            navController.navigateSingleTop(when (uri.split(':')[1]) {
                "playlist" -> com.music.spotui.ui.navigation.playlistRoute(id)
                "album" -> com.music.spotui.ui.navigation.albumRoute("", "", id)
                "artist" -> com.music.spotui.ui.navigation.artistRoute("artist", id)
                else -> com.music.spotui.ui.navigation.showRoute(id)
            })
        }
    }
    val shareTarget by com.music.spotui.ui.components.SpotifyCode.request.collectAsState()
    shareTarget?.let { t -> com.music.spotui.ui.components.SpotifyCodeSheet(t) { com.music.spotui.ui.components.SpotifyCode.request.value = null } }

    // The look (Settings > Display) switches the shell live. The nav host keeps one place in the
    // tree in both looks, so a switch keeps the back stack, the pages' state and the playback.
    val immersive = com.music.spotui.ui.navigation.isImmersive()
    val haze = com.music.spotui.ui.immersive.shell.rememberImmersiveHazeState()
    // Liquid Glass (Settings > Display): the pages record themselves for the glass to refract.
    // Not provided while it is off or unsupported, so nothing is recorded for no reader.
    val glassPage = com.music.spotui.ui.immersive.shell.rememberImmersiveBackdrop()
    val liquidGlass = immersive && com.music.spotui.ui.immersive.shell.rememberLiquidGlassActive()
    // Without it (Android 12+), the frost blurs the same recording, which follows page and tab
    // transitions frame by frame where Haze lagged behind.
    val frostPage = immersive && !liquidGlass && android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S
    // The floating chrome's fold (iOS 26): pages scroll it through immersiveChromeScroll().
    val chrome = com.music.spotui.ui.immersive.shell.rememberImmersiveChromeState()
    androidx.compose.runtime.CompositionLocalProvider(
        com.music.spotui.ui.look.LocalReduceMotion provides com.music.spotui.ui.look.rememberReduceMotion(),
        com.music.spotui.ui.immersive.shell.LocalImmersiveHaze provides if (immersive) haze else null,
        com.music.spotui.ui.immersive.shell.LocalImmersiveBackdrop provides if (liquidGlass) glassPage else null,
        com.music.spotui.ui.immersive.shell.LocalImmersiveFrostPage provides if (frostPage) glassPage else null,
        com.music.spotui.ui.immersive.shell.LocalImmersiveChrome provides if (immersive) chrome else null,
    ) {
    // Large windows (tablets, foldables, DeX): Classic's tabs in a rail at the side, the
    // Immersive tab bar on the left edge (ImmersiveBottomChrome); phones keep the bottom bars.
    val large = com.music.spotui.ui.components.windowSize().large
    androidx.compose.foundation.layout.Box(Modifier.fillMaxSize()) {
      androidx.compose.foundation.layout.Row(Modifier.fillMaxSize()) {
        // Before the Scaffold, in a group of its own: the nav host keeps its place when the
        // window grows or shrinks past the threshold (back stack and pages kept).
        if (large && !immersive) com.music.spotui.ui.navigation.MainNavigationRail(navController)
        Scaffold(
            modifier = Modifier.weight(1f),
            bottomBar = {
                if (!immersive) MainBottomNavigation(navController)
            }
        ) {
            MyNavHost(
                navHostController = navController,
                // Immersive: each page marks its own list as what the glass bars blur (a source on
                // the whole host would hold the pages' own top bars too).
                modifier = if (immersive) Modifier.background(com.music.spotui.ui.immersive.Immersive.Page) else Modifier,
            )
        }
      }
        if (immersive) ImmersiveShell(navController)
        // Over the pages and the bottom bar: pulling it down shows them, already in place.
        else com.music.spotui.ui.navigation.PlayerOverlay(navController)
        // "Not interested" and its Undo, over the pages and the players.
        com.music.spotui.ui.components.HiddenSongUndo()
        // A sponsor read skipped in a podcast (SponsorBlock), and its Undo.
        com.music.spotui.podcast.SponsorSkipUndo()
    }
    }
}



/**
 * The Immersive look's floating chrome: the mini player capsule over the tab bar (both hidden
 * on the full-screen routes; folded into one row while a page scrolls down), and its player over
 * everything.
 */
@Composable
private fun ImmersiveShell(navController: androidx.navigation.NavHostController) {
    androidx.compose.foundation.layout.Box(Modifier.fillMaxSize()) {
        com.music.spotui.ui.immersive.shell.ImmersiveBottomChrome(
            navController,
            miniPlayer = { m -> com.music.spotui.ui.immersive.player.ImmersiveMiniPlayer(m) },
            modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter),
        )
        com.music.spotui.ui.immersive.player.ImmersivePlayerOverlay(navController)
    }
}

