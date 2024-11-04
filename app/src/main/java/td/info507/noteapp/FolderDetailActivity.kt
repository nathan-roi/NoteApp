package td.info507.noteapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class FolderDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_folder_detail)

        // Récupération des extras envoyés depuis l'Intent
        val folderId = intent.getIntExtra("folderId", -1)
        val folderTitle = intent.getStringExtra("folderTitle")

        // Mise à jour des vues avec les données du dossier
        val folderTitleTextView = findViewById<TextView>(R.id.folder_title)
        folderTitleTextView.text = folderTitle
    }
}
