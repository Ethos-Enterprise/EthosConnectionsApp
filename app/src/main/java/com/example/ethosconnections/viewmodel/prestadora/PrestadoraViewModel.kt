package com.example.ethosconnections.viewmodel.prestadora

import android.util.Log
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ethosconnections.models.Empresa
import com.example.ethosconnections.models.Prestadora
import com.example.ethosconnections.repositories.PrestadoraRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.util.UUID

class PrestadoraViewModel(private val repository: PrestadoraRepository): ViewModel() {
    val prestadoras = MutableLiveData(SnapshotStateList<Prestadora>())
    val prestadora = MutableLiveData<Prestadora>()
    val empresa = MutableLiveData<Prestadora>()
    val errorMessage = MutableLiveData<String>()

    fun getPrestadoras(token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getPrestadoras("Bearer $token")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        prestadoras.value!!.clear()
                        prestadoras.value!!.addAll(response.body() ?: emptyList())
                        errorMessage.value = ""

                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: "Erro desconhecido"
                    }
                }
            } catch (e: HttpException) {
                Log.e("PrestadoraViewModel", "Erro na HTTP: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido "
                }
            } catch (e: Exception) {
                Log.e("PrestadoraViewModel", "Excecao: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido"
                }
            }
        }
    }
    fun getPrestadoraPorId(id: UUID, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getPrestadoraPorId(id, "Bearer $token")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        prestadora.value = response.body()
                        errorMessage.value = ""
                    } else {
                        errorMessage.value = response.errorBody()?.string() ?: "Erro desconhecido"
                    }
                }
            } catch (e: HttpException) {
                Log.e("PrestadoraViewModel", "Erro na HTTP: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido "
                }
            } catch (e: Exception) {
                Log.e("PrestadoraViewModel", "Excecao: ${e.message}")
                withContext(Dispatchers.Main) {
                    errorMessage.value = e.message ?: "Erro desconhecido"
                }
            }
        }
    }

}
