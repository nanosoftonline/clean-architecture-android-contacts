package za.co.nanosoft.cleancontacts.domain.usecases.contact

import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel

class CreateContact constructor(private val contactRepository: ContactRepository) :
    CreateContactUseCase {
    override suspend fun execute(contact: ContactRequestModel): Boolean {
        return contactRepository.createContact(contact)
    }
}