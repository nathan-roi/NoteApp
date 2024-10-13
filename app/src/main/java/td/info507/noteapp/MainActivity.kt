package td.info507.noteapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import td.info507.noteapp.model.Folder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Exemple de données avec des sous-dossiers pour chaque dossier
        val folderList = listOf(
            Folder(0, "Dossier 1", listOf(Folder(0, "Sous-dossier 1"), Folder(1, "Sous-dossier 2"))),
            Folder(1, "Dossier 2", listOf(Folder(2, "Sous-dossier 3"))),
            Folder(2, "Dossier 3", emptyList()),
            Folder(3, "Dossier 3", emptyList()),
            Folder(4, "Dossier 3", emptyList()),
            Folder(5, "Dossier 3", emptyList()),
            Folder(6, "Dossier 3", emptyList())
        )

        // Initialiser le RecyclerView principal
        val folderRecyclerView: RecyclerView = findViewById(R.id.recycler_view_main)
        folderRecyclerView.layoutManager = LinearLayoutManager(this)

        // Créer et définir l'adaptateur pour le RecyclerView principal
        val folderAdapter = MainFolderAdapter(folderList)
        folderRecyclerView.adapter = folderAdapter

        // FloatingActionButton pour ajouter un dossier
        val button = findViewById<FloatingActionButton>(R.id.add_button)
        button.setOnClickListener {
            // Ouvrir le CardDialogFragment
            CardDialogFragment().show(supportFragmentManager, null)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

  //  override fun onSupportNavigateUp(): Boolean {
 //       val navController = findNavController(R.id.nav_host_fragment_content_main)
 //       return navController.navigateUp(appBarConfiguration)
 //               || super.onSupportNavigateUp()
 //   }
}


// Démarre FolderActivity
// val intent = Intent(this, FolderActivity::class.java)
// startActivity(intent)