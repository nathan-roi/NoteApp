package td.info507.noteapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import td.info507.noteapp.MainActivity
import td.info507.noteapp.R
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.TextNoteStorage

class TextNoteActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extraNote = intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0)

        if (extraNote >= 0){
            val textNote = TextNoteStorage.get(applicationContext).find(intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0))!!

            val title = findViewById<TextView>(R.id.title_note)
            val text = findViewById<TextView>(R.id.text_note)

            title.text = textNote.title
            text.text = textNote.text
        }

    }

    override fun onPause() {
        super.onPause()
        saveNote()
    }


    private fun saveNote() {
        val title = findViewById<EditText>(R.id.title_note).text.toString()
        val text = findViewById<EditText>(R.id.text_note).text.toString()

        if (title != "" || text != "") {

            val extraNote = intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0)

            if (extraNote >= 0) { // Si la note existe on update celle-ci
                val textNote = TextNoteStorage.get(applicationContext)
                    .find(intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0))!!
                val idCourant = textNote.id

                TextNoteStorage.get(applicationContext).update(
                    idCourant,
                    TextNote(
                        idCourant,
                        findViewById<EditText>(R.id.title_note).text.toString(),
                        findViewById<EditText>(R.id.text_note).text.toString()
                    )
                )
                Toast.makeText(applicationContext, "Saved !", Toast.LENGTH_SHORT).show()

            } else { // Si la note n'existe pas on en crée une nouvelle
                TextNoteStorage.get(applicationContext).insert(
                    TextNote(
                        0,
                        findViewById<EditText>(R.id.title_note).text.toString(),
                        findViewById<EditText>(R.id.text_note).text.toString()
                    )
                )
                Toast.makeText(applicationContext, "Saved !", Toast.LENGTH_SHORT).show()
            }
        }
    }
}