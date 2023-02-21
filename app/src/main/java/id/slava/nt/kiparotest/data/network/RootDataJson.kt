package id.slava.nt.kiparotest.data.network

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import id.slava.nt.kiparotest.models.NewsDomainModel

@JsonIgnoreProperties(ignoreUnknown = true)
data class RootDataJson (
    @JsonProperty("name")
    val name: String? = "",
    @JsonProperty("location")
    val location: String? = "",
    @JsonProperty("news")
    val news: List<NewsDataJson> = emptyList())


@JsonIgnoreProperties(ignoreUnknown = true)
data class NewsDataJson (
    @JsonProperty("id")
    val id: Int? = 0,
    @JsonProperty("title")
    val title: String? = "",
    @JsonProperty("description")
    val description: String? = "",
    @JsonProperty("date")
    val date: String? = "",
    @JsonProperty("keywords")
    val keywords: List<String>? = emptyList(),
    @JsonProperty("visible")
    val visible: Boolean? = true
)

fun RootDataJson.asDomainModel(): List<NewsDomainModel>{
    return news.map { news ->
        NewsDomainModel(
            id = news.id,
            title = news.title,
            description = news.description,
            date = news.date,
            keywords = news.keywords,
            visible = news.visible
        )
    }
}


