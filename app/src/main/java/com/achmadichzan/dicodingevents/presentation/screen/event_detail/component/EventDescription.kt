package com.achmadichzan.dicodingevents.presentation.screen.event_detail.component

import android.text.TextUtils
import android.text.util.Linkify
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.achmadichzan.dicodingevents.domain.model.Event
import com.google.android.material.textview.MaterialTextView

@Composable
fun EventDescription(
    modifier: Modifier = Modifier,
    event: Event?
) {
    var isExpanded by remember { mutableStateOf(false) }
    val spannedText = HtmlCompat.fromHtml(event?.description ?: "Tidak tersedia", 0)
    val currentTextColor = MaterialTheme.colorScheme.onSurface.toArgb()

    AndroidView(
        modifier = modifier.animateContentSize()
            .clickable { isExpanded = !isExpanded },
        factory = { context ->
            MaterialTextView(context).apply {
                maxLines = 10
                ellipsize = TextUtils.TruncateAt.END
                autoLinkMask = Linkify.WEB_URLS
                linksClickable = false
                setTextColor(currentTextColor)
            }
        },
        update = {
            it.text = spannedText
            it.maxLines = if (isExpanded) Int.MAX_VALUE else 10
        }
    )

    Text(
        text = if (isExpanded) "Sembunyikan" else "Lihat selengkapnya",
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(bottom = 10.dp)
            .clickable { isExpanded = !isExpanded }
    )
}