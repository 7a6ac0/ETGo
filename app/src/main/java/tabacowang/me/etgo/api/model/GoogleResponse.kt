package tabacowang.me.etgo.api.model

import com.google.gson.annotations.SerializedName

data class GoogleResponse(
    val items: List<Items>?
)

data class Items(
    val link: String?,
    val pageMap: PageMap?
)

data class PageMap(
    val cse_thumbnail: List<CseThumbnail>?,
    val metatags: List<MetaTags>
)

data class CseThumbnail(
    val src: String?,
    val width: String?,
    val height: String?
)

data class MetaTags(
    @SerializedName("og:title") val title: String?,
    @SerializedName("og:description") val description: String?
)