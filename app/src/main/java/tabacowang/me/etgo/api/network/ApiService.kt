package tabacowang.me.etgo.api.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import tabacowang.me.etgo.api.model.GoogleResponse

interface ApiService {
    @GET("customsearch/v1?cx=0b87c2d2cf7a65e38&key=AIzaSyAmE-4vFBHgeEYVG0mPUAUWCVuwP8t0TrY")
    fun getData(@Query("q") keyword: String): Observable<GoogleResponse>
}