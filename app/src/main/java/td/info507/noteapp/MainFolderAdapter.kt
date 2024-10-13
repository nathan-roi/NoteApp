package td.info507.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder

class MainFolderAdapter(
    private val folderList: List<Folder>,
    private val onSubFolderClick: (Folder) -> Unit
) : RecyclerView.Adapter<MainFolderAdapter.MainFolderViewHolder>() {

    // ViewHolder avec le TextView pour le titre du dossier principal et le RecyclerView pour les sous-dossiers
    class MainFolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val folderTitleTextView: TextView = itemView.findViewById(R.id.folder_title)
        val subFolderRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view_folder)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_view, parent, false)
        return MainFolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainFolderViewHolder, position: Int) {
        val folder = folderList[position]

        // Définir le titre du dossier principal
        holder.folderTitleTextView.text = folder.title

        // Configurer le RecyclerView des sous-dossiers avec gestion des clics
        val subFolderAdapter = FolderAdapter(folder.subFolders) { subFolder ->
            // Appel du callback lorsqu'un sous-dossier est cliqué
            onSubFolderClick(subFolder)
        }
        holder.subFolderRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.subFolderRecyclerView.adapter = subFolderAdapter
    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}

