package com.example.readerapplication.screens.home

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readerapplication.components.BookListCard
import com.example.readerapplication.components.ReaderAppBar
import com.example.readerapplication.components.ReaderFAB
import com.example.readerapplication.components.ReaderTitle
import com.example.readerapplication.model.MBook
import com.example.readerapplication.navigation.ReaderBookScreens
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ReadeHomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "A.Reader",
                navController = navController,
            )
        },
        floatingActionButton = {
            ReaderFAB() {
                Log.d("Home", "ReadeHomeScreen: FAB Clicked")
            }
        }
    ) {
        HomeScreenContent(navController = navController)
    }

}


@Preview
@Composable
fun HomeScreenContent(navController: NavController = NavController(LocalContext.current)) {

    val bookList = listOf<MBook>(
        MBook(id = "cdshjb", title = "Android Jetpack Compose",
            author = "Thomas Kunnath", notes = null),
        MBook(id = "bn", title = " Compose", author = "Thomas ",
            notes = null),
        MBook(id = "cdsshjb", title = " Jetpack Compose",
            author = " Kunnath", notes = null),
        MBook(id = "cscs", title = "Android Jetpack Compose",
            author = "Thomas Kunnath", notes = null),
        MBook(id = "cscdss", title = "Android Jetpack Compose",
            author = "Thomas Kunnath", notes = null),
        MBook(id = "vrgvr", title = "Android Jetpack Compose",
            author = "Thomas Kunnath", notes = null),
    )

    val currentUser = FirebaseAuth.getInstance().currentUser?.email
        ?.split('@')?.get(0) ?: "N/A"
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Row(
                horizontalArrangement = Arrangement.Start
            ) {
                ReaderTitle(title = "Your Reading\nactivity right now..")
                Spacer(modifier = Modifier.fillMaxWidth(0.7f))
                Column(
                    modifier = Modifier
                        .width(80.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(Icons.Filled.SupervisedUserCircle,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ReaderBookScreens.ReaderStatsScreen.name)
                            }
                            .size(45.dp),
                        contentDescription = "User Profile")
                    Text(
                        text = currentUser,
                        modifier = Modifier
                            .padding(2.dp),
                        style = MaterialTheme.typography.overline,
                        fontSize = 15.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Clip,
                    )
                    Divider()
                }
            }//Row

            CurrentReadingList(book = listOf(), navController = navController)
            ReaderTitle(
                modifier = Modifier.padding(vertical = 8.dp),
                title = "Reading List")
            BookListShelf(books = bookList,
                navController = navController)

        }
    }
}

@Composable
fun BookListShelf(
    books: List<MBook>,
    navController: NavController
) {
    HorizontalListComponent(books){
        //Todo: Card Response
        Log.d("Home Screen", "BookListShelf: $it")
    }
}

@Composable
fun HorizontalListComponent(books: List<MBook>, onCardPressed:(String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(rememberScrollState())
    ) {
            for (book in books){
                BookListCard(book = book){
                    onCardPressed.invoke(it)
                }
            }
    }
}

@Composable
fun CurrentReadingList(book: List<MBook>, navController: NavController) {
    BookListCard(){
        Log.d("Card", "HomeScreenContent: $it")
    }
}






