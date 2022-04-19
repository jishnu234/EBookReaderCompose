package com.example.readerapplication.screens


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.readerapplication.components.ReaderLogo
import com.example.readerapplication.navigation.ReaderBookScreens
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
fun ReaderSplashScreen(navController: NavController = rememberNavController()) {

    val scale = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            1f,
            animationSpec = tween(
                durationMillis = 1000,
                easing = {
                    OvershootInterpolator(10f)
                        .getInterpolation(it)
                }))
        delay(2000L)
        navController.navigate(ReaderBookScreens.LoginScreen.name)
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Surface(
            modifier = Modifier
                .size(300.dp)
                .scale(scale.value)
                .padding(12.dp),
            shape = CircleShape,
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Column(
                modifier = Modifier
                    .padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ReaderLogo()
                Spacer(
                    modifier = Modifier
                        .height(8.dp)
                )
                Text(
                    text = "\"Read. Change. YourSelf\"",
                    color = Color.LightGray,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

            }
        }
    }
}
