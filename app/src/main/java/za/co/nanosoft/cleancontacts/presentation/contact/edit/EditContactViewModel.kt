package za.co.nanosoft.cleancontacts.presentation.contact.edit

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.DeleteContactUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.UpdateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import java.lang.Exception

class EditContactViewModel constructor(
    private val deleteContactUseCase: DeleteContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")

    val errorMessage: String
        get() = _errorMessage.value

    suspend fun deleteContact(id: String): Boolean {
        return try {
            deleteContactUseCase.execute(id)
        } catch (err: Exception) {
            _errorMessage.value = "Error Deleting Contact"
            false
        }
    }

    suspend fun updateContact(id: String, data: ContactRequestModel): Boolean {
        return try {
            updateContactUseCase.execute(id, data)
        } catch (err: Exception) {
            _errorMessage.value = "Error Updating Contact"
            false
        }
    }
}