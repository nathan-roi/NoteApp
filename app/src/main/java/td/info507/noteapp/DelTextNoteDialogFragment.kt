package td.info507.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import td.info507.noteapp.adapter.TextNoteAdapter
import td.info507.noteapp.storage.TextNoteStorage
import td.info507.noteapp.storage.Updatable

class DelTextNoteDialogFragment(private val noteId: Int, private val updatable: Updatable) : DialogFragment() {


//    companion object {
//        private const val ARG_NOTE_ID = "note_id"
//
//        // Méthode pour créer une nouvelle instance de DeleteNoteDialogFragment avec l'ID de la note
//        fun newInstance(noteId: Int): DelTextNoteDialogFragment {
//            val fragment = DelTextNoteDialogFragment(this)
//            val args = Bundle()
//            args.putInt(ARG_NOTE_ID, noteId)
//            fragment.arguments = args
//            return fragment
//        }
//    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(context)
            .setTitle("Supprimer la note")
            .setMessage("Êtes-vous sûr de vouloir supprimer cette note ?")
            .setPositiveButton("Supprimer") { _, _ ->
                TextNoteStorage.get(requireContext()).delete(noteId)
                updatable.update()
            }
            .setNegativeButton("Annuler", null)
            .create()
    }
}