package id.slava.nt.kiparotest.data.network

import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api2.kiparo.com/"
private const val END_POINT_JSON = "static/it_news.json"
private const val END_POINT_XML = "static/it_news.xml"


private val retrofitJson = Retrofit.Builder()
    .addConverterFactory(JacksonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface JsonApiService{

    @GET(END_POINT_JSON)
    suspend fun getNewsList(): RootDataJson?

}

object JsonApi{
    val retrofitService : JsonApiService by lazy {
        retrofitJson.create(JsonApiService::class.java) }
}

private val retrofitXml = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface XmlApiService{

    @GET(END_POINT_XML)
    suspend fun getNewsList(): String?

}

object XmlApi{
    val retrofitService : XmlApiService by lazy {
        retrofitXml.create(XmlApiService::class.java) }
}