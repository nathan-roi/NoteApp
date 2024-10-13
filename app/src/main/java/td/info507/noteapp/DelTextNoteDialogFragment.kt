package td.info507.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.DialogFragment
import td.info507.noteapp.storage.TextNoteStorage
import td.info507.noteapp.storage.Updatable

class DelTextNoteDialogFragment(private val textNoteId: Int, private val updatable: Updatable) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(context)
            .setTitle("Supprimer la note")
            .setMessage("Êtes-vous sûr de vouloir supprimer cette note ?")
            .setPositiveButton("Supprimer") { _, _ ->
                TextNoteStorage.get(requireContext()).delete(textNoteId)
                updatable.textNoteRemoved()
            }
            .setNegativeButton("Annuler", null)
            .create()
    }
}