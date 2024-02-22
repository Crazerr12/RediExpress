package com.example.deliveryapp.presentation.ui.sendpackage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme

@Composable
fun SendPackageReceiptScreen(
    editPackage: () -> Unit,
    makePayment: () -> Unit,
    packageData: SendPackageState,
    modifier: Modifier = Modifier,
    vm: SendPackageReceiptViewModel = hiltViewModel(),
) {
    vm.receiveData(data = packageData)
    val state = vm.state

    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = stringResource(R.string.package_information),
            color = primaryColorLightTheme
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.origin_details),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = "${packageData.details.address}, ${packageData.details.country}",
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = packageData.details.phoneNumber,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.destination_details),
            color = Color.Black
        )

        packageData.destinationDetails.forEachIndexed { index, details ->

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "${index + 1}. ${details.address} ${details.country}",
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = packageData.details.phoneNumber,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.other_details),
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.package_items,
            fieldValue = packageData.packageDetails.packageItems
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.weight_of_item_hint,
            fieldValue = packageData.packageDetails.weight.toString()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.worth_of_items_hint,
            fieldValue = packageData.packageDetails.worthOfItems.toString()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.tracking_number,
            fieldValue = packageData.trackingNumber
        )

        Spacer(modifier = Modifier.height(37.dp))

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.charges),
            color = primaryColorLightTheme
        )

        Spacer(modifier = Modifier.height(10.dp))

        DetailsReceiptField(
            fieldName = R.string.delivery_charges,
            fieldValue = state.deliveryCharges.toString()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.instant_delivery,
            fieldValue = state.instantDelivery.toString()
        )

        Spacer(modifier = Modifier.height(8.dp))

        DetailsReceiptField(
            fieldName = R.string.tax_and_service_charges,
            fieldValue = state.taxAndServiceCharges.toString()
        )

        Spacer(modifier = Modifier.height(9.dp))

        HorizontalDivider(modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(4.dp))

        DetailsReceiptField(
            fieldName = R.string.package_total,
            fieldValue = state.taxAndServiceCharges.toString()
        )

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(
                modifier = Modifier.weight(1f),
                onClick = editPackage,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = primaryColorLightTheme
                )
            ) {
                Text(text = stringResource(id = R.string.edit_package))
            }

            Spacer(modifier = Modifier.width(24.dp))

            Button(
                modifier = Modifier.weight(1f),
                onClick = makePayment,
                colors = ButtonDefaults.buttonColors(
                    containerColor = primaryColorLightTheme,
                    contentColor = Color.White
                )
            ) {
                Text(text = stringResource(id = R.string.edit_package))
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun DetailsReceiptField(fieldName: Int, fieldValue: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = stringResource(id = fieldName))

        Spacer(modifier = Modifier.width(60.dp))

        Text(
            textAlign = TextAlign.End,
            text = fieldValue,
            color = Color.Blue
        )
    }
}