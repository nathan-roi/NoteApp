import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import td.info507.noteapp.R
import td.info507.myppoker.storage.utility.Updatable
import td.info507.noteapp.model.Folder
import td.info507.noteapp.utility.FolderStorage

class AddDialogFragment(private val updatable: Updatable) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infle le layout du dialogue
        return inflater.inflate(R.layout.dialog_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Récupère les boutons à partir de la vue
        val addNoteButton: Button = view.findViewById(R.id.add_note)
        val addFolderButton: Button = view.findViewById(R.id.add_folder)

        // Ajoute une action au bouton "Créer une note"
        addNoteButton.setOnClickListener {
            // code de nathan le bg
            dismiss()
        }

        // Ajoute une action au bouton "Créer un dossier"
        addFolderButton.setOnClickListener {
            val folderName = "Dossiers"
            if (folderName.isNotBlank()) {
                // Crée un nouveau dossier avec un sous-dossier
                val newFolder = Folder(
                    0,
                    folderName,
                    listOf(Folder(0, "Sous-dossier 1"))
                )
                FolderStorage.get(requireContext()).insert(newFolder)
                updatable.update()
            }
            dismiss()
        }
    }
}
