package td.info507.noteapp

import android.content.Intent
import android.icu.text.Transliterator.Position
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.noteapp.activity.ListTextNotes
import td.info507.noteapp.activity.ListTextNotes.Companion.EXTRA_NOTE
import td.info507.noteapp.activity.TextNoteActivity
import td.info507.noteapp.adapter.TextNoteAdapter
import td.info507.noteapp.request.NoteListRequest
import td.info507.noteapp.storage.TextNoteStorage
import td.info507.noteapp.storage.Updatable

class MainActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_FOLDER = "EXTRA_FOLDER"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val favButton = findViewById<ConstraintLayout>(R.id.fav_buton)
        favButton.setOnClickListener{
            val intent = Intent(applicationContext, ListTextNotes::class.java).apply {
                putExtra(EXTRA_FOLDER, 1)
            }
            startActivity(intent)
        }

        val allNotesButton = findViewById<ConstraintLayout>(R.id.all_buton) // Bouton affichage dossier 'toutes les notes'
        allNotesButton.setOnClickListener{
            val intent = Intent(applicationContext, ListTextNotes::class.java).apply {
                putExtra(EXTRA_FOLDER, 0)
            }
            startActivity(intent)
        }

        val cloudNotesButton = findViewById<ConstraintLayout>(R.id.cloud_button)
        cloudNotesButton.setOnClickListener{
            val intent = Intent(applicationContext, ListTextNotes::class.java).apply{
                putExtra(EXTRA_FOLDER, 2)
            }
            startActivity(intent)
        }

        val createButton = findViewById<FloatingActionButton>(R.id.create_button) // Bouton add a new note
        createButton.setOnClickListener {
            val intent = Intent(this, TextNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, -1)
            }
            startActivity(intent)
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}