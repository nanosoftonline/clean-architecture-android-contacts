package za.co.nanosoft.cleancontacts.presentation.contact.list

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel
import java.lang.Exception
import javax.inject.Inject

data class ContactListResponseModel(
    val id: String,
    val name: String,
)

fun ContactResponseModel.toContactListResponseModel(): ContactListResponseModel {
    return ContactListResponseModel(
        id = id.toString(),
        name = name,
    )
}


@HiltViewModel
class ListContactsViewModel @Inject constructor(
    private val getAllContactsUseCase: GetAllContactsUseCase
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")
    private val _contacts = mutableStateListOf<ContactListResponseModel>()

    val errorMessage: String
        get() = _errorMessage.value


    val contacts: List<ContactListResponseModel>
        get() = _contacts

    suspend fun getContacts() {
        try {
            _contacts.clear()
            val list = getAllContactsUseCase.execute()
            _contacts.addAll(list.map { it.toContactListResponseModel() })
        } catch (err: Exception) {
            _errorMessage.value = "Error Fetching Contacts"
        }
    }
}