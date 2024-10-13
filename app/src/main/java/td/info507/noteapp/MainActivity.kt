package td.info507.noteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.noteapp.activity.TextNoteActivity
import td.info507.noteapp.adapter.TextNoteAdapter
import td.info507.noteapp.request.NoteListRequest
import td.info507.noteapp.storage.TextNoteStorage

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.text_note_list)
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true  // Inverse l'ordre des éléments
            stackFromEnd = true  // Empêche l'empilement des éléments en bas
        }
        list.layoutManager = layoutManager

        list.adapter = object : TextNoteAdapter(applicationContext) {
            override fun onItemClick(view: View) {
                    val intent = Intent(applicationContext, TextNoteActivity::class.java).apply {
                    putExtra(EXTRA_NOTE, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongItemClick(view: View): Boolean {
                val noteId = view.tag as Int
                val dialogFragment = DelTextNoteDialogFragment.newInstance(noteId)
                dialogFragment.show(supportFragmentManager, "deleteDialog")

                return true
            }
        }

         val createButton = findViewById<FloatingActionButton>(R.id.create_button)

        createButton.setOnClickListener { view ->
            val intent = Intent(this, TextNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, -1)
            }
            startActivity(intent)
        }

        NoteListRequest(applicationContext)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}