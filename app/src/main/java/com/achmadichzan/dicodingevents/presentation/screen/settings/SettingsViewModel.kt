package com.achmadichzan.dicodingevents.presentation.screen.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadichzan.dicodingevents.data.worker.ReminderWorker
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val repository: EventRepository,
) : ViewModel() {

    val isDarkThemeEnabled: StateFlow<Boolean> = repository.getThemeSetting()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = false
        )

    fun saveThemeSettings(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            repository.saveThemeSetting(isDarkModeActive)
        }
    }

    private val _isReminderEnabled = MutableStateFlow(false)
    val isReminderEnabled: StateFlow<Boolean> = _isReminderEnabled

    fun setReminderEnabled(enabled: Boolean, context: Context) {
        _isReminderEnabled.update { enabled }
        if (enabled) {
            ReminderWorker.schedule(context)
        } else {
            ReminderWorker.cancel(context)
        }
    }
}