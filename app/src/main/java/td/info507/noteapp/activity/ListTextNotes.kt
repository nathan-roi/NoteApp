package td.info507.noteapp.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.noteapp.DelTextNoteDialogFragment
import td.info507.noteapp.MainActivity
import td.info507.noteapp.MainActivity.Companion.EXTRA_FOLDER
import td.info507.noteapp.R
import td.info507.noteapp.adapter.TextNoteAdapter
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.request.NoteListRequest
import td.info507.noteapp.storage.TextNoteStorage
import td.info507.noteapp.storage.Updatable

class ListTextNotes: AppCompatActivity(), Updatable {
    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_text_note)

        val courantFolder = intent.getIntExtra(EXTRA_FOLDER, 0) //Origine EXTRA_FOLDER dans les imports


        list = findViewById(R.id.text_note_list)
        val layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true  // Inverse l'ordre des éléments
            stackFromEnd = true  // Empêche l'empilement des éléments en bas
        }
        list.layoutManager = layoutManager

        list.adapter = object : TextNoteAdapter(applicationContext, courantFolder, listOfNotes()) {
            override fun onItemClick(view: View) {
                val intent = Intent(applicationContext, TextNoteActivity::class.java).apply {
                    putExtra(EXTRA_NOTE, view.tag as Int)
                }
                startActivity(intent)
            }

            override fun onLongItemClick(view: View): Boolean {
                val textNoteId = view.tag as Int
                val dialogFragment = DelTextNoteDialogFragment(textNoteId, this@ListTextNotes)
                dialogFragment.show(supportFragmentManager, "deleteDialog")
                return true
            }
        }

        val createButton = findViewById<FloatingActionButton>(R.id.create_button)

        createButton.setOnClickListener { view ->
            val intent = Intent(this, TextNoteActivity::class.java).apply {
                putExtra(EXTRA_NOTE, -1)
                putExtra(EXTRA_FOLDER, courantFolder)
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

    override fun textNoteRemoved(){
        list.adapter?.notifyDataSetChanged()
    }

    private fun listOfNotes(): List<TextNote> {
        val folderOfNote = intent.getIntExtra(MainActivity.EXTRA_FOLDER, 0)
        val notes = TextNoteStorage.get(applicationContext).findAll()
        var newNotes = notes

        if (folderOfNote == 1){
            newNotes = notes.filter{it.favorite}
        }
        return newNotes
    }
}