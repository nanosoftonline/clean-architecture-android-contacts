package za.co.nanosoft.cleancontacts.domain.interfaces

import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

interface ContactRepository {
    suspend fun getContacts(): List<ContactResponseModel>
    suspend fun getContact(id: String): ContactResponseModel
    suspend fun deleteContact(id: String): Boolean
    suspend fun updateContact(id: String, data: ContactRequestModel): Boolean
    suspend fun createContact(data: ContactRequestModel): Boolean
}