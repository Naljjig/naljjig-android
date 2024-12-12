package com.naljjig.presentation.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.naljjig.R
import com.naljjig.core.designsystem.NaljjigTheme
import com.naljjig.presentation.component.AuthButton
import com.naljjig.presentation.component.AuthTextField
import com.naljjig.presentation.component.SnsButton

@Composable
fun SignUpScreen(
    navigateToLogin: () -> Unit
){
    val email = remember{ mutableStateOf("") }
    val password = remember{ mutableStateOf("") }
    val confirmPassword = remember{ mutableStateOf("") }

    val isEmailTextFieldFocused = remember{ mutableStateOf(false) }
    val isPasswordTextFieldFocused = remember{ mutableStateOf(false) }
    val isConfirmPasswordTextFieldFocused = remember{ mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(108.dp))

        Text(
            text = "Create Account",
            fontSize = 30.sp,
            fontWeight = FontWeight(700),
            color = NaljjigTheme.colors.titleText
        )

        Spacer(modifier = Modifier.height(72.dp))

        AuthTextField(
            type = "Email",
            value = email.value,
            onValueChanged = {newValue -> email.value = newValue},
            isTextFieldFocused = isEmailTextFieldFocused.value,
            onFocusedChange = {focusState -> isEmailTextFieldFocused.value = focusState}
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthTextField(
            type = "Password",
            value = password.value,
            onValueChanged = {newValue -> password.value = newValue} ,
            isTextFieldFocused = isPasswordTextFieldFocused.value,
            onFocusedChange = {focusState -> isPasswordTextFieldFocused.value = focusState}
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthTextField(
            type = "Confirm Password",
            value = confirmPassword.value,
            onValueChanged = {newValue -> confirmPassword.value = newValue} ,
            isTextFieldFocused = isConfirmPasswordTextFieldFocused.value,
            onFocusedChange = {focusState -> isConfirmPasswordTextFieldFocused.value = focusState}
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "forgot your password?",
            textAlign = TextAlign.End,
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = NaljjigTheme.colors.titleText
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthButton(
            type = "primary",
            text = "Sign Up",
            navigate = navigateToLogin
        )

        Spacer(modifier = Modifier.height(20.dp))

        AuthButton(
            type = "",
            text = "Already have an account",
            navigate = navigateToLogin
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Or continue with",
            fontSize = 14.sp,
            fontWeight = FontWeight(600),
            color = NaljjigTheme.colors.titleText
        )

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ){
            SnsButton(
                icon = R.drawable.ic_google,
                contentDescription = "google icon"
            )

            SnsButton(
                icon = R.drawable.ic_facebook,
                contentDescription = "facebook icon"
            )

            SnsButton(
                icon = R.drawable.ic_apple,
                contentDescription = "apple icon"
            )
        }
    }
}

@Composable
@Preview
fun SignUpScreenPreview(){
    NaljjigTheme{
        SignUpScreen {  }
    }
}