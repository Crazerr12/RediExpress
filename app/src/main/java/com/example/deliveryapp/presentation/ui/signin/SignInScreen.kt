package com.example.deliveryapp.presentation.ui.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.common.OutlinedTextBox
import com.example.deliveryapp.presentation.theme.Typography
import com.example.deliveryapp.presentation.theme.grayDarkColorLightTheme
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme
import com.example.deliveryapp.presentation.ui.onboarding.FinishButton
import com.example.deliveryapp.presentation.ui.signup.SignWithGoogle

@Composable
fun SignInScreen(
    signUp: () -> Unit,
    signIn: () -> Unit,
    forgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
    vm: SignInViewModel = hiltViewModel(),
) {
    val state = vm.state

    LaunchedEffect(state.error) {
        if (state.error == "successful") {
            signIn()
        }
    }

    if (state.error != null && state.error != "successful") {
        AlertDialog(
            onDismissRequest = { vm.dismissError() },
            title = { Text(text = "Возникла ошибка") },
            text = { Text(text = state.error) },
            confirmButton = {
                Button(onClick = { vm.dismissError() }) {
                    Text(text = "Ok")
                }
            },
        )
    }

    if (!state.isLoading) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(start = 24.dp, top = 33.dp, end = 24.dp, bottom = 24.dp)
        ) {
            Text(
                text = stringResource(id = R.string.welcome_back),
                style = Typography.headlineMedium,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.fill_in_your_email),
                style = Typography.bodyMedium,
                color = grayDarkColorLightTheme
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextBox(
                text = state.email,
                onValueChange = { vm.setEmail(it) },
                hint = R.string.email_hint,
                label = R.string.email_address,
                isError = state.emailIsError,
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextBox(
                text = state.password,
                onValueChange = { vm.setPassword(it) },
                hint = R.string.password_hint,
                label = R.string.password,
                showTrailingIcon = true,
            )

            Spacer(modifier = Modifier.height(17.dp))

            RememberPassword(
                rememberPassword = { vm.rememberPassword(it) },
                forgotPassword = forgotPassword,
                isChecked = state.isRemember,
            )

            Spacer(modifier = Modifier.height(187.dp))

            FinishButton(
                isEnable = state.logInIsEnable,
                buttonClick = {
                    vm.logInUser()
                }, underButtonCLick = signUp,
                textHelper = R.string.already_have_account,
                textUnderButton = R.string.sign_up, buttonText = R.string.log_in
            )

            Spacer(modifier = Modifier.height(18.dp))

            SignWithGoogle(signWithGoogle = {})
        }
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RememberPassword(
    rememberPassword: (Boolean) -> Unit,
    forgotPassword: () -> Unit,
    modifier: Modifier = Modifier,
    isChecked: Boolean = false,
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            Checkbox(
                modifier = Modifier
                    .padding(end = 4.dp),
                checked = isChecked,
                onCheckedChange = { rememberPassword(it) }
            )
        }

        Text(
            text = stringResource(id = R.string.remember_password),
            style = Typography.bodyMedium,
            color = grayDarkColorLightTheme,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(id = R.string.forgot_password_ref),
            style = Typography.bodyMedium,
            color = primaryColorLightTheme,
            modifier = Modifier
                .clickable { forgotPassword() }
        )
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    RememberPassword(rememberPassword = {}, forgotPassword = {})
}