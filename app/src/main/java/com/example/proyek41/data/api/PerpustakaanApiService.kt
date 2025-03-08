package com.example.proyek41.data.api

import com.example.proyek41.data.model.PengunjungPerpustakaan
import retrofit2.http.GET

interface PerpustakaanApiService {
    @GET("api-backend/bigdata/dispusipda/od_18308_jml_rata_rata_pengunjung_prpstkn_umum_per_hari__ka_v1")
    suspend fun getPengunjungPerpustakaan(): List<PengunjungPerpustakaan>
}
