package td.info507.noteapp.storage

import td.info507.noteapp.model.TextNote
import td.info507.noteapp.storage.utility.Storage

class TextNoteNoneStorage: Storage<TextNote> {
    override fun insert(obj: TextNote): Int {
        return 0
    }

    override fun size(): Int {
        return 0
    }

    override fun find(id: Int): TextNote? {
        return null
    }

    override fun findAll(): List<TextNote> {
        return emptyList()
    }

    override fun delete(id: Int) {
    }

    override fun update(id: Int, obj: TextNote) {
    }
}