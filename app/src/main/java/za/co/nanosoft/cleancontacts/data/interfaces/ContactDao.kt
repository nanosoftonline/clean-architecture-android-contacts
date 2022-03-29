package za.co.nanosoft.cleancontacts.data.interfaces

import androidx.room.*
import za.co.nanosoft.cleancontacts.data.datasources.room.entities.ContactRoomEntity


@Database(entities = [ContactRoomEntity::class], version = 1)
abstract class ContactDatabase : RoomDatabase() {
    abstract val contactDao: ContactDao

    companion object {
        const val DATABASE_NAME = "contacts_db"
    }
}

@Dao
interface ContactDao {
    @Query("SELECT * FROM tb_contact")
    suspend fun getAll(): List<ContactRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: ContactRoomEntity)

    @Query("UPDATE tb_contact SET name =:name WHERE id = :id")
    suspend fun update(id: Int, name: String)

    @Query("SELECT * FROM tb_contact WHERE id = :id")
    suspend fun getById(id: Int): ContactRoomEntity?

    @Query("DELETE FROM tb_contact WHERE id = :id")
    suspend fun deleteById(id: Int)

}