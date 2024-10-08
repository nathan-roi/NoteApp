package td.info507.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder

class FolderAdapter(private val folderList: List<Folder>) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    // ViewHolder class to hold the references to the views in item_folder.xml
    class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val folderIcon: ImageView = itemView.findViewById(R.id.folder_icon)
        val folderTitle: TextView = itemView.findViewById(R.id.folder_title)
        val arrowIcon: ImageView = itemView.findViewById(R.id.arrow_icon)
    }

    // Inflates the item_folder.xml layout for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
        return FolderViewHolder(view)
    }

    // Binds the data to the views in the ViewHolder
    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        holder.folderTitle.text = "Ceci est un fichier"
    }

    // Returns the size of the list
    override fun getItemCount(): Int {
        return 4
    }
}
