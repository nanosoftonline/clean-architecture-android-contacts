package za.co.nanosoft.cleancontacts.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.usecases.contact.DeleteContact
import za.co.nanosoft.cleancontacts.domain.usecases.contact.UpdateContact

class UpdateContactTest {
    @Test
    fun should_return_true() = runBlocking {
        val mockContactRepo = mock<ContactRepository>()
        whenever(mockContactRepo.updateContact(any(), any())).thenReturn(true)
        val useCase = UpdateContact(mockContactRepo)
        val result = useCase.execute(1, ContactRequestModel(name = "Paul"))
        verify(mockContactRepo, times(1)).updateContact(any(), any())
        Assert.assertEquals(result, true)
    }
}