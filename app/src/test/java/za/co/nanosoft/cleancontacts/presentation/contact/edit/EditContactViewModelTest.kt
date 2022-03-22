package za.co.nanosoft.cleancontacts.presentation.contact.edit

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.DeleteContactUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.UpdateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel

class EditContactViewModelTest {

    @Test
    fun test_DeleteContact_Should_Return_True() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockDeleteUseCase.execute(any())).thenReturn(true)
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact("123")
        assertEquals(result, true)
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun test_DeleteContact_Should_Set_Error_Message_When_UseCase_Throws() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockDeleteUseCase.execute(any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact("12345")
        assertEquals(result, false)
        assertEquals(vm.errorMessage, "Error Deleting Contact")
    }

    @Test
    fun test_UpdateContact_Should_Return_True() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockUpdateUseCase.execute(any(), any())).thenReturn(true)
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.updateContact("123", ContactRequestModel(name = "Paul"))
        assertEquals(result, true)
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun test_UpdateContact_Should_Set_Error_Message_When_UseCase_Throws() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockUpdateUseCase.execute(any(), any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.updateContact("12345", ContactRequestModel(name = "Paul"))
        assertEquals(result, false)
        assertEquals(vm.errorMessage, "Error Updating Contact")

    }
}