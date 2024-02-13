package com.example.deliveryapp.presentation.ui.signup

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.common.OutlinedTextBox
import com.example.deliveryapp.presentation.theme.Typography
import com.example.deliveryapp.presentation.theme.grayDarkColorLightTheme
import com.example.deliveryapp.presentation.ui.onboarding.FinishButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    signIn: () -> Unit,
    modifier: Modifier = Modifier,
    vm: SignUpViewModel = hiltViewModel(),
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
            }
        )
    }
    if (!state.isLoading) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier.padding(top = 33.dp),
                text = stringResource(id = R.string.create_an_account),
                style = Typography.headlineMedium,
                color = Color.Black
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.complete_sign_up),
                style = Typography.bodyMedium,
                color = grayDarkColorLightTheme
            )

            Spacer(modifier = Modifier.height(33.dp))

            OutlinedTextBox(
                text = state.name,
                onValueChange = { vm.setName(it) },
                hint = R.string.name_hint,
                label = R.string.full_name,
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextBox(
                text = state.phoneNumber,
                onValueChange = { vm.setPhone(it) },
                hint = R.string.phone_number_hint,
                label = R.string.phone_number,
            )

            Spacer(modifier = Modifier.height(24.dp))

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

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextBox(
                text = state.confirmedPassword,
                onValueChange = { vm.setConfirmedPassword(it) },
                hint = R.string.password_hint,
                label = R.string.confirm_password,
                showTrailingIcon = true,
                isError = state.confirmedPasswordIsError
            )

            Spacer(modifier = Modifier.height(37.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                    Checkbox(
                        modifier = Modifier
                            .padding(end = 11.dp),
                        checked = state.isAgreed,
                        onCheckedChange = { vm.setAgreement(it) })
                }

                Text(
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.by_ticking_this_box)
                )
            }

            Spacer(modifier = Modifier.height(64.dp))

            FinishButton(
                buttonClick = {
                    vm.registerUser()
                    signIn()
                },
                underButtonCLick = signIn,
                isEnable = state.signUpIsEnabled,
                textUnderButton = R.string.sign_in,
                textHelper = R.string.already_have_account,
                buttonText = R.string.sign_up
            )

            Spacer(modifier = Modifier.height(16.dp))

            SignWithGoogle(signWithGoogle = { })
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

@Composable
fun SignWithGoogle(signWithGoogle: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.or_sign_in_using),
        )

        IconButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            onClick = signWithGoogle,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.google),
                contentDescription = stringResource(
                    R.string.sign_with_google
                )
            )
        }
    }
}