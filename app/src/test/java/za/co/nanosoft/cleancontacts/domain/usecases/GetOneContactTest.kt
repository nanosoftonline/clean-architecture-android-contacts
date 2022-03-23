package za.co.nanosoft.cleancontacts.domain.usecases

import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel
import za.co.nanosoft.cleancontacts.domain.usecases.contact.CreateContact
import za.co.nanosoft.cleancontacts.domain.usecases.contact.GetOneContact

class GetOneContactTest {
    @Test
    fun should_return_data() = runBlocking {
        val mockContactRepo = mock<ContactRepository>()
        val expectedResponse = ContactResponseModel(id = 1, name = "Paul")
        whenever(mockContactRepo.getContact(any())).thenReturn(expectedResponse)
        val useCase = GetOneContact(mockContactRepo)
        val result = useCase.execute(1)
        verify(mockContactRepo, times(1)).getContact(any())
        Assert.assertEquals(result, expectedResponse)
    }
}