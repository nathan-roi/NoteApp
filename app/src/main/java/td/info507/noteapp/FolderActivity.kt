package td.info507.noteapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import td.info507.noteapp.model.Folder

class FolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.infolder_view)

        // Initialisation du RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_infolder)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Liste d'exemples pour peupler le RecyclerView
        val folderList = listOf(
            Folder(0, "Infos doc test"),
            Folder(1, "Another folder")
        )

        // Configuration de l'adaptateur avec un écouteur de clic
        val adapter = object : FolderAdapter(folderList, applicationContext){
            override fun onItemClick(view: View) {

            }
        }
        recyclerView.adapter = adapter

        // Ajouter une ligne de séparation
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.divider_line, null))
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
