// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.extensions

import android.content.Context
import app.myspoti.extensions.ExtensionPackage
import app.myspoti.host.android.Logbook
import app.myspoti.host.android.MyspotiRuntime
import com.music.spotui.data.api.ProfileCache
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * The Lossless FLAC source is shipped with Shinyfly and installed by the app after sign-in.
 * Its package is local: first login and later download attempts never depend on a store URL.
 */
object DefaultExtensionInstaller {
    const val ID = "audio.lossless.flac"
    private const val ASSET = "extensions/lossless-flac.zip"
    private val installLock = Mutex()
    @Volatile private var watcher: Job? = null

    /** Starts one watcher for authenticated sessions. Repeated calls are harmless. */
    fun start(context: Context) {
        if (watcher?.isActive == true) return
        val app = context.applicationContext
        watcher = MyspotiRuntime.scope.launch {
            MyspotiRuntime.api.ready.collect { ready ->
                if (!ready) return@collect
                val userId = ProfileCache.awaitId(app, 20_000) ?: return@collect
                runCatching { ensureInstalled(app) }
                    .onFailure { Logbook.log("default extension: install failed: ${it.message}") }
            }
        }
    }

    /**
     * Ensures the bundled source is installed and enabled. Called after login and again just
     * before a download, so a transient first-install failure can recover without user setup.
     */
    suspend fun ensureInstalled(context: Context): Boolean = installLock.withLock {
        val app = context.applicationContext
        val current = Extensions.registry.value.find(ID)
        if (current?.enabled == true && Extensions.hasDownloadSource()) return@withLock true

        val zip = app.assets.open(ASSET).use { it.readBytes() }
        val pkg = ExtensionPackage.read(zip)
        require(pkg.manifest.id == ID) { "unexpected bundled extension id" }

        val now = Extensions.registry.value.find(ID)
        if (now != null && now.versionCode >= pkg.manifest.versionCode) {
            val manifest = Extensions.manifest(ID) ?: pkg.manifest
            Extensions.setEnabled(ID, true, Extensions.clashes(manifest)).join()
        } else {
            // The bundled source is the chosen default. It takes priority over conflicting
            // third-party sources; no other extension is removed from storage.
            Extensions.install(
                zip = zip,
                p = pkg,
                granted = Grants.keys(pkg.manifest),
                source = "bundled",
                clashes = Extensions.clashes(pkg.manifest),
                keepNew = { true },
                noticeAccepted = true,
                accepted = true,
            )
        }
        val installed = Extensions.registry.value.find(ID)
        installed?.enabled == true && Extensions.hasDownloadSource()
    }
}
