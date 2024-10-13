package td.info507.noteapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder

class FolderAdapter(
    private val folderList: List<Folder>,
    private val onItemClick: (Folder) -> Unit // Ajout d'un paramètre pour le callback de clic
) : RecyclerView.Adapter<FolderAdapter.FolderViewHolder>() {

    // ViewHolder pour gérer les éléments de la liste
    class FolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.folder_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_folder, parent, false)
        return FolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: FolderViewHolder, position: Int) {
        val folder = folderList[position]
        holder.titleTextView.text = folder.title

        // Vérification du titre du dossier
        Log.d("FolderAdapter", "Title: ${folder.title}")

        // Gestion du clic sur l'élément
        holder.itemView.setOnClickListener {
            onItemClick(folder) // Appelle la fonction callback avec le Folder cliqué
        }
    }


    override fun getItemCount(): Int = folderList.size
}
