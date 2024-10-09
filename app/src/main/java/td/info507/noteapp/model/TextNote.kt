package td.info507.noteapp.model

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import td.info507.noteapp.R

public final class TextNote(val id: Int, val title:String, val text:String) {
    companion object{
        const val ID = "id"
        const val TITLE = "title"
        const val TEXT = "text"
    }
}