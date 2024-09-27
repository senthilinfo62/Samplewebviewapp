package com.example.mysampleapp.utilites

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp

@Composable
fun GlobalEditText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "",
    placeholder: String = "",
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions =KeyboardOptions.Default,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = Int.MAX_VALUE, // Dynamic maximum lines
    isPassword: Boolean = false,
    maxLength: Int = Int.MAX_VALUE// Flag to determine if it's a password field
) {
    val passwordVisualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None

    OutlinedTextField(
        value = value,
        onValueChange = {
            // Enforce maximum length
            if (it.length <= maxLength) {
                onValueChange(it)
            }
        },
        label = { Text(text = label, fontSize = 14.sp) },
        placeholder = { Text(text = placeholder, fontSize = 14.sp, color = Color.Gray) },
        modifier = modifier,
        isError = isError,
        keyboardOptions = keyboardOptions,
        visualTransformation = passwordVisualTransformation,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = maxLines,
    )
}