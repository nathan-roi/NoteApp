package td.info507.noteapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import td.info507.noteapp.storage.TextNoteStorage

class TextNoteActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val title_note = findViewById<EditText>(R.id.title_note)
        val text_note = findViewById<EditText>(R.id.text_note)

        findViewById<EditText>(R.id.title_note).text = TextNoteStorage.getTitle(applicationContext)
        findViewById<EditText>(R.id.text_note).text = TextNoteStorage.getText(applicationContext)

        updateTextNote()
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