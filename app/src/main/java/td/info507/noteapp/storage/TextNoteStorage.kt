package td.info507.noteapp.storage

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import android.util.Log
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.utility.Storage

object TextNoteStorage {

    fun get(context: Context): Storage<TextNote>{
        return TextNoteJSONFileStorage(context)
    }
}