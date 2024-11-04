package td.info507.noteapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder
import td.info507.noteapp.utility.FolderStorage

abstract class FolderAdapter(private val folderList: List<Folder>, private val context: Context) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    abstract fun onItemClick(view: View)

    // ViewHolder class to hold the references to the views in item_folder.xml
    class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val folderIcon: ImageView = itemView.findViewById(R.id.folder_icon)
        val folderTitle: TextView = itemView.findViewById(R.id.folder_title)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrow_icon)
    }

    // Inflates the item_folder.xml layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
        view.setOnClickListener{ view ->
            onItemClick(view)
        }
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folder = FolderStorage.get(context).findAll().get(position)
        holder.itemView.tag = folder.id
        holder.folderTitle.text = folder.title
    }
    override fun getItemCount(): Int {
        return FolderStorage.get(context).size()
    }
}
