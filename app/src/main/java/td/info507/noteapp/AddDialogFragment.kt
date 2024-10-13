package td.info507.noteapp

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class CardDialogFragment() : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_add, null)

        return AlertDialog.Builder(activity)
            .setView(view)
            .setPositiveButton("Valider") { _, _ ->
            }
            .setNegativeButton("Annuler", null)
            .create()

    }


}