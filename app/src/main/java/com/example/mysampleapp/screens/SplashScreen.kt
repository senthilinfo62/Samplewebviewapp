@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mysampleapp.screens
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mysampleapp.MyApplication
import com.example.mysampleapp.R
import com.example.mysampleapp.utilites.PreferenceManager
import com.example.mysampleapp.viewmodel.SplashViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(viewModel: SplashViewModel, navigateToTutorial: () -> Unit,navigateToLogin :()-> Unit){
    // Manage the state for animation
    val animatedState = remember { mutableStateOf(true) }
    val context =MyApplication.applicationContext()
    // Start the timer or animation
    LaunchedEffect(Unit) {
        delay(2000) // Adjust the delay as needed
        animatedState.value = false
        delay(500) // Extra delay to ensure animation completes

        if(PreferenceManager.isFirstLaunch(context)){
            navigateToTutorial()
        }else{
            navigateToLogin()
        }

    }

    // Animated Logo
    AnimatedVisibility(visible = animatedState.value, enter = fadeIn(), exit = fadeOut()) {
        // Replace with your actual logo
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp) // Adjust size as needed
                .graphicsLayer(
                    scaleX = 1.5f,
                    scaleY = 1.5f
                )
        )
    }
}

