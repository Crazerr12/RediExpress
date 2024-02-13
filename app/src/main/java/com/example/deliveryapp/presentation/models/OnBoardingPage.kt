package com.example.deliveryapp.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.deliveryapp.R
import kotlinx.serialization.Serializable

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    @StringRes
    val title: Int,
    @StringRes
    val description: Int,
) {
    data object First : OnBoardingPage(
        image = R.drawable.on_board_first,
        title = R.string.quick_delivery,
        description = R.string.enjoy_quick,
    )

    data object Second : OnBoardingPage(
        image = R.drawable.on_board_second,
        title = R.string.flexible_payment,
        description = R.string.different_models,
    )

    data object Third : OnBoardingPage(
        image = R.drawable.on_board_third,
        title = R.string.real_time_tracking,
        description = R.string.track_your_packages,
    )
}