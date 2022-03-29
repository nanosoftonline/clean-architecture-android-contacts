package za.co.nanosoft.cleancontacts.domain.repositories

import za.co.nanosoft.cleancontacts.data.interfaces.ContactDataSource
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.models.ContactRequestModel
import za.co.nanosoft.cleancontacts.domain.models.ContactResponseModel

class ContactRepositoryImpl constructor(private val contactDataSource: ContactDataSource) :
    ContactRepository {
    override suspend fun getContacts(): List<ContactResponseModel> {
        return contactDataSource.getAll()
    }

    override suspend fun getContact(id: Int): ContactResponseModel? {
        return contactDataSource.getOne(id)
    }

    override suspend fun deleteContact(id: Int) {
        return contactDataSource.delete(id)
    }

    override suspend fun updateContact(id: Int, data: ContactRequestModel) {
        return contactDataSource.update(id, data)
    }

    override suspend fun createContact(data: ContactRequestModel) {
        return contactDataSource.create(data)
    }
}