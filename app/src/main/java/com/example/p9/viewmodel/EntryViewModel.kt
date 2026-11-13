package com.example.p9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.p9.repositori.RepositoriSiswa
import com.example.p9.room.Siswa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class EntryUiState(
    val nama: String = "",
    val alamat: String = "",
    val telpon: String = ""
)

class EntryViewModel(
    private val repositoriSiswa: RepositoriSiswa
) : ViewModel() {

    private val _uiState = MutableStateFlow(EntryUiState())
    val uiState: StateFlow<EntryUiState> = _uiState.asStateFlow()

    fun updateUiState(entryUiState: EntryUiState) {
        _uiState.update { entryUiState }
    }

    suspend fun saveSiswa() {
        val currentState = uiState.value

        if (
            currentState.nama.isNotBlank() &&
            currentState.alamat.isNotBlank() &&
            currentState.telpon.isNotBlank()
        ) {
            repositoriSiswa.insertSiswa(
                Siswa(
                    nama = currentState.nama,
                    alamat = currentState.alamat,
                    telpon = currentState.telpon
                )
            )
        }
    }
}