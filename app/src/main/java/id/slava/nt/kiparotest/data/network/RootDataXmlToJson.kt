package id.slava.nt.kiparotest.data.network

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import id.slava.nt.kiparotest.models.NewsDomainModel
import com.fasterxml.jackson.annotation.JsonFormat.Feature

@JsonIgnoreProperties(ignoreUnknown = true)
data class RootDataXmlToJson (
    @JsonProperty("name")
    var name: String = "",
    @JsonProperty("location")
    var location: String = "",

    @JsonProperty("news")
    var news: NewsRootXmlToJson = NewsRootXmlToJson()
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class NewsRootXmlToJson(
    @JsonProperty("element")
    val elements: List<NewsDataXmlToJson> = emptyList()
)

@JsonIgnoreProperties(ignoreUnknown = true)
class NewsDataXmlToJson (

    @JsonProperty("id")
    val id: String? = "",
    @JsonProperty("title")
    val title: String? = "",
    @JsonProperty("description")
    val description: String? = "",
    @JsonProperty("date")
    val date: String? = "",
    @JsonProperty("keywords")
    val keywords: KeywordsXmlToJson? = null,
    @JsonProperty("visible")
    val visible: String? = ""
)

@JsonIgnoreProperties(ignoreUnknown = true)
class  KeywordsXmlToJson{
    @JsonProperty("element")
    @JsonFormat(with = [Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY])
    val elements: List<String>? = null
}

fun RootDataXmlToJson.asDomainModel(): List<NewsDomainModel>{
    return news.elements.map { news ->
        NewsDomainModel(
            id = news.id?.toInt(),
            title = news.title,
            description = news.description,
            date = news.date,
            keywords = news.keywords?.elements,
            visible = news.visible.toBoolean()
        )
    }
}