# clean-architecture-android-contacts

By employing clean architecture, you can design applications with very low coupling and independent of technical implementation details. That way, the application becomes easy to maintain and flexible to change. Clean architecture allows us to create architectural boundaries between dependencies which allows components to be intrinsically testable.


What's to follow is our attempt to build an Android application using clean architecture with TDD. The app will be used to manage and organize contacts.


#### Setup Project
Create a new android compose project. 

<div class="bg-red" style="text-align: center">
    <img src="/assets/img/blog/clean-architecture-android/create-new-project.png" width="700" />
</div>

#### Run the application
Run the application, and we get the following

<div class="bg-red" style="text-align: center; background-color:grey; padding:20px; margin:20px">
    <img src="/assets/img/blog/clean-architecture-android/first-run.png" width="300" />
</div>


### Data entities
The first thing to decide on is what are the shape of the data we plan on passing around. i.e. the request model and the response model

Let's create these models in the domain

```kt
package za.co.nanosoft.cleancontacts.domain

data class ContactResponseModel(
    val id: Int,
    val name: String
)


data class ContactRequestModel(
    val id: Int? = null,
    val name: String
)
```

#### Architectural Boundaries
The next thing we have to do is decide on where we'd like our architectural boundaries to appear.
Let's look at the following diagram give us more insight into where we can insert those boundaries. We use interfaces to specify them.

<div class="bg-red" style="text-align: center;  padding:20px; margin:20px">

[![](https://mermaid.ink/img/pako:eNp1ks-KgzAQh18l5Ny-QA4L3aSUBfWg2Iv2EHRqA9FIjFuk9N038X8o60H8fn4jZmZeuFAlYIIrzdsHyhtkrzO9htm5FAZR1RheGHQV8KytKG-TETkjgue_QuCEQHTrJ7oPhdKUZlQDNzA7BHmYdkB5B7OeOj1ty73uoa8zpzOQsNM99PXLyfkXMCcplz8myGe_gsbZ_CKGVnXCKD381K0k6COeK0LKslA1lWLfs8K44YnqdQFr1RYtnUyycAhUVYFOQP8K53p424Z2PH65NnkBW4NoCugaBFMwnn2biVPirec7ZD6OdTumsQV7yB0FyQTTHR9wDbrmorQL93JZjs0DasgxsY8l3HkvTY7z5m3VfhyuW0OlMblz2cEB896oZGgKTIzuYZGY4HZ_69l6_wHcw-p8)](https://mermaid-js.github.io/mermaid-live-editor/edit#pako:eNp1ks-KgzAQh18l5Ny-QA4L3aSUBfWg2Iv2EHRqA9FIjFuk9N038X8o60H8fn4jZmZeuFAlYIIrzdsHyhtkrzO9htm5FAZR1RheGHQV8KytKG-TETkjgue_QuCEQHTrJ7oPhdKUZlQDNzA7BHmYdkB5B7OeOj1ty73uoa8zpzOQsNM99PXLyfkXMCcplz8myGe_gsbZ_CKGVnXCKD381K0k6COeK0LKslA1lWLfs8K44YnqdQFr1RYtnUyycAhUVYFOQP8K53p424Z2PH65NnkBW4NoCugaBFMwnn2biVPirec7ZD6OdTumsQV7yB0FyQTTHR9wDbrmorQL93JZjs0DasgxsY8l3HkvTY7z5m3VfhyuW0OlMblz2cEB896oZGgKTIzuYZGY4HZ_69l6_wHcw-p8)

</div>

So let's create those interfaces for all use cases, repositories and data sources. We'll use the following folder structure

```
│── presentation
│   └── contact
│       ├── create
│       │   ├── CreateContactViewModel.kt
│       │   └── CreateContactView.kt
│       └── list
│           ├── ListContactViewModel.kt
│           └── ListContactView.kt
├── domain
│   ├── interfaces
│   │   ├── repositories
│   │   │    └── ContactRepository.kt
│   │   └── usecases
│   │       └── contact
│   │           ├── GetAllContactsUseCase.kt
│   │           ├── GetContactUseCase.kt
│   │           ├── UpdateContactUseCase.kt
│   │           ├── DeleteContactUseCase.kt
│   │           └── CreateContactUseCase.kt
│   ├── models
│   │   └── Contact.kt
│   ├── repositories
│   │   └── ContactRepository.kt
│   └── usecases
│       └── contact
│           ├── GetAllContacts.kt
│           └── CreateContact.kt
└── data
    ├── interfaces
    │   └── datasources
    │       └── ContactDataSource.kt
    └── datasources

```

#### GetAllContactsUseCase
```kt
interface GetAllContactsUseCase {
    suspend fun execute(): List<ContactResponseModel>
}
```

#### GetContactUseCase
```kt
interface GetContactUseCase {
    suspend fun execute(id: String): ContactResponseModel
}
```

#### UpdateContactUseCase
```kt
interface UpdateContactUseCase {
    suspend fun execute(id: String, data: ContactRequestModel): Boolean
}
```

#### DeleteContactUseCase
```kt
interface DeleteContactUseCase {
    suspend fun execute(id: String): Boolean
}
```

#### CreateContactUseCase
```kt
interface CreateContactUseCase {
    suspend fun execute(contact: ContactRequestModel): Boolean
}
```


#### ContactRepository
```kt
interface ContactRepository {
    suspend fun getContacts(): List<ContactResponseModel>
    suspend fun getContact(id: String): ContactResponseModel
    suspend fun deleteContact(id: String): Boolean
    suspend fun updateContact(id: String, data: ContactRequestModel): Boolean
    suspend fun createContact(data: ContactRequestModel): Boolean
}
```

#### ContactDataSource
```kt
interface ContactDataSource {
    suspend fun getAll(): List<ContactResponseModel>
    suspend fun getOne(id: String): ContactResponseModel
    suspend fun delete(id: String): Boolean
    suspend fun update(id: String, data: ContactRequestModel): Boolean
    suspend fun create(data: ContactRequestModel): Boolean
}
```

### Note about TDD:
*TDD should be done by writing tests first, however, for easy illustrative reasons we’ll show code then tests*

#### Create Contact View Model
```kt
class CreateContactViewModel constructor(private val createContact: CreateContactUseCase) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")

    val errorMessage: String
        get() = _errorMessage.value

    suspend fun createContact(data: ContactRequestModel): Boolean {
        return try {
            createContact.execute(data)
        } catch (err: Exception) {
            _errorMessage.value = "Error Creating Contact"
            false
        }
    }
}
```

#### Create Contact VM Tests

```kt
class CreateContactViewModelTest {
    @Test
    fun test_CreateContactViewModel() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        whenever(mockUseCase.execute(any())).thenReturn(true)
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel( name = "Paul"))
        assertEquals(result, true)
        assertEquals(vm.errorMessage, "")

    }

    @Test
    fun test_CreateContactViewModelWhenUseCaseThrowsError() = runBlocking {
        val mockUseCase = mock<CreateContactUseCase>()
        whenever(mockUseCase.execute(any())).thenThrow()
        val vm = CreateContactViewModel(mockUseCase)
        val result = vm.createContact(ContactRequestModel( name = "Paul"))
        assertEquals(result, false)
        assertEquals(vm.errorMessage, "Error Creating Contact")

    }


}

```

<div class="bg-red" style="text-align: center; padding:20px; margin:20px">
    <img src="/assets/img/blog/clean-architecture-android/create-contact-view-model-tests.png"  />
</div>

<div class="bg-red" style="text-align: center; padding:20px; margin:20px">
    <img src="/assets/img/blog/clean-architecture-android/create-contact-view-model-test-coverage.png"  />
</div>


Tests pass with 100% code coverage.
Let's do the same for the other two view models


#### Edit View Model
```kt
class EditContactViewModel constructor(
    private val deleteContactUseCase: DeleteContactUseCase,
    private val updateContactUseCase: UpdateContactUseCase
) :
    ViewModel() {
    private val _errorMessage = mutableStateOf("")

    val errorMessage: String
        get() = _errorMessage.value

    suspend fun deleteContact(id: String): Boolean {
        return try {
            deleteContactUseCase.execute(id)
        } catch (err: Exception) {
            _errorMessage.value = "Error Deleting Contact"
            false
        }
    }

    suspend fun updateContact(id: String, data: ContactRequestModel): Boolean {
        return try {
            updateContactUseCase.execute(id, data)
        } catch (err: Exception) {
            _errorMessage.value = "Error Updating Contact"
            false
        }
    }
}
```


#### Edit View Model Tests
```kt
class EditContactViewModelTest {

    @Test
    fun test_DeleteContact_Should_Return_True() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockDeleteUseCase.execute(any())).thenReturn(true)
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact("123")
        Assert.assertEquals(result, true)
        Assert.assertEquals(vm.errorMessage, "")
    }

    @Test
    fun test_DeleteContact_Should_Set_Error_Message_When_UseCase_Throws() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockDeleteUseCase.execute(any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.deleteContact("12345")
        Assert.assertEquals(result, false)
        Assert.assertEquals(vm.errorMessage, "Error Deleting Contact")

    }

    @Test
    fun test_UpdateContact_Should_Return_True() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockUpdateUseCase.execute(any(), any())).thenReturn(true)
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.updateContact("123", ContactRequestModel(name = "Paul"))
        Assert.assertEquals(result, true)
        Assert.assertEquals(vm.errorMessage, "")
    }

    @Test
    fun test_UpdateContact_Should_Set_Error_Message_When_UseCase_Throws() = runBlocking {
        val mockDeleteUseCase = mock<DeleteContactUseCase>()
        val mockUpdateUseCase = mock<UpdateContactUseCase>()
        whenever(mockUpdateUseCase.execute(any(), any())).thenThrow()
        val vm = EditContactViewModel(mockDeleteUseCase, mockUpdateUseCase)
        val result = vm.updateContact("12345", ContactRequestModel(name = "Paul"))
        Assert.assertEquals(result, false)
        Assert.assertEquals(vm.errorMessage, "Error Updating Contact")

    }
}
```

<div class="bg-red" style="text-align: center; padding:20px; margin:20px">
    <img src="/assets/img/blog/clean-architecture-android/edit-contact-vm-tests.png"  />
</div>

<div class="bg-red" style="text-align: center; padding:20px; margin:20px">
    <img src="/assets/img/blog/clean-architecture-android/edit-contact-vm-test-coverage.png"  />
</div>


<a href="https://developer.android.com/training/data-storage/room" target="_blank">Room Database</a>

<a href="https://github.com/nanosoftonline/clean-architecture-android-contacts" target="_blank">Check out the github repo here</a>
