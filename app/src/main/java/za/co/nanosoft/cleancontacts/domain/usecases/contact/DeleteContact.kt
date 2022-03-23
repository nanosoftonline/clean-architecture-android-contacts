package za.co.nanosoft.cleancontacts.domain.usecases.contact

import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.DeleteContactUseCase

class DeleteContact constructor(private val contactRepository: ContactRepository) :
    DeleteContactUseCase {
    override suspend fun execute(id: Int): Boolean {
        return contactRepository.deleteContact(id)
    }
}