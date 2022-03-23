package za.co.nanosoft.cleancontacts.domain.usecases.contact

import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class GetContacts constructor(private val contactRepository: ContactRepository) :
    GetAllContactsUseCase {
    override suspend fun execute(): List<ContactResponseModel> {
        return contactRepository.getContacts()
    }
}