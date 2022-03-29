package za.co.nanosoft.cleancontacts.domain.usecases.contact

import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetContactUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.UpdateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class UpdateContact constructor(private val contactRepository: ContactRepository) :
    UpdateContactUseCase {
    override suspend fun execute(id: Int, data: ContactRequestModel) {
        return contactRepository.updateContact(id, data)
    }
}