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
    fun deleteContact_should_return_true() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact(1)
        assertEquals(vm.isBusy, false)
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun should_set_error_when_deleteContact_fails() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockDeleteUseCase.execute(any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact(1)
        assertEquals(vm.isBusy, false)
        assertEquals(vm.errorMessage, "Error Deleting Contact")
    }

    @Test
    fun updateContact_should_return_true() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)

        val result = vm.updateContact(1, ContactRequestModel(name = "Paul"))
        assertEquals(vm.isBusy, false)
        assertEquals(vm.errorMessage, "")
    }

    @Test
    fun should_set_error_when_updateContact_fails() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockUpdateUseCase.execute(any(), any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.updateContact(1, ContactRequestModel(name = "Paul"))
        assertEquals(vm.isBusy, false)
        assertEquals(vm.errorMessage, "Error Updating Contact")

    }
}