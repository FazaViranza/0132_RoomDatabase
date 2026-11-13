package com.example.p9.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.p9.AplikasiSiswa
import com.example.p9.R
import com.example.p9.room.Siswa
import com.example.p9.viewmodel.HomeViewModel
import com.example.p9.viewmodel.PenyediaViewModel

@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = PenyediaViewModel(
            (androidx.compose.ui.platform.LocalContext.current.applicationContext
                    as AplikasiSiswa).container
        )
    )
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.entry_siswa)
                )
            }
        }
    ) { innerPadding ->
        if (homeUiState.daftarSiswa.isEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.deskripsi_no_item),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(homeUiState.daftarSiswa) { siswa ->
                    SiswaItem(siswa = siswa)
                }
            }
        }
    }
}

@Composable
fun SiswaItem(
    siswa: Siswa,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = "${stringResource(R.string.nama1)} ${siswa.nama}",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "${stringResource(R.string.alamat1)} ${siswa.alamat}"
            )

            Text(
                text = "${stringResource(R.string.telpon1)} ${siswa.telpon}"
            )
        }
    }
}