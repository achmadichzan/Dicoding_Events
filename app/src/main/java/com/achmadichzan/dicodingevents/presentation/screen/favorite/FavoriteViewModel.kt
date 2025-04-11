package com.achmadichzan.dicodingevents.presentation.screen.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.achmadichzan.dicodingevents.data.local.entity.FavoriteEventEntity
import com.achmadichzan.dicodingevents.domain.repository.EventRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val repository: EventRepository
): ViewModel() {

    private val _favoriteEvents = MutableStateFlow<List<FavoriteEventEntity>>(emptyList())
    val favoriteEvents: StateFlow<List<FavoriteEventEntity>> = _favoriteEvents

    fun loadAllFavorites() {
        viewModelScope.launch {
            repository.getAllFavorites()
                .filterNotNull()
                .collectLatest { favorites ->
                    _favoriteEvents.update { favorites }
                }
        }
    }

}