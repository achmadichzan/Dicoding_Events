package com.achmadichzan.dicodingevents.data.worker

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
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
            showNotification(it.name ?: "", formattedDate)
        }
        return Result.success()
    }

    private fun showNotification(title: String, date: String) {
        val manager = applicationContext
            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(applicationContext, "event_reminder_channel")
            .setSmallIcon(R.drawable.ic_event)
            .setContentTitle(title)
            .setStyle(NotificationCompat.BigTextStyle()
                .setBigContentTitle(title)
                .bigText(date))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        manager.notify(1001, notification)
    }

    companion object {
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
