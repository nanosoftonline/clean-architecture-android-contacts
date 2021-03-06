package za.co.nanosoft.cleancontacts.data.datasources.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel
import java.sql.Date

@Entity(tableName = "tb_contact")
data class ContactRoomEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
)


fun ContactRoomEntity.toContactResponseModel(): ContactResponseModel {
    return ContactResponseModel(
        id = id!!,
        name = name,
    )
}


fun ContactRequestModel.toContactRoomEntity(): ContactRoomEntity {
    return ContactRoomEntity(
        id = id,
        name = name
    )
}