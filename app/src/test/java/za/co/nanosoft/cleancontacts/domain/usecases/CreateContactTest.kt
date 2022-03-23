package za.co.nanosoft.cleancontacts.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.usecases.contact.CreateContact

class CreateContactTest {
    @Test
    fun should_return_true() = runBlocking {
        val mockContactRepo = mock<ContactRepository>()
        whenever(mockContactRepo.createContact(any())).thenReturn(true)
        val useCase = CreateContact(mockContactRepo)
        val result = useCase.execute(ContactRequestModel(name = "Paul"))
        verify(mockContactRepo, times(1)).createContact(any())
        assertEquals(result, true)
    }
}