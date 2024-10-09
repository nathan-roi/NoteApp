package td.info507.noteapp.request

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import td.info507.noteapp.R

class NoteListRequest(private val context: Context) {
        init {
            val request = JsonObjectRequest(
                Request.Method.GET,
                "http://51.68.91.213/gr-2-3/note_list.json",
                null,
                { res ->
                    Log.d("REQUESTTT", res.toString())
                    Toast.makeText(context, R.string.success, Toast.LENGTH_SHORT).show()},
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

}