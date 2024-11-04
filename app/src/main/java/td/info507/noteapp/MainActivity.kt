package td.info507.noteapp

import AddDialogFragment
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.myppoker.storage.utility.Updatable
import td.info507.noteapp.model.Folder

class MainActivity : AppCompatActivity(), Updatable {
    private lateinit var folderStorage: FolderJSONFileStorage // Déclaration
    private lateinit var folderRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialiser folderStorage
        folderStorage = FolderJSONFileStorage(this)

        // Exemple de données avec des sous-dossiers pour chaque dossier
        val folderList = listOf(
            Folder(0, "Dossiers", listOf(Folder(0, "Sous-dossier 1"), Folder(1, "Sous-dossier 2"))),
            Folder(1, "Dossiers 2", listOf(Folder(0, "Sous-dossier 1"))),
            Folder(2, "Dossiers 3", listOf(Folder(0, "Sous-dossier 1")))
        )

        // Initialiser le RecyclerView principal
        folderRecyclerView = findViewById(R.id.recycler_view_main)
        folderRecyclerView.layoutManager = LinearLayoutManager(this)

        // Création de l'adaptateur avec gestion du clic sur les sous-dossiers
        val mainFolderAdapter = MainFolderAdapter(folderList, applicationContext)

        // Assigner l'adaptateur au RecyclerView
        folderRecyclerView.adapter = mainFolderAdapter

        // FloatingActionButton pour ajouter un dossier
        val button = findViewById<FloatingActionButton>(R.id.add_button)
        button.setOnClickListener {
            // Ouvrir le CardDialogFragment
            val addDialogFragment = AddDialogFragment(this)
            addDialogFragment.show(supportFragmentManager, "addDialog")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun update(){
        folderRecyclerView.adapter?.notifyDataSetChanged()
    }
}
