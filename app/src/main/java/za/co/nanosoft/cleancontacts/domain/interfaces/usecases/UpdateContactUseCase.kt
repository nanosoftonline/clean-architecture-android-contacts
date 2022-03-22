package za.co.nanosoft.cleancontacts.domain.interfaces.usecases

import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel

interface UpdateContactUseCase {
    suspend fun execute(id: String, data: ContactRequestModel): Boolean
}