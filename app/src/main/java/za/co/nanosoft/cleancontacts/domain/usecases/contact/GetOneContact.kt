package za.co.nanosoft.cleancontacts.domain.usecases.contact

import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetContactUseCase
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class GetOneContact constructor(private val contactRepository: ContactRepository) :
    GetContactUseCase {
    override suspend fun execute(id: Int): ContactResponseModel {
        return contactRepository.getContact(id)
    }
}