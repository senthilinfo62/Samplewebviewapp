package com.example.mysampleapp.screens.tutorialpages

import android.Manifest
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.mysampleapp.R
import com.example.mysampleapp.utilites.GlobalButton
import com.example.mysampleapp.utilites.GlobalTextView
import com.example.mysampleapp.utilites.Logger

@Composable
fun TutorialPageFour(onNavigateToLogin: () -> Unit, modifier: Modifier = Modifier) {
    // Content for the fourth tutorial page
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
         Logger.e("Error","Permission Granted")
         onNavigateToLogin()
        } else {
            // Permission denied, handle as needed
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp)
            .background(Color.Green), // Ensure padding at the bottom
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            GlobalTextView(text = "Page Four")
        }
        Spacer(modifier = Modifier.padding(50.dp))
        GlobalButton(
            text = stringResource(R.string.submit),
            onClick = {
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        )
    }

}