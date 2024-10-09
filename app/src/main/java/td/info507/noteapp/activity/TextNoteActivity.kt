package td.info507.noteapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import td.info507.noteapp.R
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.TextNoteStorage

class TextNoteActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        updateTextNote()
    }

    override fun onDestroy() {
        super.onDestroy()
        val title = findViewById<EditText>(R.id.title_note).text.toString()
        val text = findViewById<EditText>(R.id.text_note).text.toString()

        if (title != "" || text != "" ){
            TextNoteStorage.get(applicationContext).insert(
                TextNote(
                    0,
                    findViewById<EditText>(R.id.title_note).text.toString(),
                    findViewById<EditText>(R.id.text_note).text.toString()
                )
            )
        }

        // TODO: notifier le recycleView qu'une nouvelle note est ajouté + ne s'enregistre pas tout les cas (taskkiller)
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