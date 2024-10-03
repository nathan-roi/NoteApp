package td.info507.noteapp.model

class Photo(
    val id: Int,
    val url: String,
    val description: String? = null
) {
    companion object {
        const val ID = "id"
        const val URL = "url"
        const val DESCRIPTION = "description"
    }
}