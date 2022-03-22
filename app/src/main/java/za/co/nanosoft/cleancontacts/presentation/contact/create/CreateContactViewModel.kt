package za.co.nanosoft.cleancontacts.presentation.contact.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import java.lang.Exception

class CreateContactViewModel constructor(private val createContactUseCase: CreateContactUseCase) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")

    val errorMessage: String
        get() = _errorMessage.value

    suspend fun createContact(data: ContactRequestModel): Boolean {
        return try {
            createContactUseCase.execute(data)
        } catch (err: Exception) {
            _errorMessage.value = "Error Creating Contact"
            false
        }
    }
}