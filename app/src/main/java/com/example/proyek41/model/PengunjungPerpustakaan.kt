package com.example.proyek41.data.model

import com.google.gson.annotations.SerializedName

data class PengunjungPerpustakaan(
    @SerializedName("kode_kabupaten_kota") val kodeKabupatenKota: String,
    @SerializedName("kabupaten_kota") val kabupatenKota: String,
    @SerializedName("tahun") val tahun: Int,
    @SerializedName("jumlah_rata_rata_pengunjung") val jumlahRataRataPengunjung: Int
)
