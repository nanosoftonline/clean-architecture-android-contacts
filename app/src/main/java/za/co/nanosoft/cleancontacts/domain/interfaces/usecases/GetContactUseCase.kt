package za.co.nanosoft.cleancontacts.domain.interfaces.usecases

import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

interface GetContactUseCase {
    suspend fun execute(id: Int): ContactResponseModel
}