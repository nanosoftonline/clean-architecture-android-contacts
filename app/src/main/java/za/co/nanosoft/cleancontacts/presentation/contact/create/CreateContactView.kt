package za.co.nanosoft.cleancontacts.presentation.contact.create

import android.text.Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.runBlocking
import za.co.nanosoft.cleancontacts.presentation.components.TextInput


@Composable
fun CreateContactView(
    navController: NavController,
    vm: CreateContactViewModel = hiltViewModel()
) {


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("New Contact")
                }, actions = {
                    Button(onClick = {
                        runBlocking {
                            vm.createContact()
                            navController.popBackStack()
                        }

                    }) {
                        Text("Save")
                    }
                }
            )
        }

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            TextInput(vm.name, { value -> vm.onNameChange(value) }, "Name")
        }
    }

}


