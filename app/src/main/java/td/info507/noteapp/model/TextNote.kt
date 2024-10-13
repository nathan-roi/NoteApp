package td.info507.noteapp.model

public final class TextNote(val id: Int, val title: String, val text: String, val parent_folder: String = "allNotes") {
    companion object{
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val PARENT_FOLDER = "parent_folder"
    }
}