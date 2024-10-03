package td.info507.noteapp.model

class Note(
    val id: Int,
    val title: String,
    val content: String
) {
    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val CONTENT = "content"
    }
}
