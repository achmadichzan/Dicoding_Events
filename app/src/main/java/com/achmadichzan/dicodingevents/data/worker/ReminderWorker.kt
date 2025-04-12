package com.achmadichzan.dicodingevents.data.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.achmadichzan.dicodingevents.R
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import java.net.URL
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.concurrent.TimeUnit

@HiltWorker
class ReminderWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: EventRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val event = repository.getReminderEvent()?.listEvents?.firstOrNull()
        val inputDateFormatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm:ss")
        val outputDateFormatter = DateTimeFormatter
            .ofPattern("EEEE, dd MMM yy HH:mm", Locale("in", "ID"))
        val UNKNOWN_DATE_STRING = "Unknown Date"

        val formattedDate = event?.beginTime
            ?.takeIf { it.isNotBlank() }
            ?.runCatching { LocalDateTime.parse(this, inputDateFormatter) }
            ?.mapCatching { parsedDateTime ->
                parsedDateTime.format(outputDateFormatter)
            }
            ?.getOrElse { exception ->
                exception.printStackTrace()
                UNKNOWN_DATE_STRING
            }
            ?: UNKNOWN_DATE_STRING

        event?.let {
            showNotification(
                title = it.name ?: "Unknown Event",
                date = formattedDate,
                imageUrl = it.imageLogo ?: ""
            )
        }
        return Result.success()
    }

    private suspend fun showNotification(title: String, date: String, imageUrl: String) {
        val context = applicationContext
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val imageBitmap = try {
            val url = URL(imageUrl)
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        val collapsedView = RemoteViews(
            context.packageName,
            R.layout.collapsed_notification
        ).apply {
            setTextViewText(R.id.collapsed_title, title)
        }

        val expandedView = RemoteViews(
            context.packageName,
            R.layout.expanded_notification
        ).apply {
            if (imageBitmap != null) {
                setImageViewBitmap(R.id.expanded_image, imageBitmap)
            }
            setTextViewText(R.id.expanded_title, date)
            setTextViewText(R.id.expanded_message, title)
        }

        val event = repository.getReminderEvent()?.listEvents?.firstOrNull()
        val intent = Intent(Intent.ACTION_VIEW, "app://event/${event?.id}".toUri()).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_event)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setCustomContentView(collapsedView)
            .setCustomBigContentView(expandedView)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
            .build()

        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)
        notificationManager.notify(1001, notification)
    }



    companion object {
        const val CHANNEL_ID = "event_reminder_channel"
        const val CHANNEL_NAME = "dicoding channel"

        fun schedule(context: Context) {
            val request = PeriodicWorkRequestBuilder<ReminderWorker>(1, TimeUnit.DAYS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .build()
                )
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                "daily_reminder",
                ExistingPeriodicWorkPolicy.REPLACE,
                request
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork("daily_reminder")
        }
    }
}
