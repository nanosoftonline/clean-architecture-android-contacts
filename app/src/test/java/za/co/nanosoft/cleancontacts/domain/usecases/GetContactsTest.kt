package za.co.nanosoft.cleancontacts.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel
import za.co.nanosoft.cleancontacts.domain.usecases.contact.CreateContact
import za.co.nanosoft.cleancontacts.domain.usecases.contact.GetContacts

class GetContactsTest {
    @Test
    fun should_return_data() = runBlocking {
        val mockContactRepo = mock<ContactRepository>()
        val expected = listOf(
            ContactResponseModel(
                id = 1,
                name = "Paul"
            )
        )
        whenever(mockContactRepo.getContacts()).thenReturn(expected)
        val useCase = GetContacts(mockContactRepo)
        val result = useCase.execute()
        verify(mockContactRepo, times(1)).getContacts()
        Assert.assertEquals(result, expected)
    }
}