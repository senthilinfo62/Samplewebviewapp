package com.example.mysampleapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mysampleapp.R
import com.example.mysampleapp.utilites.Constants
import com.example.mysampleapp.utilites.GlobalButton
import com.example.mysampleapp.utilites.GlobalEditText
import com.example.mysampleapp.utilites.GlobalTextView
import com.example.mysampleapp.utilites.Logger
import com.example.mysampleapp.viewmodel.LoginViewModel



@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    activity: FragmentActivity,
    onLoginSuccess: () -> Unit,
    onBiometricAuthSuccess: () -> Unit,
    onBiometricAuthFailed:()-> Unit,
    onForgotPassword: () -> Unit
){
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Collect the authentication result as state from the ViewModel's LiveData
    val authenticationResult by viewModel.authenticationResult.observeAsState()

    // Handle the authentication result in a LaunchedEffect block
    LaunchedEffect(authenticationResult) {
        when (authenticationResult) {
            Constants.AUTHENTICATION_SUCCEEDED -> {
                Logger.e("LoginScreen", "Authentication Sucesss")
                onBiometricAuthSuccess()
            }
            Constants.AUTHENTICATION_FAILED -> {
                Logger.e("LoginScreen", "Authentication Failed")
                onBiometricAuthFailed()
            }
            Constants.AUTHENTICATION_ERROR -> {
                // Handle error if needed
                Logger.e("LoginScreen", "Authentication Error")
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GlobalTextView(text = stringResource(id = R.string.login))

        Spacer(modifier = Modifier.height(16.dp))

        GlobalEditText(
            value = username,
            onValueChange = { username = it },
            maxLines = 1 ,
            maxLength = 32,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            ),
            label = stringResource(id = R.string.username)
        )
        Spacer(modifier = Modifier.height(16.dp))
        GlobalEditText(
            value = password,
            onValueChange = { password = it },
            isError = true,
            maxLength = 8,
            isPassword = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                capitalization = KeyboardCapitalization.None,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            ),
            maxLines = 1 ,// Single line for passwords
            label = stringResource(id = R.string.password)
        )

        Spacer(modifier = Modifier.height(16.dp))

        GlobalButton(
            onClick = { viewModel.login(username, password, onLoginSuccess) },
            text =  stringResource(id = R.string.login)
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(onClick = { viewModel.onForgotPassword() }) {
            Text(text = stringResource(id = R.string.forgot_password))
        }

        Spacer(modifier = Modifier.height(16.dp))

        GlobalButton(
            onClick = { viewModel.onBiometricAuth(activity,onBiometricAuthSuccess,onBiometricAuthFailed) },
            text = stringResource(id = R.string.biometric_auth)
        )
    }
}