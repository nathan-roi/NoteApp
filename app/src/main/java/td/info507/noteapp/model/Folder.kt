package td.info507.noteapp.model

class Folder(
    val id: Int,
    val title: String,
    val subFolders: List<Folder> = emptyList(),
    val notes: List<Note> = emptyList(),
    val groupPhotos: List<GroupPhoto> = emptyList()
) {
    companion object {
        const val ID = "id"
        const val TITLE = "title"
        const val SUB_FOLDERS = "subFolders"
        const val NOTES = "notes"
        const val GROUP_PHOTOS = "groupPhotos"
    }
}