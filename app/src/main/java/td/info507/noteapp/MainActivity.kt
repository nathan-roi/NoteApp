package td.info507.noteapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.noteapp.activity.TextNoteActivity
import td.info507.noteapp.adapter.TextNoteAdapter
import td.info507.noteapp.request.NoteListRequest

class MainActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NOTE = "EXTRA_NOTE"
    }

    private lateinit var list: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list: RecyclerView = findViewById(R.id.text_note_list)
        list.adapter = TextNoteAdapter(applicationContext)

         val createButton = findViewById<FloatingActionButton>(R.id.create_button)

        createButton.setOnClickListener { view ->
            val intent = Intent(this, TextNoteActivity::class.java)
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