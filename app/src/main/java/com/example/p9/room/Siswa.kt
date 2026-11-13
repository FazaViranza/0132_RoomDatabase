package com.example.p9.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_siswa")
data class Siswa(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val alamat: String,
    val telpon: String
)