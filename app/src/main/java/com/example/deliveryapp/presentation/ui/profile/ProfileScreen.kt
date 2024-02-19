package com.example.deliveryapp.presentation.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.theme.Typography
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme

@Composable
fun ProfileScreen(
    editProfile: () -> Unit,
    openStatementsAndReports: () -> Unit,
    openNotifications: () -> Unit,
    openCardAccountSettings: () -> Unit,
    openReferrals: () -> Unit,
    openAboutUs: () -> Unit,
    logOut: () -> Unit,
    modifier: Modifier = Modifier,
    vm: ProfileViewModel = hiltViewModel(),
) {
    val state = vm.state

    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        UserProfile(
            modifier = Modifier.padding(
                start = 10.dp, top = 27.dp, end = 19.dp, bottom = 19.dp
            ),
            name = state.name,
            currentBalance = state.currentBalance,
            photo = R.drawable.ic_launcher,
            showBalance = { vm.showBalance() },
            balanceIsShow = state.balanceIsShow,
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = stringResource(R.string.enable_dark_mode))

            Switch(checked = state.darkModeIsEnable, onCheckedChange = { vm.enableDarkMode() })
        }

        ProfileMenuCard(
            cardName = R.string.edit_profile,
            cardIcon = R.drawable.profile_item,
            cardDescription = R.string.name_phono_no_address,
            openMenuItem = editProfile
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.statements_and_reports,
            cardIcon = R.drawable.statements_icon,
            cardDescription = R.string.download_transaction,
            openMenuItem = openStatementsAndReports
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.notification_settings,
            cardIcon = R.drawable.notification_icon,
            cardDescription = R.string.mute_unmute,
            openMenuItem = openNotifications
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.card_and_bank,
            cardIcon = R.drawable.wallet_item,
            cardDescription = R.string.change_cards,
            openMenuItem = openCardAccountSettings
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.referrals,
            cardIcon = R.drawable.referrals_icon,
            cardDescription = R.string.check_no_of_friends,
            openMenuItem = openReferrals
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.about_us,
            cardIcon = R.drawable.about_us_icon,
            cardDescription = R.string.know_more_about_us,
            openMenuItem = openAboutUs
        )

        Spacer(modifier = Modifier.height(12.dp))

        ProfileMenuCard(
            cardName = R.string.log_out,
            cardIcon = R.drawable.log_out_icon,
            openMenuItem = logOut
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun UserProfile(
    name: String,
    currentBalance: Int,
    photo: Int,
    showBalance: () -> Unit,
    balanceIsShow: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painterResource(id = photo),
            contentDescription = stringResource(R.string.user_photo),
            modifier = Modifier
                .clip(CircleShape)
                .size(60.dp)
                .padding(top = 8.dp, bottom = 8.dp, start = 10.dp)
        )

        Column {
            Text(text = name)

            Row {
                Text(text = stringResource(R.string.current_balance))

                Text(
                    text = if (balanceIsShow) currentBalance.toString() else stringResource(id = R.string.password_hint),
                    style = Typography.bodyMedium,
                    color = primaryColorLightTheme
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        IconButton(
            onClick = { showBalance() },
            modifier = Modifier
                .padding(end = 10.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.show_data_icon),
                contentDescription = stringResource(R.string.show_balance_icon)
            )
        }
    }
}

@Composable
fun ProfileMenuCard(
    cardName: Int,
    cardIcon: Int,
    openMenuItem: () -> Unit,
    modifier: Modifier = Modifier,
    cardDescription: Int? = null,
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        onClick = { /*TODO*/ },
        modifier = modifier
            .padding(horizontal = 24.dp),
        shape = RectangleShape,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                modifier = Modifier.padding(end = 8.dp),
                painter = painterResource(id = cardIcon),
                contentDescription = stringResource(R.string.profile_menu_icon)
            )

            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(text = stringResource(id = cardName))
                if (cardDescription != null) {
                    Text(
                        text = stringResource(id = cardDescription),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(30.dp))

            IconButton(onClick = openMenuItem) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_right),
                    contentDescription = stringResource(R.string.go_to_this_point_of_menu)
                )
            }
        }
    }
}

@Preview
@Composable
fun CardPreview() {

}