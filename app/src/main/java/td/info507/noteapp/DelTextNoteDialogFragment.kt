package td.info507.noteapp

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import td.info507.noteapp.storage.TextNoteStorage

class DelTextNoteDialogFragment : DialogFragment() {
    companion object {
        private const val ARG_NOTE_ID = "note_id"

        // Méthode pour créer une nouvelle instance de DeleteNoteDialogFragment avec l'ID de la note
        fun newInstance(noteId: Int): DelTextNoteDialogFragment {
            val fragment = DelTextNoteDialogFragment()
            val args = Bundle()
            args.putInt(ARG_NOTE_ID, noteId)
            fragment.arguments = args
            return fragment
        }
    }

    interface OnNoteDeletedListener {
        fun onNoteDeleted(position: Int)
    } /*TODO : actualiser le recyclerview */

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val noteId = arguments?.getInt(ARG_NOTE_ID)

        return AlertDialog.Builder(context)
            .setTitle("Supprimer la note")
            .setMessage("Êtes-vous sûr de vouloir supprimer cette note ?")
            .setPositiveButton("Supprimer") { _, _ ->
                if (noteId != null) {
                    TextNoteStorage.get(requireContext()).delete(noteId)
                }
            }
            .setNegativeButton("Annuler", null)
            .create()

    }
}