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

        val extraNote = intent.getIntExtra(MainActivity.EXTRA_NOTE, 0)
        Log.d("NewNote", extraNote.toString())
        if (extraNote >= 0){
            val textNote = TextNoteStorage.get(applicationContext).find(intent.getIntExtra(MainActivity.EXTRA_NOTE, 0))!!
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

            val extraNote = intent.getIntExtra(MainActivity.EXTRA_NOTE, 0)

            if (extraNote >= 0) { // Si la note existe on supprime l'existante et on met à jour les nouvelles données
                val textNote = TextNoteStorage.get(applicationContext)
                    .find(intent.getIntExtra(MainActivity.EXTRA_NOTE, 0))!!
                val idCourant = textNote.id

                TextNoteStorage.get(applicationContext).delete(idCourant)
                TextNoteStorage.get(applicationContext).insert(
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


    private fun noteExist(idCourant: Int): Boolean {
        var exist = false

        val allNotes = TextNoteStorage.get(applicationContext).findAll()
        val allIds: MutableList<Int> = mutableListOf(allNotes.size)

        for (note in allNotes){
            allIds.add(note.id)
        }

        if (allIds.contains(idCourant)){
            exist = true
        }

        return exist
    }

    private fun updateTextNote(){
        var titleNote = findViewById<EditText>(R.id.title_note)
        var textNote = findViewById<EditText>(R.id.text_note)

        titleNote.text = TextNoteStorage.getTitle(applicationContext)
        titleNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(title: Editable?) {
                TextNoteStorage.setTitle(applicationContext, title.toString())
            }
        })

        textNote.text = TextNoteStorage.getText(applicationContext)
        textNote.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(text: Editable?) {
                TextNoteStorage.setText(applicationContext, text.toString())
            }
        })
    }
}