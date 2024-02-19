package com.example.deliveryapp.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme

@Composable
fun DetailsPoint(
    firstPoint: String,
    firstPointChange: (String) -> Unit,
    secondPoint: String,
    secondPointChange: (String) -> Unit,
    thirdPoint: String,
    thirdPointChange: (String) -> Unit,
    cardName: Int,
    modifier: Modifier = Modifier,
    fourthPoint: String = "",
    fourthPointChange: (String) -> Unit = {},
    icon: Int? = null,
    isPackage: Boolean = false,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (icon != null) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = primaryColorLightTheme
                )
            }
            Text(text = stringResource(cardName))
        }

        Spacer(modifier = Modifier.height(5.dp))

        DetailsTextField(
            value = firstPoint,
            changeText = firstPointChange,
            hint =
            if (!isPackage)
                R.string.address_hint
            else
                R.string.package_items_hint,
        )

        Spacer(modifier = Modifier.height(5.dp))

        DetailsTextField(
            value = secondPoint,
            changeText = secondPointChange,
            hint =
            if (!isPackage)
                R.string.country_hint
            else
                R.string.weight_of_item_hint,
        )

        Spacer(modifier = Modifier.height(5.dp))

        DetailsTextField(
            value = thirdPoint,
            changeText = thirdPointChange,
            hint =
            if (!isPackage)
                R.string.phone_number
            else
                R.string.worth_of_items_hint,
        )

        Spacer(modifier = Modifier.height(5.dp))

        if (!isPackage)
            DetailsTextField(
                value = fourthPoint,
                changeText = fourthPointChange,
                hint = R.string.others_hint,
            )
    }
}


@Composable
fun DetailsTextField(
    value: String,
    changeText: (String) -> Unit,
    hint: Int,
    modifier: Modifier = Modifier,
) {
    Surface(
        shadowElevation = 5.dp,
        modifier = modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = changeText,
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            placeholder = { Hint(text = hint) },
            shape = RectangleShape,
        )
    }
}

@Preview
@Composable
fun CardTextFieldPreview() {
    DetailsTextField(value = "", changeText = {}, hint = R.string.email_address)
}