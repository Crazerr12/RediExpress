package com.example.deliveryapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.theme.Typography
import com.example.deliveryapp.presentation.theme.grayDarkColorLightTheme
import com.example.deliveryapp.presentation.theme.hintTextColorLightTheme

@Composable
fun OutlinedTextBox(
    text: String,
    onValueChange: (String) -> Unit,
    hint: Int,
    label: Int,
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    showTrailingIcon: Boolean = false,
) {

    var isDataShow by remember {
        mutableStateOf(showTrailingIcon)
    }

    Column(modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = label),
            style = Typography.bodyMedium,
            color = grayDarkColorLightTheme
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            singleLine = true,
            onValueChange = onValueChange,
            textStyle = Typography.labelMedium,
            visualTransformation = if (!isDataShow) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                if (showTrailingIcon) {
                    IconButton(onClick = {
                        isDataShow = !isDataShow
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.show_data_icon),
                            tint = Color.Black,
                            contentDescription = "Show data"
                        )
                    }
                }
            },
            isError = isError,
            placeholder = { Hint(text = hint) },
        )
    }
}

@Composable
fun Hint(text: Int) {
    Text(
        text = stringResource(id = text),
        style = Typography.bodyMedium,
        color = hintTextColorLightTheme,
    )
}


@Preview
@Composable
fun OutlinedTextBoxPreview() {
    OutlinedTextBox("", {}, R.string.sign_in, R.string.real_time_tracking, isError = true)
}