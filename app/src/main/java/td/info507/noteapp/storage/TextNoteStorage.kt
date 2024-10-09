package td.info507.noteapp.storage

import android.content.Context
import android.content.SharedPreferences
import android.text.Editable
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.utility.Storage

object TextNoteStorage {
    private const val TITLE = "title"
    private const val TEXT = "text"

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("td.info507.noteapp.preferences", Context.MODE_PRIVATE)
    }

    fun getTitle(context: Context): Editable?{
        val title = getPreferences(context).getString(TITLE, "")
        return Editable.Factory.getInstance().newEditable(title) // string to editable
    }

    fun setTitle(context: Context, prefTitle: String) {
        getPreferences(context).edit().putString(TITLE, prefTitle).apply()
    }

    fun getText(context: Context): Editable?{
        val text = getPreferences(context).getString(TEXT,"")
        return Editable.Factory.getInstance().newEditable(text) // string to editable
    }

    fun setText(context: Context, prefText: String) {
        getPreferences(context).edit().putString(TEXT, prefText).apply()
    }
}