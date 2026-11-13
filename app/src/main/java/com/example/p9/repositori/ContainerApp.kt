package com.example.p9.repositori

import android.content.Context
import com.example.p9.room.DatabaseSiswa

interface AppContainer {
    val repositoriSiswa: RepositoriSiswa
}

class DefaultAppContainer(
    private val context: Context
) : AppContainer {

    override val repositoriSiswa: RepositoriSiswa by lazy {
        OfflineRepositoriSiswa(
            DatabaseSiswa.getDatabase(context).siswaDao()
        )
    }
}