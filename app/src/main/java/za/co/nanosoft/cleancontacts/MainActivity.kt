package za.co.nanosoft.cleancontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import za.co.nanosoft.cleancontacts.presentation.contact.create.CreateContactView
import za.co.nanosoft.cleancontacts.presentation.contact.create.CreateContactViewModel
import za.co.nanosoft.cleancontacts.presentation.contact.list.ContactListView
import za.co.nanosoft.cleancontacts.presentation.contact.list.ListContactsViewModel


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                Router(navController)
            }
        }
    }
}


@Composable
fun Router(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "list"
    ) {
        composable("list") {
            val listContactsViewModel: ListContactsViewModel = hiltViewModel()
            ContactListView(navController, listContactsViewModel)
        }
        composable("create") {
            val createContactsViewModel: CreateContactViewModel = hiltViewModel()
            CreateContactView(navController, createContactsViewModel)
        }
    }
}

