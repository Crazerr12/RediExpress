package com.example.deliveryapp.presentation.ui.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.common.OutlinedTextBox
import com.example.deliveryapp.presentation.ui.onboarding.FinishButton

@Composable
fun ForgotPasswordScreen(
    sendOTP: () -> Unit,
    signIn: () -> Unit,
    modifier: Modifier = Modifier,
    vm: ForgotPasswordViewModel = hiltViewModel(),
) {
    val state = vm.state

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.forgot_password_title))
        Text(text = stringResource(id = R.string.enter_your_email_address))

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextBox(
            text = state.email,
            onValueChange = { vm.setEmail(it) },
            hint = R.string.email_hint,
            label = R.string.email_address,
            isError = state.emailIsError
        )

        Spacer(modifier = Modifier.weight(1f))

        FinishButton(
            buttonClick = sendOTP,
            underButtonCLick = signIn,
            buttonText = R.string.send_otp,
            textHelper = R.string.remember_password_back_to,
            textUnderButton = R.string.sign_in,
        )
    }
}