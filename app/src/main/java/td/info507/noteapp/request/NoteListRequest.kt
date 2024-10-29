package td.info507.noteapp.request

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import td.info507.noteapp.R
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.TextNoteStorage
import td.info507.noteapp.storage.Updatable

class NoteListRequest(private val context: Context, private val updatable: Updatable){
    init {
            val request = JsonObjectRequest(
                Request.Method.GET,
                "http://51.68.91.213/gr-2-3/note_list.json",
                null,
                { res ->
                    refresh(res)
                    updatable.update()
                    Log.d("REQUESTTT", res.toString())
                    Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()

                },

                { err ->
                    // Gestion des erreurs
                    err.networkResponse?.let {
                        Log.d("REQUESTTT", "Erreur: ${it.statusCode}, Data: ${String(it.data)}")
                    } ?: run {
                        Log.d("REQUESTTT", err.toString())
                    }
                    Toast.makeText(context, R.string.error, Toast.LENGTH_SHORT).show()
                }
            )

            val queue = Volley.newRequestQueue(context)
            queue.add(request)
        }

    private fun refresh(res: JSONObject) {
        delete()
        insert(res)
    }

    private fun delete(){
        for (note in TextNoteStorage.get(context).findAll()){
            if (note.parent_folder == 2){
                TextNoteStorage.get(context).delete(note.id)
            }
        }
    }

    private fun insert(json: JSONObject){
        val notes = json.getJSONArray("listNotes")
        for (i in 0 until notes.length()){
            val note = notes.getJSONObject(i)
            TextNoteStorage.get(context).insert(
                TextNote(0,
                    note.getString(TextNote.TITLE),
                    note.getString(TextNote.TEXT),
                    2,
                    note.getBoolean(TextNote.FAVORITE)
                )
            )
        }
    }
}