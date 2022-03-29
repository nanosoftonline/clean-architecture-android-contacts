package za.co.nanosoft.cleancontacts.data.datasources.room

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.data.datasources.room.entities.ContactRoomEntity
import za.co.nanosoft.cleancontacts.data.interfaces.ContactDao
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class RoomContactDataSourceTest {
    @Test
    fun getAll_should_return_data() = runBlocking {
        val mockDao = mock<ContactDao>()
        whenever(mockDao.getAll()).thenReturn(listOf(ContactRoomEntity(name = "Paul", id = 1)))
        val ds = RoomContactDataSource(mockDao)
        val result = ds.getAll()
        assertEquals(result, listOf(ContactResponseModel(id = 1, name = "Paul")))
    }

    @Test
    fun getOne_should_return_data() = runBlocking {
        val mockDao = mock<ContactDao>()
        whenever(mockDao.getById(any())).thenReturn((ContactRoomEntity(name = "Paul", id = 1)))
        val ds = RoomContactDataSource(mockDao)
        val result = ds.getOne(1)
        verify(mockDao, times(1)).getById(1)
        assertEquals(result, ContactResponseModel(id = 1, name = "Paul"))
    }

    @Test
    fun delete_should_call_dao_deleteById() = runBlocking {
        val mockDao = mock<ContactDao>()
        val ds = RoomContactDataSource(mockDao)
        ds.delete(1)
        verify(mockDao, times(1)).deleteById(1)
    }

    @Test
    fun update_should_call_dao_update() = runBlocking {
        val mockDao = mock<ContactDao>()
        val ds = RoomContactDataSource(mockDao)
        ds.update(1, ContactRequestModel(name = "Paul"))
        verify(mockDao, times(1)).update(1, "Paul")
    }

    @Test
    fun create_should_call_dao_insert() = runBlocking {
        val mockDao = mock<ContactDao>()
        val ds = RoomContactDataSource(mockDao)
        ds.create(ContactRequestModel(name = "Paul"))
        verify(mockDao, times(1)).insert(ContactRoomEntity( name = "Paul"))
    }

}