package id.slava.nt.kiparotest

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper
import id.slava.nt.kiparotest.data.network.JsonApi
import id.slava.nt.kiparotest.data.network.RootDataXmlToJson
import id.slava.nt.kiparotest.data.network.XmlApi
import id.slava.nt.kiparotest.data.network.asDomainModel
import id.slava.nt.kiparotest.models.NewsDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()


    private val _news = MutableStateFlow(emptyList<NewsDomainModel>())
    @OptIn(FlowPreview::class)
    val news = searchText
        .debounce(500L)
        .onEach { _isSearching.update { true } }
        .combine(_news) { text, news ->
            if(text.isBlank()) {
                news.sortedBy { it.date }
            } else {
                // simulates network call
//                delay(2000L)
                news
                    .filter {
                    it.doesMatchSearchQuery(text) }
                    .sortedBy { it.date }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            _news.value
        )

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    fun getJsonNews(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _isSearching.update { true}
                    val news = JsonApi.retrofitService.getNewsList()
                if ( news != null){
                    _news.value = news.asDomainModel()
                    _isSearching.update { false }
                }
            } catch (e: Exception){
                Log.d("network exception", "Could not get remote data")
                _isSearching.update { false }
            }
        }
    }

    fun getXmlNews(){
        viewModelScope.launch(Dispatchers.IO) {
            var news: String? = ""
            try{
                _isSearching.update { true}
                news = XmlApi.retrofitService.getNewsList()
            }catch (e: Exception){
                Log.d("network exception", "Could not get remote data")
                _isSearching.update { false }
            }
            if ( news != null){
                val rootData = ObjectMapper()
                    //single value as array
                    .enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
                    .readValue(xmlToJsonObject(news), RootDataXmlToJson::class.java)
                _news.value = rootData.asDomainModel()
                _isSearching.update { false}
            }
        }
    }

    // xml string to json string converter
    private fun xmlToJsonObject(xml: String): String {
        val node = XmlMapper()
            .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
            .readTree(xml.toByteArray())
        val json = ObjectMapper()
            .writerWithDefaultPrettyPrinter()
            .writeValueAsString(node)
        return json
    }

}