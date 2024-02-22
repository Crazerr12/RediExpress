package com.example.deliveryapp.presentation.ui.wallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.theme.hintTextColorLightTheme
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme
import com.example.deliveryapp.presentation.theme.whiteColorLightTheme
import com.example.deliveryapp.presentation.ui.profile.UserProfile

@Composable
fun WalletScreen(modifier: Modifier = Modifier, vm: WallerViewModel = hiltViewModel()) {

    val state = vm.state

    Column(
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(41.dp))

        UserProfile(
            name = state.name,
            currentBalance = state.balance.toFloat(),
            photo = R.drawable.ic_launcher,
            showBalance = { vm.showBalance() },
            balanceIsShow = state.balanceIsShow
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = hintTextColorLightTheme
            )
        ) {
            Spacer(modifier = Modifier.height(10.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = stringResource(R.string.top_up))

                Spacer(modifier = Modifier.height(12.dp))

                Row(horizontalArrangement = Arrangement.SpaceAround) {
                    TopUpItem(
                        icon = R.drawable.bank_icon,
                        name = R.string.bank
                    )
                    TopUpItem(
                        icon = R.drawable.transfer_icon,
                        name = R.string.transfer
                    )
                    TopUpItem(
                        icon = R.drawable.card_icon,
                        name = R.string.card
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(41.dp))

        Text(text = stringResource(R.string.transaction_history))


    }
}

@Composable
fun TopUpItem(icon: Int, name: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Button(
            onClick = { },
            shape = CircleShape, colors = ButtonDefaults.buttonColors(
                containerColor = primaryColorLightTheme
            )
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = whiteColorLightTheme,
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(text = stringResource(id = name))
    }
}