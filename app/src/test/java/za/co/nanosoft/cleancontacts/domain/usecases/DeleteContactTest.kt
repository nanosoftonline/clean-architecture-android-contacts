package za.co.nanosoft.cleancontacts.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.usecases.contact.CreateContact
import za.co.nanosoft.cleancontacts.domain.usecases.contact.DeleteContact

class DeleteContactTest {
    @Test
    fun should_return_true() = runBlocking {
        val mockContactRepo = mock<ContactRepository>()
        val useCase = DeleteContact(mockContactRepo)
        val result = useCase.execute(1)
        verify(mockContactRepo, times(1)).deleteContact(any())
    }
}