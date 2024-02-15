package com.example.deliveryapp.presentation.ui.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.deliveryapp.R
import com.example.deliveryapp.presentation.models.OnBoardingPage
import com.example.deliveryapp.presentation.theme.Typography
import com.example.deliveryapp.presentation.theme.grayDarkColorLightTheme
import com.example.deliveryapp.presentation.theme.primaryColorLightTheme
import com.example.deliveryapp.presentation.theme.textLightColorLightTheme
import com.example.deliveryapp.presentation.theme.whiteColorLightTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    signIn: () -> Unit,
    signUp: () -> Unit,
    modifier: Modifier = Modifier,
    vm: OnBoardingViewModel = hiltViewModel(),
) {
    val state = vm.state

    val pagerState = rememberPagerState {
        state.count
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxWidth(),
            userScrollEnabled = false,
            verticalAlignment = Alignment.Top,
        ) {
            PagerScreen(
                onBoardingPage = state.currentPage,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 66.dp, start = 24.dp, end = 24.dp),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        if (state.onBoardingQueue.isEmpty()) {
            FinishButton(buttonClick = {
                vm.saveOnBoarding()
                signUp()
            }, underButtonCLick = {
                vm.saveOnBoarding()
                signIn()
            },
                buttonText = R.string.sign_up,
                textUnderButton = R.string.sign_in,
                textHelper = R.string.already_have_account,
                modifier = Modifier.padding(bottom = 64.dp, start = 24.dp, end = 24.dp)
            )
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 100.dp, start = 24.dp, end = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ButtonOnBoard(
                    modifier = Modifier.testTag("Skip"),
                    onClick = {
                        state.onBoardingQueue.clear()
                        vm.saveOnBoarding()
                        signUp()
                    },
                    content = R.string.skip,
                    containerColor = whiteColorLightTheme,
                    contentColor = primaryColorLightTheme,
                )

                ButtonOnBoard(
                    modifier = Modifier.testTag("Next"),
                    onClick = {
                        vm.onNextButtonClick()
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    content = R.string.next,
                    containerColor = primaryColorLightTheme,
                    contentColor = whiteColorLightTheme,
                )
            }
        }
    }
}

@Composable
fun PagerScreen(onBoardingPage: OnBoardingPage, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = onBoardingPage.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Spacer(modifier = Modifier.height(48.dp))

        OnBoardDescription(
            onBoardingPage.title,
            onBoardingPage.description,
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )
    }
}

@Composable
fun OnBoardDescription(title: Int, description: Int, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            text = stringResource(id = title),
            style = Typography.headlineSmall,
            textAlign = TextAlign.Center,
            color = primaryColorLightTheme,
        )

        Text(
            modifier = Modifier
                .padding(horizontal = 13.dp, vertical = 5.dp)
                .fillMaxWidth(),
            text = stringResource(id = description),
            style = Typography.bodySmall,
            textAlign = TextAlign.Center,
            color = textLightColorLightTheme,
        )
    }
}

@Composable
fun ButtonOnBoard(
    onClick: () -> Unit,
    content: Int,
    modifier: Modifier = Modifier,
    containerColor: Color = primaryColorLightTheme,
    contentColor: Color = whiteColorLightTheme,
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(1.dp, primaryColorLightTheme),
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor, contentColor = contentColor
        ),
        shape = RoundedCornerShape(5.dp),
    ) {
        Text(
            text = stringResource(content), style = Typography.labelMedium
        )
    }
}

@Composable
fun FinishButton(
    buttonClick: () -> Unit,
    underButtonCLick: () -> Unit,
    buttonText: Int,
    textUnderButton: Int,
    textHelper: Int,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
) {
    Column(
        modifier = modifier
    ) {
        Button(
            enabled = isEnable,
            onClick = buttonClick,
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(5.dp)
        ) {
            Text(
                color = whiteColorLightTheme,
                text = stringResource(id = buttonText),
                style = Typography.bodyMedium
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                color = grayDarkColorLightTheme,
                text = stringResource(id = textHelper),
                style = Typography.labelLarge
            )
            Text(
                modifier = Modifier
                    .testTag("Sign In")
                    .clickable { underButtonCLick() },
                text = stringResource(id = textUnderButton),
                style = Typography.labelLarge,
                color = primaryColorLightTheme,
            )
        }
    }
}

@Preview
@Composable
private fun OnBoardingScreenFirstPreview() {
    PagerScreen(OnBoardingPage.First)
}

@Preview
@Composable
private fun OnBoardingScreenSecondPreview() {
    PagerScreen(OnBoardingPage.Second)
}

@Preview
@Composable
private fun OnBoardingScreenThirdPreview() {
    PagerScreen(OnBoardingPage.Third)
}