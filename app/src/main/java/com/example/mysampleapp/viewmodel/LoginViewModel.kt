package com.example.mysampleapp.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysampleapp.MyApplication
import com.example.mysampleapp.utilites.Constants
import com.example.mysampleapp.utilites.DeviceUtilities
import com.example.mysampleapp.utilites.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.Executor
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    // Inject any dependencies required by the ViewModel here
) :ViewModel(){
    @SuppressLint("StaticFieldLeak")
    private val context: Context = MyApplication.applicationContext()
    private var executor: Executor = ContextCompat.getMainExecutor(context)
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private val _authenticationResult = MutableLiveData<String>()
    val authenticationResult: LiveData<String>
        get() = _authenticationResult



    private fun initBiometricPrompt(activity: FragmentActivity) {
        val callback = object : BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                super.onAuthenticationError(errorCode, errString)
                _authenticationResult.postValue(Constants.AUTHENTICATION_ERROR + " " + errString)
            }

            @RequiresApi(Build.VERSION_CODES.R)
            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                Logger.e("LoginViewModel", result.authenticationType.toString())
                Logger.e("LoginViewModel", result.cryptoObject?.identityCredential.toString())
                _authenticationResult.postValue(Constants.AUTHENTICATION_SUCCEEDED)
            }

            override fun onAuthenticationFailed() {
                super.onAuthenticationFailed()
                _authenticationResult.postValue(Constants.AUTHENTICATION_FAILED)
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val authFlag = DEVICE_CREDENTIAL or BIOMETRIC_STRONG
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(Constants.BIOMETRIC_AUTHENTICATION)
                .setSubtitle(Constants.BIOMETRIC_AUTHENTICATION_SUBTITLE)
                .setDescription(Constants.BIOMETRIC_AUTHENTICATION_DESCRIPTION)
                .setAllowedAuthenticators(authFlag)
                .build()
        } else {
            @Suppress("DEPRECATION")
            promptInfo = BiometricPrompt.PromptInfo.Builder()
                .setTitle(Constants.PASSWORD_PIN_AUTHENTICATION)
                .setSubtitle(Constants.PASSWORD_PIN_AUTHENTICATION)
                .setDescription(Constants.PASSWORD_PIN_AUTHENTICATION_DESCRIPTION)
                .setDeviceCredentialAllowed(true)
                .build()
        }

        biometricPrompt = BiometricPrompt(activity, executor, callback)
    }

    fun checkDeviceSecurityFeatures(): Boolean {
        return DeviceUtilities.deviceHasPasswordPinLock(context)
    }

    fun isBiometricHardwareAvailable(): Boolean {
        return DeviceUtilities.isBiometricHardWareAvailable(context)
    }




    fun login(username: String, password: String, onLoginSuccess: () -> Unit) {
        if (username == "test" && password == "password") {
            onLoginSuccess()
        }
    }

    fun onForgotPassword() {
      Logger.d("LoginViewModel", "Forget password Cliked")
    }


    fun onBiometricAuth(
        activity: FragmentActivity,
        onBiometricAuthSuccess: () -> Unit,
        onBiometricAuthFailed: () -> Unit
    ) {
        Logger.e("LoginViewModel","Authendication Cliked")
            initBiometricPrompt(activity)
            biometricPrompt.authenticate(promptInfo)
    }

}