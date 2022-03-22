package za.co.nanosoft.cleancontacts.presentation.contact.create

import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel


class CreateContactViewModelTest {
    @Test
    fun test_CreateContactViewModel_Should_Return_True() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        whenever(mockUseCase.execute(any())).thenReturn(true)
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel(id = null, name = "Paul"))
        assertEquals(result, true)
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun test_CreateContactViewModel_Should_Set_Error_When_UseCase_Throws() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        whenever(mockUseCase.execute(any())).thenThrow()
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel(id = null, name = "Paul"))
        assertEquals(result, false)
        assertEquals(vm.errorMessage, "Error Creating Contact")

    }
}






