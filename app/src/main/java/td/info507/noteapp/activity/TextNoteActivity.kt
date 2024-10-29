package td.info507.noteapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import td.info507.noteapp.MainActivity
import td.info507.noteapp.R
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.TextNoteStorage
import kotlin.properties.Delegates

class TextNoteActivity: AppCompatActivity() {
    private lateinit var textOfNote: String
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.text_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val extraNote = intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0)

        if (extraNote >= 0){
            val textNote = TextNoteStorage.get(applicationContext).find(intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0))

            val title = findViewById<TextView>(R.id.title_note)
            val text = findViewById<TextView>(R.id.text_note)

            title.text = textNote?.title
            text.text = textNote?.text

            isFavorite = textNote!!.favorite
        }

        textOfNote = findViewById<EditText>(R.id.text_note).text.toString() // Pour le partage du texte

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
                val fav = textNote.favorite

                TextNoteStorage.get(applicationContext).update(
                    idCourant,
                    TextNote(
                        idCourant,
                        findViewById<EditText>(R.id.title_note).text.toString(),
                        findViewById<EditText>(R.id.text_note).text.toString(),
                        0,
                        fav
                    )
                )
                Toast.makeText(applicationContext, "Saved !", Toast.LENGTH_SHORT).show()

            } else { // Si la note n'existe pas on en crée une nouvelle
                TextNoteStorage.get(applicationContext).insert(
                    TextNote(
                        0,
                        findViewById<EditText>(R.id.title_note).text.toString(),
                        findViewById<EditText>(R.id.text_note).text.toString(),
                        0,
                        false
                    )
                )
                Toast.makeText(applicationContext, "Enregistré !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_text_note, menu)
        val iconFav = menu?.findItem(R.id.action_fav)
        if (isFavorite){
            iconFav?.setIcon(R.drawable.ic_fav_full)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, textOfNote)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
                true
            }
            R.id.action_fav -> {
                val textNote = TextNoteStorage.get(applicationContext)
                    .find(intent.getIntExtra(ListTextNotes.EXTRA_NOTE, 0))!!
                val textNoteId = textNote.id

                TextNoteStorage.get(applicationContext).update(
                    textNoteId,
                    TextNote(textNoteId, textNote.title, textNote.text, textNote.parent_folder, true)
                    )
                Toast.makeText(applicationContext, "Note mise en favoris", Toast.LENGTH_SHORT).show()
                item.setIcon(R.drawable.ic_fav_full)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}