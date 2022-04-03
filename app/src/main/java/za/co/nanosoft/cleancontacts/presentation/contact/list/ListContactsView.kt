package za.co.nanosoft.cleancontacts.presentation.contact.list

import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.runBlocking


@Composable
fun ContactListView(vm: ListContactsViewModel = hiltViewModel()) {

    LaunchedEffect(Unit, block = {
        vm.getContacts()
    })


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Contacts")
                },  actions = {
                    Button(onClick = {
                        runBlocking {
                            print("Clicked")
                        }

                    }) {
                        Text("Add")
                    }
                }
            )
        },
        content = {
            Text(vm.contacts.count().toString())
        }
    )

}