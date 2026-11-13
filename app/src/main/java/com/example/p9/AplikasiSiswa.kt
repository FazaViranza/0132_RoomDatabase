package com.example.p9

import android.app.Application
import com.example.p9.repositori.AppContainer
import com.example.p9.repositori.DefaultAppContainer

class AplikasiSiswa : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}