package za.co.nanosoft.cleancontacts

import android.app.Application
import android.content.Context
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import za.co.nanosoft.cleancontacts.data.datasources.room.RoomContactDataSource
import za.co.nanosoft.cleancontacts.data.interfaces.ContactDataSource
import za.co.nanosoft.cleancontacts.data.interfaces.ContactDatabase
import za.co.nanosoft.cleancontacts.domain.interfaces.ContactRepository
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.CreateContactUseCase
import za.co.nanosoft.cleancontacts.domain.interfaces.usecases.GetAllContactsUseCase
import za.co.nanosoft.cleancontacts.domain.repositories.ContactRepositoryImpl
import za.co.nanosoft.cleancontacts.domain.usecases.contact.CreateContact
import za.co.nanosoft.cleancontacts.domain.usecases.contact.GetContacts
import za.co.nanosoft.cleancontacts.presentation.contact.list.ListContactsViewModel
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providesContactDatasource(@ApplicationContext context: Context): ContactDataSource {
        return RoomContactDataSource(
            dao = Room.databaseBuilder(
                context,
                ContactDatabase::class.java,
                ContactDatabase.DATABASE_NAME
            ).build().contactDao
        )
    }

    @Provides
    @Singleton
    fun providesContactRepository(dataSource: ContactDataSource): ContactRepository {
        return ContactRepositoryImpl(contactDataSource = dataSource)
    }

    @Provides
    @Singleton
    fun providesGetContactsUseCase(repository: ContactRepository): GetAllContactsUseCase {
        return GetContacts(contactRepository = repository)
    }

    @Provides
    @Singleton
    fun providesCreateContactUseCase(repository: ContactRepository): CreateContactUseCase {
        return CreateContact(contactRepository = repository)
    }

}