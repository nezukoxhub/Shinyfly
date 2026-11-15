// Copyright (C) 2026 Bruno Henrryke Marcelino de Souza
// SPDX-License-Identifier: GPL-3.0-only
package com.music.spotui.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.myspoti.engine.BuildConfig
import app.myspoti.engine.R
import com.music.spotui.ui.theme.AppText
import com.music.spotui.ui.theme.tone

private const val CREATOR_INSTAGRAM = "https://www.instagram.com/kryptobrute/"

private fun openUrl(context: Context, url: String) {
    runCatching {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}

/** Project information and the creator's public contact. */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutSheet(onDismiss: () -> Unit) {
    val context = androidx.compose.ui.platform.LocalContext.current
    com.music.spotui.extensions.ProtectedSurfaceEffect(app.myspoti.extensions.ProtectedSurface.ABOUT)
    LookSheet(
        onDismissRequest = onDismiss,
        sheetState = androidx.compose.material3.rememberModalBottomSheetState(skipPartiallyExpanded = true),
        containerColor = tone(0xFF282828),
    ) {
        Column(
            Modifier.navigationBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(start = 20.dp, end = 20.dp, bottom = 28.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = Modifier.size(56.dp).clip(RoundedCornerShape(14.dp))
                        .background(tone(0xFF121212)),
                )
                Column(Modifier.padding(start = 14.dp)) {
                    Text("Shinyfly", color = AppText, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Text(
                        stringResource(R.string.settings_item_version_title) + " " + BuildConfig.VERSION_NAME,
                        color = AppText,
                        fontSize = 13.sp,
                    )
                }
            }
            Text(
                stringResource(R.string.shinyfly_about_description),
                color = AppText,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                modifier = Modifier.padding(top = 18.dp, bottom = 12.dp),
            )
            Column(
                Modifier.fillMaxWidth().clickable { openUrl(context, CREATOR_INSTAGRAM) }
                    .padding(vertical = 12.dp),
            ) {
                Text(stringResource(R.string.shinyfly_contact_title), color = AppText, fontSize = 16.sp)
                Text(
                    stringResource(R.string.shinyfly_contact_detail),
                    color = AppText,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 3.dp),
                )
            }
        }
    }
}
