package td.info507.noteapp.storage

import android.content.Context
import android.util.Log
import android.widget.EditText
import org.json.JSONException
import org.json.JSONObject
import org.w3c.dom.Text
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.utility.file.JSONFileStorage

class TextNoteJSONFileStorage(context: Context): JSONFileStorage<TextNote>(context, "text_note") {
    override fun create(id: Int, obj: TextNote): TextNote {
        return TextNote(id, obj.title, obj.text, obj.parent_folder, obj.favorite)
    }
    override fun objectToJson(id: Int, obj: TextNote): JSONObject {
        val json = JSONObject()
        json.put(TextNote.ID, id)
        json.put(TextNote.TITLE, obj.title)
        json.put(TextNote.TEXT, obj.text)
        json.put(TextNote.PARENT_FOLDER, obj.parent_folder)
        json.put(TextNote.FAVORITE, obj.favorite)

        return json
    }

    override fun jsonToObject(json: JSONObject): TextNote {
        return TextNote(
            json.getInt(TextNote.ID),
            json.getString(TextNote.TITLE),
            json.getString(TextNote.TEXT),
            json.getInt(TextNote.PARENT_FOLDER),
            json.getBoolean(TextNote.FAVORITE)
        )
    }
}