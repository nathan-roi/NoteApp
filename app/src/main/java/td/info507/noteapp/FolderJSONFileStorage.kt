package td.info507.noteapp

import android.content.Context
import org.json.JSONObject
import td.info507.myppoker.storage.utility.file.JSONFileStorage
import td.info507.noteapp.model.Folder
import td.info507.noteapp.model.GroupPhoto
import td.info507.noteapp.model.Note

class FolderJSONFileStorage (context: Context): JSONFileStorage<Folder>(context, "Folder"){
    override fun create(id: Int, obj: Folder): Folder {
        return Folder(id, obj.title, obj.subFolders, obj.notes, obj.groupPhotos)
    }

    override fun objectToJson(id: Int, obj: Folder): JSONObject {
        val json = JSONObject()
        json.put(Folder.ID, id)
        json.put(Folder.TITLE, obj.title)
        json.put(Folder.SUB_FOLDERS, obj.subFolders)
        json.put(Folder.NOTES, obj.notes)
        json.put(Folder.GROUP_PHOTOS, obj.groupPhotos)
        return json
    }

    override fun jsonToObject(json: JSONObject): Folder {
        return Folder(
            json.getInt(Folder.ID),
            json.getString(Folder.TITLE)
        )
    }
}
