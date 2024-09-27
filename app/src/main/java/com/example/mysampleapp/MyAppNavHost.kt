package com.example.mysampleapp


import android.content.Context
import androidx.compose.runtime.Composable
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mysampleapp.screens.HomeScreen
import com.example.mysampleapp.screens.LoginScreen
import com.example.mysampleapp.screens.SettingsScreen
import com.example.mysampleapp.screens.SplashScreen
import com.example.mysampleapp.screens.tutorialpages.TutorialScreen
import com.example.mysampleapp.utilites.PreferenceManager
import com.example.mysampleapp.viewmodel.HomeViewModel
import com.example.mysampleapp.viewmodel.LoginViewModel
import com.example.mysampleapp.viewmodel.SettingsViewModel
import com.example.mysampleapp.viewmodel.SplashViewModel
import com.example.mysampleapp.viewmodel.TutorialViewModel


@Composable
fun MyAppNavHost(navController: NavHostController, context: Context, activity: FragmentActivity) {
    NavHost(navController, startDestination = "splash") {
        composable("splash") {
            val viewModel: SplashViewModel = hiltViewModel()
            SplashScreen(viewModel = viewModel, navigateToTutorial = {
                navController.navigate("tutorial") {
                    popUpTo("splash") { inclusive = true }
                }
            }, navigateToLogin = {
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            })
        }
        composable("tutorial") {
            val viewModel: TutorialViewModel = hiltViewModel()
            TutorialScreen(viewModel = viewModel) {
                PreferenceManager.setFirstLaunch(context, true)
                navController.navigate("login") {
                    popUpTo("splash") { inclusive = true }
                }
            }
        }
        composable("login") {
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(viewModel = viewModel, activity, onLoginSuccess = {
                navController.navigate("home") {
                    popUpTo("login") { inclusive = true }
                }
            },
                onBiometricAuthSuccess = {
                    navController.navigate("home") {
                        popUpTo("login") { inclusive = true }
                    }
                }, onBiometricAuthFailed = {
                    // Implement biometric authentication Failed logic here
                },
                onForgotPassword = {
                    // Implement forgot password logic here
                })
        }
        composable("home") {
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(viewModel = viewModel)
        }
        composable("settings") {
            val viewModel: SettingsViewModel = hiltViewModel()
            SettingsScreen(viewModel = viewModel)
        }
    }
}




