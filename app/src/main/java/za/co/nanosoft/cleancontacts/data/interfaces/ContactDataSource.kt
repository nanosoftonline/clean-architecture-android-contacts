package za.co.nanosoft.cleancontacts.data.interfaces

import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

interface ContactDataSource {
    suspend fun getAll(): List<ContactResponseModel>
    suspend fun getOne(id: Int): ContactResponseModel?
    suspend fun delete(id: Int)
    suspend fun update(id: Int, contact: ContactRequestModel)
    suspend fun create(data: ContactRequestModel)
}