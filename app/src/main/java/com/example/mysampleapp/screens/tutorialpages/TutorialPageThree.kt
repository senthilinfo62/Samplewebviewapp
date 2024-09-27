package com.example.mysampleapp.screens.tutorialpages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mysampleapp.utilites.GlobalTextView


@Composable
fun TutorialPageThree(){
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 15.dp).background(Color.Blue), // Ensure padding at the bottom
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .background(Color.White)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            GlobalTextView(text = "Page Three")
        }
    }
}