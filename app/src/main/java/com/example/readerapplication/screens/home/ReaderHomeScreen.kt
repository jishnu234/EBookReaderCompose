package com.example.readerapplication.screens.home

import android.icu.text.CaseMap
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.readerapplication.R


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
            ReaderFAB(){
                Log.d("Home", "ReadeHomeScreen: FAB Clicked")
            }
        }
    ) {
        //Todo: HomeScreen Content
    }
    
}

@Composable
fun ReaderAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showProfile: Boolean = true,
    navController: NavController
) {

   TopAppBar(
       modifier = modifier.padding(
           12.dp
       ),
       title = {
               Row(
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   if (showProfile) {
                       Icon(
                           modifier = Modifier
                               .scale(0.9f),
                           painter = painterResource(
                               id = R.drawable.ic_outline_book
                           ),
                           contentDescription = "Book logo",
                           tint = Color.Black
                       )
                       Spacer(modifier = Modifier.width(6.dp))
                   }
                   Text(
                       text = title,
                       fontSize = 16.sp,
                       color = Color.Red.copy(alpha = 0.7f)
                   )
               }
       },
       actions = {
                 Icon(painter = painterResource(
                     id = R.drawable.ic_baseline_exit_to_app_24),
                     contentDescription = "logout button",
                     tint = Color(0xFF064B06).copy(alpha = 0.7f)
                 )
       },
       elevation = 0.dp,
       backgroundColor = Color.Transparent,
       
   )
}

@Preview
@Composable
fun ReaderFAB(onTap: () -> Unit = {}) {

   FloatingActionButton(
       onClick = { onTap() },
       backgroundColor = Color(0xFF92CBDF),
   ) {
       Icon(
           Icons.Filled.Add,
           contentDescription = "logo Book",
           tint = Color.White
       )
   }

}
