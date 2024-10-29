package td.info507.noteapp.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.R
import td.info507.noteapp.model.TextNote
import td.info507.noteapp.request.NoteListRequest
import td.info507.noteapp.storage.TextNoteStorage
import kotlin.properties.Delegates

abstract class TextNoteAdapter(private val context: Context, private val folder: Int): RecyclerView.Adapter<TextNoteAdapter.TextNoteHolder>() {

    fun notesFilter(): List<TextNote> {
        var notes = TextNoteStorage.get(context).findAll()
        if (folder == 1){
            notes = notes.filter { it.favorite }
        }else if (folder == 2){
            notes = notes.filter{ it.parent_folder == 2 }
        }

        return notes
    }

    class TextNoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title_note)
        val text: TextView = itemView.findViewById(R.id.text_note)

    }

    abstract fun onItemClick(view: View)
    abstract fun onLongItemClick(view: View): Boolean

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextNoteHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_text_note, parent, false)

        view.setOnClickListener{ view ->
            onItemClick(view)
        }
        view.setOnLongClickListener { view ->
            onLongItemClick(view)
        }
        return TextNoteHolder(view)
    }
    override fun onBindViewHolder(holder: TextNoteHolder, position: Int) {
        val notes = notesFilter()

        if (notes.isNotEmpty()){
            val textNote = notes.get(position)
            holder.itemView.tag = textNote.id
            holder.title.text = textNote.title
            holder.text.text = textNote.text
        }
    }

    override fun getItemCount(): Int {
        return notesFilter().size
    }
}