import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.navigation.Graph

enum class BottomItemScreen(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String,
    val graph: String,
) {
    HOME(title = R.string.home_item, R.drawable.home_item, "home_item", Graph.HOME),
    WALLET(title = R.string.wallet_item, R.drawable.wallet_item, "wallet_item", Graph.WALLET),
    TRACK(title = R.string.track_item, R.drawable.track_item, "track_item", Graph.TRACK),
    PROFILE(title = R.string.profile_item, R.drawable.profile_item, "profile_item", Graph.PROFILE),
}