// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
// BETA, isolado: não é usado pelo app atual. Ativar só no build .dev.
package com.music.spotui.connect.beta

import android.content.Context
import android.content.Intent
import android.net.Uri
import net.openid.appauth.AuthState
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues

/**
 * Login OAuth (PKCE) do Spotify pelo navegador padrão do aparelho (Custom Tabs, via AppAuth).
 * Guarda o [AuthState] (access + refresh token) em SharedPreferences privadas.
 */
object SpotifyPkceLogin {
    // Trocar pelo client ID do app criado no Spotify Developer Dashboard.
    const val CLIENT_ID = "017356b9cce94672beeb2fbaafa20f47"
    const val REDIRECT = "shinyfly://callback"

    val SCOPES = listOf(
        "streaming", "user-read-email", "user-read-private",
        "user-library-read", "user-read-playback-state", "user-modify-playback-state",
    )

    private val config = AuthorizationServiceConfiguration(
        Uri.parse("https://accounts.spotify.com/authorize"),
        Uri.parse("https://accounts.spotify.com/api/token"),
    )

    private const val PREFS = "spotify_beta_auth"
    private const val KEY_STATE = "auth_state"

    fun loginIntent(context: Context): Intent {
        val request = AuthorizationRequest.Builder(config, CLIENT_ID, ResponseTypeValues.CODE, Uri.parse(REDIRECT))
            .setScopes(SCOPES)
            .build()
        val service = AuthorizationService(context)
        val intent = service.getAuthorizationRequestIntent(request)
        service.dispose()
        return intent
    }

    /** Chamar com o Intent devolvido ao Activity de retorno. onDone recebe o resultado (ok e mensagem). */
    fun handleResult(context: Context, data: Intent?, onDone: (ok: Boolean, message: String) -> Unit) {
        if (data == null) return onDone(false, "Login cancelado")
        val response = AuthorizationResponse.fromIntent(data)
        val error: AuthorizationException? = AuthorizationException.fromIntent(data)
        val state = AuthState(config)
        state.update(response, error)
        if (response == null) return onDone(false, error?.errorDescription ?: "Falha no login")

        val service = AuthorizationService(context)
        service.performTokenRequest(response.createTokenExchangeRequest()) { tokenResponse, ex ->
            state.update(tokenResponse, ex)
            service.dispose()
            if (tokenResponse != null) {
                save(context, state)
                onDone(true, "Conectado")
            } else {
                onDone(false, ex?.errorDescription ?: "Falha ao trocar o código por token")
            }
        }
    }

    /** Entrega um access token válido (renova sozinho se estiver vencido). */
    fun withFreshToken(context: Context, onToken: (token: String?, error: String?) -> Unit) {
        val state = load(context) ?: return onToken(null, "Não conectado")
        val service = AuthorizationService(context)
        state.performActionWithFreshTokens(service) { accessToken, _, ex ->
            save(context, state)
            service.dispose()
            onToken(accessToken, ex?.errorDescription)
        }
    }

    fun logout(context: Context) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().remove(KEY_STATE).apply()
    }

    private fun save(context: Context, state: AuthState) {
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit()
            .putString(KEY_STATE, state.jsonSerializeString()).apply()
    }

    private fun load(context: Context): AuthState? =
        context.getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(KEY_STATE, null)
            ?.let { runCatching { AuthState.jsonDeserialize(it) }.getOrNull() }
}
