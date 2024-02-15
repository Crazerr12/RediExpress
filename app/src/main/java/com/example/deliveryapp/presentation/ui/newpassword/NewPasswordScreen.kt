package com.example.deliveryapp.presentation.ui.newpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.common.OutlinedTextBox

@Composable
fun NewPasswordScreen(
    signIn: () -> Unit,
    modifier: Modifier = Modifier,
    vm: NewPasswordViewModel = hiltViewModel(),
) {
    val state = vm.state

    Column(modifier = modifier.fillMaxSize()) {
        Text(text = stringResource(id = R.string.new_password))
        Text(text = stringResource(id = R.string.enter_new_password))

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextBox(
            text = state.password,
            onValueChange = { vm.setPassword(it) },
            hint = R.string.password_hint,
            label = R.string.password,
            showTrailingIcon = true,
        )

        Spacer(modifier = Modifier.weight(1f))

        OutlinedTextBox(
            text = state.confirmPassword,
            onValueChange = { vm.setPassword(it) },
            hint = R.string.password_hint,
            label = R.string.confirm_password,
            showTrailingIcon = true,
            isError = state.confirmPasswordIsError
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = signIn, enabled = state.logInIsEnabled) {
            Text(text = stringResource(id = R.string.log_in))
        }
    }
}