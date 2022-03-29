package za.co.nanosoft.cleancontacts.domain.repositories

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.data.interfaces.ContactDataSource
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class ContactRepositoryImplTest {
    @Test
    fun getContacts_should_return_data() = runBlocking {
        val mockContactDataSource = mock<ContactDataSource>()
        val expectedData = listOf(
            ContactResponseModel(
                id = 1,
                name = "Paul"
            ),
            ContactResponseModel(
                id = 2,
                name = "John"
            )
        );
        whenever(mockContactDataSource.getAll()).thenReturn(expectedData)
        val repo = ContactRepositoryImpl(mockContactDataSource)

        val result = repo.getContacts()
        verify(mockContactDataSource, times(1)).getAll()
        assertEquals(result, expectedData)
    }

    @Test
    fun getContact_should_return_data() = runBlocking {
        val mockContactDataSource = mock<ContactDataSource>()
        val expectedData = ContactResponseModel(
            id = 1,
            name = "Paul"
        );
        whenever(mockContactDataSource.getOne(any())).thenReturn(expectedData)
        val repo = ContactRepositoryImpl(mockContactDataSource)

        val result = repo.getContact(1)
        verify(mockContactDataSource, times(1)).getOne(any())
        assertEquals(result, expectedData)
    }

    @Test
    fun deleteContact_should_return_true() = runBlocking {
        val mockContactDataSource = mock<ContactDataSource>()
        val repo = ContactRepositoryImpl(mockContactDataSource)
        val result = repo.deleteContact(1)
        verify(mockContactDataSource, times(1)).delete(any())
    }

    @Test
    fun updateContact_should_return_true() = runBlocking {
        val mockContactDataSource = mock<ContactDataSource>()
        val repo = ContactRepositoryImpl(mockContactDataSource)
        val result = repo.updateContact(1, ContactRequestModel(name = "Paul"))
        verify(mockContactDataSource, times(1)).update(any(), any())
    }

    @Test
    fun createContact_should_return_true() = runBlocking {
        val mockContactDataSource = mock<ContactDataSource>()
        val repo = ContactRepositoryImpl(mockContactDataSource)
        val result = repo.createContact(ContactRequestModel(name = "Paul"))
        verify(mockContactDataSource, times(1)).create(any())
    }
}