package za.co.nanosoft.cleancontacts.domain.models

import java.util.*

data class ContactResponseModel(
    val id: Int,
    val name: String,
)


data class ContactRequestModel(
    val id: Int? = null,
    val name: String
)
