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
import td.info507.noteapp.storage.TextNoteStorage

abstract class TextNoteAdapter(private val context: Context): RecyclerView.Adapter<TextNoteAdapter.TextNoteHolder>() {
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
        val textNote = TextNoteStorage.get(context).findAll().get(position)
        holder.itemView.tag = textNote.id
        holder.title.text = textNote.title
        holder.text.text = textNote.text
    }
    override fun getItemCount(): Int {
        return TextNoteStorage.get(context).size()
    }
}