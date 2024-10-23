package td.info507.noteapp.model

public final class TextNote(val id: Int, val title: String, val text: String, val parent_folder: Int, val favorite: Boolean) {
    companion object{
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val PARENT_FOLDER = "parent_folder"
        const val FAVORITE = "favorite"
    }
}