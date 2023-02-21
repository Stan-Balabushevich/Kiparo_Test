package id.slava.nt.kiparotest.models

data class NewsDomainModel (
    val id: Int? = 0,
    val title: String? = "",
    val description: String? = "",
    val date: String? = "",
    val keywords: List<String>? = emptyList(),
    val visible: Boolean? = true) {


    fun doesMatchSearchQuery(query: String): Boolean {
//        var isMatching = false
//
//        keywords?.forEach { word ->
//            isMatching = word.contains(query, ignoreCase = true)
//        }
//        return isMatching

        return title?.contains(query, ignoreCase = true) ?: false
    }
}
