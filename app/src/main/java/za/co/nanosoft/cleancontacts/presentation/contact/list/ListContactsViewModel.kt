package za.co.nanosoft.cleancontacts.presentation.contact.list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ListContactsViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _contacts = mutableStateListOf<ContactResponseModel>()

    val errorMessage: String
        get() = _errorMessage.value


    val contacts: List<ContactResponseModel>
        get() = _contacts.toList()

    suspend fun getContacts() {
        try {
            _contacts.clear()
            val list = getAllContactsUseCase.execute()
            _contacts.addAll(list)
        } catch (err: Exception) {
            _errorMessage.value = "Error Fetching Contacts"
        }
    }
}