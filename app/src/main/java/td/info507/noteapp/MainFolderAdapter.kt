package td.info507.noteapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder

class MainFolderAdapter(private val folderList: List<Folder>) : RecyclerView.Adapter<MainFolderAdapter.MainFolderViewHolder>() {

    class MainFolderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subFolderRecyclerView: RecyclerView = itemView.findViewById(R.id.recycler_view_folder) // Changement ici
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFolderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folder_view, parent, false) // Assurez-vous que c'est le bon layout
        return MainFolderViewHolder(view)
    }

    override fun onBindViewHolder(holder: MainFolderViewHolder, position: Int) {
        val folder = folderList[position]

        // Configurer le RecyclerView des sous-dossiers
        val subFolderAdapter = FolderAdapter(folder.subFolders) // Assurez-vous que subFolders est bien défini dans votre modèle Folder
        holder.subFolderRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
        holder.subFolderRecyclerView.adapter = subFolderAdapter
    }

    override fun getItemCount(): Int {
        return folderList.size
    }
}

