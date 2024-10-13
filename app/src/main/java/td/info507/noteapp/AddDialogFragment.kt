package td.info507.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import td.info507.myppoker.storage.utility.Updatable
import td.info507.noteapp.model.Folder
import td.info507.noteapp.utility.FolderStorage

class AddDialogFragment(private val updatable: Updatable) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add, null)

        return AlertDialog.Builder(requireActivity())
            .setView(view)
            .setPositiveButton("Valider") { _, _ ->
                val folderName = "Dossiers"
                if (folderName.isNotBlank()) {
                    val newFolder = Folder(
                        0,
                        "Dossiers",
                        listOf(Folder(0, "Sous-dossier 1"))
                        )
                    FolderStorage.get(requireContext()).insert(newFolder)
                    updatable.update()
                }
            }
            .setNegativeButton("Annuler", null)
            .create()
    }
}
