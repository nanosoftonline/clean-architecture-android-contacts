package za.co.nanosoft.cleancontacts.domain.interfaces.usecases
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

interface GetAllContactsUseCase {
    suspend fun execute(): List<ContactResponseModel>
}