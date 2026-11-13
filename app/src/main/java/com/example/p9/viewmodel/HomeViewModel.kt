package com.example.p9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p9.repositori.RepositoriSiswa
import com.example.p9.room.Siswa
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val daftarSiswa: List<Siswa> = emptyList()
)

class HomeViewModel(
    repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    val homeUiState: StateFlow<HomeUiState> =
        repositoriSiswa.getAllSiswaStream()
            .map { HomeUiState(daftarSiswa = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState()
            )
}