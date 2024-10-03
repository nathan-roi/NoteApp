package td.info507.noteapp.model

class GroupPhoto(
    val id: Int,
    val title: String,
    val photos: List<Photo> = emptyList()
) {
    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val PHOTOS = "photos"
    }
}