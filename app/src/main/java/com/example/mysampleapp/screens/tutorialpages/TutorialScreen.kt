package com.example.mysampleapp.screens.tutorialpages




import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mysampleapp.viewmodel.TutorialViewModel



@Composable
fun TutorialScreen(viewModel: TutorialViewModel = hiltViewModel(), navigateToLogin: () -> Unit){
    val pageCount = 4
   
    val pagerState = rememberPagerState(pageCount = { pageCount })
    var currentPage by remember { mutableStateOf(0) }

    LaunchedEffect(pagerState.currentPage) {
        currentPage = pagerState.currentPage
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // LazyRow for the tutorial pages
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(4f)
        ) { pageIndex ->
            TutorialPage(pageIndex,navigateToLogin ,modifier = Modifier.fillMaxSize())
        }

        // Custom Pager Indicator
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.padding(16.dp)
        ) {
            repeat(pageCount) { index ->
                DotIndicator(
                    isSelected = index == currentPage,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(100.dp))
    }
}

@Composable
fun DotIndicator(
    isSelected: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(8.dp)
            .background(
                color = if (isSelected) Color.Blue else Color.Gray,
                shape = CircleShape
            )
    )
}

@Composable
fun TutorialPage(pageIndex: Int,onNavigateToLogin: () -> Unit, modifier: Modifier = Modifier) {
    // Customize your pages based on index
    when (pageIndex) {
        0 -> TutorialPageOne()
        1 -> TutorialPageTwo()
        2 -> TutorialPageThree()
        3 -> TutorialPageFour(onNavigateToLogin, modifier)
    }
}






