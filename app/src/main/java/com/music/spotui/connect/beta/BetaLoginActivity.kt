// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
// BETA, isolado: tela de teste do login pelo navegador. Só registrar no build .dev.
package com.music.spotui.connect.beta

import androidx.activity.ComponentActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class BetaLoginActivity : ComponentActivity() {

    private lateinit var status: TextView
    private val launcher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        SpotifyPkceLogin.handleResult(this, result.data) { ok, message ->
            status.text = if (ok) "✅ $message" else "❌ $message"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        status = TextView(this).apply { textSize = 16f; setPadding(48, 48, 48, 48) }
        val login = Button(this).apply {
            text = "Entrar com o navegador (teste)"
            setOnClickListener { launcher.launch(SpotifyPkceLogin.loginIntent(this@BetaLoginActivity)) }
        }
        val check = Button(this).apply {
            text = "Testar token"
            setOnClickListener {
                SpotifyPkceLogin.withFreshToken(this@BetaLoginActivity) { token, error ->
                    runOnUiThread {
                        status.text = if (token != null) "Token ok (${token.take(8)}…)" else "Erro: $error"
                    }
                }
            }
        }
        setContentView(LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            addView(login); addView(check); addView(status)
        })
    }
}
