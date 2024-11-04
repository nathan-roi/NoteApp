package td.info507.noteapp

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder
import td.info507.noteapp.utility.FolderStorage

class MainFolderAdapter(
    private var folderList: List<Folder>,
//    private val onSubFolderClick: (Folder) -> Unit
    private val context : Context
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
        if(FolderStorage.get(context).size() > 0){

            val folder = FolderStorage.get(context).findAll().get(position)

            // Définir le titre du dossier principal
            holder.folderTitleTextView.text = folder.title

            val subFolderAdapter = object : FolderAdapter(folder.subFolders, context) {
                override fun onItemClick(view: View) {
                    val folderId = view.tag as Int
                    Log.d("CLICK", "a cliquer $folderId")

                    val intent = Intent(context, FolderDetailActivity::class.java).apply{
                        putExtra("folderId", folderId)
                        putExtra("folderTitle", folder.title)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    context.startActivity(intent)
                }
            }

            holder.subFolderRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context)
            holder.subFolderRecyclerView.adapter = subFolderAdapter
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    fun updateFolders(newFolders: List<Folder>) {
        folderList = newFolders
        notifyDataSetChanged()
    }
}

