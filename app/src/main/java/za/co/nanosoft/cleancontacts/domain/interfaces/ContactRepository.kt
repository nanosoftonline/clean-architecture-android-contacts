package za.co.nanosoft.cleancontacts.domain.interfaces

import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

interface ContactRepository {
    suspend fun getContacts(): List<ContactResponseModel>
    suspend fun getContact(id: Int): ContactResponseModel
    suspend fun deleteContact(id: Int): Boolean
    suspend fun updateContact(id: Int, data: ContactRequestModel): Boolean
    suspend fun createContact(data: ContactRequestModel): Boolean
}