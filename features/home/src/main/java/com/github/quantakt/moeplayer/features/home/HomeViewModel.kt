package com.github.quantakt.moeplayer.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.quantakt.moeplayer.domain.usecase.GetAudioUrlUseCase
import com.github.quantakt.moeplayer.domain.usecase.GlobalSearchUseCase
import com.github.quantakt.moeplayer.model.SearchResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val globalSearchUseCase: GlobalSearchUseCase,
    private val getAudioUrlUseCase: GetAudioUrlUseCase,
) : ViewModel() {

    private val searchQuery = MutableStateFlow("")

    @OptIn(ExperimentalCoroutinesApi::class)
    private val searchResultFlow = searchQuery
        .mapLatest {
            if (it.isBlank())
                null
            else
                globalSearchUseCase(it)
        }


    val state: StateFlow<HomeScreenState> = combine(
        searchQuery,
        searchResultFlow,
        ::HomeScreenState
    ).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(10000),
        HomeScreenState()
    )

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    suspend fun getAudioUrl(animeTheme: SearchResult.AnimeTheme): String {
        return getAudioUrlUseCase(animeTheme.video.basename)
    }
}

