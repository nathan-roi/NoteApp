package td.info507.noteapp.utility

import android.content.Context
import android.content.SharedPreferences
import td.info507.myppoker.storage.utility.Storage
import td.info507.noteapp.FolderJSONFileStorage
import td.info507.noteapp.model.Folder

object FolderStorage {
    private const val LOGIN = "login"
    private const val STORAGE = "storage"
    const val NONE = 0
    const val DATA_BASE = 1
    const val FILE_JSON = 2

    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences("com.td_info507.noteapp.preferences", Context.MODE_PRIVATE)
    }

    fun getLogin(context: Context): String? {
        return getPreferences(context).getString(LOGIN, "aero")
    }

    fun setLogin(context: Context, prefLogin: String) {
        getPreferences(context).edit().putString(LOGIN, prefLogin).apply()
    }

    fun getStorage(context: Context): Int {
        return getPreferences(context).getInt(STORAGE, NONE)
    }

    fun setStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<Folder> {
        return FolderJSONFileStorage(context)
    }
}