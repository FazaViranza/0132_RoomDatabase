package com.example.p9.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.platform.LocalContext
import com.example.p9.AplikasiSiswa
import com.example.p9.R
import com.example.p9.viewmodel.EntryUiState
import com.example.p9.viewmodel.EntryViewModel
import com.example.p9.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

@Composable
fun EntrySiswaScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EntryViewModel = viewModel(
        factory = PenyediaViewModel(
            (LocalContext.current.applicationContext as AplikasiSiswa).container
        )
    )
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = stringResource(R.string.entry_siswa)
            )

            OutlinedTextField(
                value = uiState.nama,
                onValueChange = {
                    viewModel.updateUiState(
                        uiState.copy(nama = it)
                    )
                },
                label = {
                    Text(stringResource(R.string.nama))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = uiState.alamat,
                onValueChange = {
                    viewModel.updateUiState(
                        uiState.copy(alamat = it)
                    )
                },
                label = {
                    Text(stringResource(R.string.alamat))
                },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = uiState.telpon,
                onValueChange = {
                    viewModel.updateUiState(
                        uiState.copy(telpon = it)
                    )
                },
                label = {
                    Text(stringResource(R.string.telpon))
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            Text(
                text = stringResource(R.string.required_field)
            )

            Button(
                onClick = {
                    if (
                        uiState.nama.isNotBlank() &&
                        uiState.alamat.isNotBlank() &&
                        uiState.telpon.isNotBlank()
                    ) {
                        coroutineScope.launch {
                            viewModel.saveSiswa()
                            navigateBack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                enabled = uiState.nama.isNotBlank() &&
                        uiState.alamat.isNotBlank() &&
                        uiState.telpon.isNotBlank()
            ) {
                Text(stringResource(R.string.btn_submit))
            }
        }
    }
}