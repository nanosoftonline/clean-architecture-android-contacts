package za.co.nanosoft.cleancontacts.domain.interfaces.usecases

interface DeleteContactUseCase {
    suspend fun execute(id: Int)
}