package com.naljjig.presentation.splash

import android.content.Intent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.naljjig.MainActivity
import com.naljjig.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen() {
    val alpha = remember {
        Animatable(0f)
    }

    val context = LocalContext.current

    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(1500)
        )
        delay(2000L)

        context.startActivity(
            Intent(context, MainActivity::class.java)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(alpha.value),
            painter = painterResource(R.drawable.img_splash),
            contentScale = ContentScale.FillWidth,
            contentDescription = "Splash Screen"
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview(){
    SplashScreen()
}