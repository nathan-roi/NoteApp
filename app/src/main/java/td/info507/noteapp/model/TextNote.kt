package td.info507.noteapp.model

import org.json.JSONObject

public final class TextNote(val id: Int, val title: String, val text: String, val parent_folder: Int, val favorite: Boolean) {
    companion object{
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
        const val PARENT_FOLDER = "parent_folder"
        const val FAVORITE = "favorite"

        fun fromJson(json: JSONObject): TextNote {
            return TextNote(
                id = json.getInt(ID),
                title = json.getString(TITLE),
                text = json.getString(TEXT),
                parent_folder = json.getInt(PARENT_FOLDER),
                favorite = json.getBoolean(FAVORITE)
            )
        }
    }
}