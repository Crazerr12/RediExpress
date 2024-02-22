package com.example.deliveryapp.presentation.ui.sendpackage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.common.DetailsPoint
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme

@Composable
fun SendPackageScreen(
    instantDeliveryClick: (SendPackageState) -> Unit,
    modifier: Modifier = Modifier,
    vm: SendPackageViewModel = hiltViewModel(),
) {

    val state = vm.state

    LazyColumn(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxWidth()
    ) {
        item { Spacer(modifier = Modifier.height(43.dp)) }

        item {
            DetailsPoint(
                firstPoint = state.details.address,
                firstPointChange = { vm.changeDetailsAddress(it) },
                secondPoint = state.details.country,
                secondPointChange = { vm.changeDetailsCountry(it) },
                thirdPoint = state.details.phoneNumber,
                thirdPointChange = { vm.changeDetailsPhoneNumber(it) },
                fourthPoint = state.details.others,
                fourthPointChange = { vm.changeDetailsOthers(it) },
                cardName = R.string.origin_details,
                icon = R.drawable.origin,
            )
        }

        item { Spacer(modifier = Modifier.height(43.dp)) }

        itemsIndexed(state.destinationDetails) { index, item ->
            DetailsPoint(
                firstPoint = state.destinationDetails[index].address,
                firstPointChange = { vm.changeDestinationDetailsAddress(it, index) },
                secondPoint = state.destinationDetails[index].country,
                secondPointChange = { vm.changeDestinationDetailsCountry(it, index) },
                thirdPoint = state.destinationDetails[index].phoneNumber,
                thirdPointChange = { vm.changeDestinationDetailsPhoneNumber(it, index) },
                fourthPoint = state.destinationDetails[index].others,
                fourthPointChange = { vm.changeDestinationDetailsOthers(it, index) },
                cardName = R.string.destination_details,
                icon = R.drawable.destination,
            )

            if (state.destinationDetails.last() != item)
                Spacer(modifier = Modifier.height(24.dp))
        }

        item { Spacer(modifier = Modifier.height(14.dp)) }

        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = { vm.addDestination() }) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_square),
                        contentDescription = null,
                        tint = primaryColorLightTheme
                    )
                }

                Text(text = stringResource(R.string.add_destination))
            }
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            DetailsPoint(
                firstPoint = state.packageDetails.packageItems,
                firstPointChange = { vm.changePackageItems(it) },
                secondPoint = state.packageDetails.weight.toString(),
                secondPointChange = { vm.changePackageWeight(it) },
                thirdPoint = state.packageDetails.worthOfItems.toString(),
                thirdPointChange = { vm.changePackageWorth(it) },
                isPackage = true,
                cardName = R.string.package_details,
            )
        }

        item { Spacer(modifier = Modifier.height(24.dp)) }

        item {
            Column {
                Text(text = stringResource(id = R.string.select_delivery_type))

                Spacer(modifier = Modifier.height(16.dp))

                Row(modifier = Modifier.fillMaxWidth()) {

                    UpperImageButton(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight(),
                        onClick = {
                            vm.getTrackingNumber()
                            instantDeliveryClick(state)
                        },
                        image = R.drawable.clock,
                        text = R.string.instant_delivery,
                    )

                    Spacer(modifier = Modifier.width(24.dp))

                    UpperImageButton(
                        modifier = Modifier.weight(1f),
                        onClick = { },
                        image = R.drawable.calendar,
                        text = R.string.scheduled_delivery,
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}

@Composable
fun UpperImageButton(
    onClick: () -> Unit,
    image: Int,
    text: Int,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = image), contentDescription = null)

            Spacer(modifier = Modifier.height(10.dp))

            Text(text = stringResource(id = text))
        }
    }
}