package com.example.proyek41.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.proyek41.data.AppDatabase
import com.example.proyek41.data.local.entity.DataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DataViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getDatabase(application).dataDao()
    val dataList: LiveData<List<DataEntity>> = dao.getAll()

    private val _selectedData = MutableStateFlow<DataEntity?>(null)
    val selectedData = _selectedData.asStateFlow()

    fun insertData(
        kodeProvinsi: String,
        namaProvinsi: String,
        kodeKabupatenKota: String,
        namaKabupatenKota: String,
        total: String,
        satuan: String,
        tahun: String
    ) {
        viewModelScope.launch {
            val totalValue = total.toDoubleOrNull() ?: 0.0
            val tahunValue = tahun.toIntOrNull() ?: 0
            dao.insert(
                DataEntity(
                    kodeProvinsi = kodeProvinsi,
                    namaProvinsi = namaProvinsi,
                    kodeKabupatenKota = kodeKabupatenKota,
                    namaKabupatenKota = namaKabupatenKota,
                    total = totalValue,
                    satuan = satuan,
                    tahun = tahunValue
                )
            )
        }
    }

    fun updateData(data: DataEntity) {
        viewModelScope.launch {
            dao.update(data)
            println("Database updated!") // Log tambahan
        }
    }

    fun deleteData(id: Long) {
        viewModelScope.launch {
            val data = dao.getById(id)
            if (data != null) {
                dao.delete(data)
                println("Deleted data: $data") // Log tambahan
            }
        }
    }

    suspend fun getDataById(id: Long): DataEntity? {
        return withContext(Dispatchers.IO) {
            dao.getById(id)
        }
    }

    fun loadSelectedData(id: Long) {
        viewModelScope.launch {
            val data = dao.getById(id)
            _selectedData.value = data
        }
    }
}
