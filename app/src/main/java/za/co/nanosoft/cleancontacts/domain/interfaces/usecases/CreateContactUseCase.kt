package za.co.nanosoft.cleancontacts.domain.interfaces.usecases

import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel

interface CreateContactUseCase {
    suspend fun execute(contact: ContactRequestModel): Boolean
}