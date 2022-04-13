package za.co.nanosoft.cleancontacts.presentation.contact.create

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class CreateContactViewModel @Inject constructor(
    private val createContactUseCase: CreateContactUseCase
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _name = mutableStateOf("")

    val name: String
        get() = _name.value


    val errorMessage: String
        get() = _errorMessage.value

    fun onNameChange(newName: String) {
        _name.value = newName
    }

    suspend fun createContact() {
        try {
            createContactUseCase.execute(ContactRequestModel(null, _name.value))
        } catch (err: Exception) {
            _errorMessage.value = "Error Creating Contact"
        }
    }
}