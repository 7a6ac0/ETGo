package tabacowang.me.etgo.api.repository

import io.reactivex.Observable
import tabacowang.me.etgo.api.model.GoogleResponse
import tabacowang.me.etgo.api.network.ApiService

interface DataRepository {
    fun getData(query: String): Observable<GoogleResponse>
}

class DataRepositoryImpl(
    private val apiService: ApiService
) : DataRepository {
    override fun getData(query: String) = apiService.getData(query)
}