package com.example.readerapplication.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.readerapplication.R
import com.example.readerapplication.model.MBook
import com.example.readerapplication.navigation.ReaderBookScreens
import com.google.firebase.auth.FirebaseAuth


//Login Screen Components
@Composable
fun ReaderLogo(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = "A. Reader",
        color = Color.Red.copy(
            alpha = 0.5f
        ),
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
fun EmailInput(
    modifier: Modifier,
    label: String,
    emailState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Next,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    InputField(
        modifier = modifier,
        label = label,
        valueState = emailState,
        imeAction = imeAction,
        enabled = enabled,
        keyboardType = KeyboardType.Email,
        keyboardActions = keyboardActions
    )
}

@Composable
fun InputField(
    modifier: Modifier,
    label: String,
    valueState: MutableState<String>,
    enabled: Boolean,
    imeAction: ImeAction = ImeAction.Next,
    isSingleLine: Boolean = true,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardType: KeyboardType = KeyboardType.Text
) {

    OutlinedTextField(
        value = valueState.value,
        onValueChange = { valueState.value = it },
        modifier = modifier
            .padding(
                start = 10.dp,
                end = 10.dp,
                bottom = 10.dp
            )
            .fillMaxWidth(),
        enabled = enabled,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        singleLine = isSingleLine,
        keyboardActions = keyboardActions
    )
}


@Composable
fun PasswordInput(
    modifier: Modifier = Modifier,
    label: String,
    passwordState: MutableState<String>,
    imeAction: ImeAction = ImeAction.Done,
    passwordVisibility: MutableState<Boolean>,
    keyboardType: KeyboardType = KeyboardType.NumberPassword,
    enabled: Boolean = true,
    keyboardActions: KeyboardActions) {


    val visualTransformation = if(passwordVisibility.value) VisualTransformation.None
    else PasswordVisualTransformation()
    OutlinedTextField(
        value = passwordState.value,
        onValueChange = { passwordState.value = it },
        modifier = modifier
            .fillMaxWidth()
            .padding(
                bottom = 10.dp,
                start = 10.dp,
                end = 10.dp
            ),
        label = { Text(text = label) },
        enabled = enabled,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(
            imeAction = imeAction,
            keyboardType = keyboardType
        ),
        singleLine = true,
        keyboardActions = keyboardActions,
        trailingIcon = {
            if (passwordState.value.isNotEmpty())
                Icon(
                    modifier = Modifier
                        .padding(12.dp)
                        .clip(CircleShape)
                        .clickable {
                            passwordVisibility.value = !passwordVisibility.value
                        },
                    painter = painterResource(
                        id = if(passwordVisibility.value) R.drawable.ic_password_hide
                        else R.drawable.ic_password_show),
                    contentDescription = "password toggle")
        }
    )
}


@Composable
fun SubmitButton(
    modifier: Modifier,
    textId: String,
    loading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {

    Button(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp),
        shape = CircleShape,
        enabled = !loading && validInputs,
        onClick = onClick
    ) {

        if (loading) CircularProgressIndicator(modifier = Modifier.size(25.dp))
        else Text(text = textId, modifier = Modifier.padding(5.dp))
    }
}



//Home Screen Components
@Preview
@Composable
fun BookListCard(
    modifier: Modifier = Modifier,
    book: MBook = MBook(
        title = "Android Jetpack Compose",
        author = "Thomas Kunneth"
    ),
    navController: NavController = NavController(LocalContext.current),
    onPressDetails:(String) -> Unit = {}
) {

//    val context = LocalContext.current
//    val resources = context.resources
//    val displayMetrics = resources.displayMetrics
//    val screenWidth = displayMetrics.widthPixels / displayMetrics.density
    Surface(
        modifier = modifier
            .width(200.dp)
            .height(260.dp)
            .padding(4.dp)
        ,
        elevation = 5.dp,
        color = Color.White,
        shape = RoundedCornerShape(30.dp)
    ) {
        Column(
            modifier = Modifier
                .clip(RoundedCornerShape(30.dp))
                .clickable {
                    onPressDetails.invoke(book.title.toString())
                },
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = modifier
                    .padding(start = 8.dp, end = 8.dp,
                        top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .height(120.dp)
                        .width(100.dp),
                    contentScale = ContentScale.FillBounds,
                    painter = rememberImagePainter(
                        data = "https://unsplash.com/photos/RRUrOVR4YZA/" +
                                "download?ixid=MnwxMjA3fDB8MXxhbGx8MzB8fHx8" +
                                "fHwyfHwxNjUwNDM5ODU1&force=true",
                        builder = {
                            crossfade(durationMillis = 1000)
                        }

                    ),
                    contentDescription = "Book Image",

                    )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Icon(
                        Icons.Filled.FavoriteBorder,
                        modifier = Modifier
                            .size(30.dp)
                            .padding(bottom = 3.dp),
                        contentDescription = "Favorite Icon"
                    )
                    RatingIcon(rating = 5f)

                }
            }//Row
            Text(
                modifier = Modifier.padding(
                    horizontal = 6.dp
                ),
                text = book.title.toString(),
                fontSize = 16.sp,
                maxLines = 2,
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Clip,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(
                    horizontal = 6.dp
                ),
                maxLines = 1,
                textAlign = TextAlign.Start,
                text = book.author.toString(),
                color = Color.Black,
                fontSize = 14.sp,
            )
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                ReaderStatusBanner(){
                    Log.d("Home", "BookListCard:  Invoked")
                }
            }


        }
    }
}


@Composable
fun ReaderTitle(
    modifier: Modifier = Modifier,
    title: String
) {
    Surface(
        modifier = modifier
    ) {
        Column(modifier = modifier) {
            Text(
                text = title,
                fontSize = 19.sp,
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Normal
            )

        }

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
                        Icons.Filled.Book,
                        modifier = Modifier
                            .scale(0.9f),
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

            IconButton(onClick = {
                FirebaseAuth.getInstance().signOut().run {
                    navController.navigate(ReaderBookScreens.LoginScreen.name)
                }
            }) {
                Icon(
                    Icons.Filled.Logout,
                    contentDescription = "logout button",
                    tint = Color(0xFF064B06)
                )
            }
        },
        elevation = 0.dp,
        backgroundColor = Color.Transparent
    )
}


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

@Composable
fun ReaderStatusBanner(
    label: String = "Reading",
    radius: Int = 30,
    onPress: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    bottomEnd = radius.dp,
                    topStart = radius.dp
                )
            )
            .clickable {
                onPress.invoke("")
            }
            .background(
                color = Color(0xFF43666B),
                shape = RoundedCornerShape(topStart = radius.dp)
            )

            .width(100.dp),
        contentAlignment = Alignment.Center,
    ) {
        /*
            * Text Restricted for unwanted expansion and replicate to multiple lines
        */
        Text(
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 4.dp),
            text = label,
            maxLines = 1,
            overflow = TextOverflow.Clip,//Now the text expanded to its parent size only
            color = Color.White,
            fontSize = 16.sp
        )
    }
}



@Composable
fun RatingIcon(
    rating: Float
) {
    Card(
        shape = RoundedCornerShape(50)
    ) {
        Column(
            modifier = Modifier
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Icon(
                if (rating > 0) Icons.Outlined.Star else Icons.Outlined.StarOutline,
                contentDescription = "Rating Icon"
            )
            Text(
                text = "%.1f".format(rating),
                fontSize = 19.sp,
            )
        }
    }

}




















