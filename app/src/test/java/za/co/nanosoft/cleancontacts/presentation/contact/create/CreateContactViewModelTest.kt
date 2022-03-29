package za.co.nanosoft.cleancontacts.presentation.contact.create

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.*
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel


class CreateContactViewModelTest {
    @Test
    fun createContact_should_return_true() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel(name = "Paul"))
        verify(mockUseCase, times(1)).execute(any())
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun should_set_error_when_createContact_fails() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        whenever(mockUseCase.execute(any())).thenThrow()
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel(name = "Paul"))
        assertEquals(vm.errorMessage, "Error Creating Contact")
    }
}






