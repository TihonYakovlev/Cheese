package com.example.cheese.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cheese.R
import kotlinx.coroutines.delay


@Preview(showBackground = true)
@Composable
fun AuthorizationFlow() {
    val currentScreen = remember { mutableStateOf("phone_input") }
    val phoneNumber = remember { mutableStateOf("") }

    when (currentScreen.value) {
        "phone_input" -> PhoneInputScreen(
            onNext = { input ->
                phoneNumber.value = input
                currentScreen.value = "code_input"
            }
        )

        "code_input" -> CodeInputScreen(
            onBack = { currentScreen.value = "phone_input" },
            onSubmit = { code ->
                println("Phone: $phoneNumber, Code: $code")
            }
        )
    }
}

@Composable
fun CodeInputScreen(onBack: () -> Unit, onSubmit: (String) -> Unit) {
    val code = remember { mutableStateOf("") }
    val isError = remember { mutableStateOf(false) }
    val maxLength = 4
    val isResendEnabled = remember { mutableStateOf(false) }
    val timerText = remember { mutableStateOf("Выслать код повторно через 1:16") }

    LaunchedEffect(isResendEnabled.value) {
        if (!isResendEnabled.value) {
            val totalTime = 76
            for (timeLeft in totalTime downTo 0) {
                val minutes = timeLeft / 60
                val seconds = timeLeft % 60
                timerText.value = "Выслать код повторно через $minutes:${seconds.toString().padStart(2, '0')}"
                delay(1000L)
            }
            timerText.value = ""
            isResendEnabled.value = true
        }
    }

    AuthScreen(
        title = stringResource(R.string.enter_4_numbers),
        value = code.value,
        onValueChange = { input ->
            if (input.length <= maxLength && input.all { it.isDigit() }) {
                code.value = input
            }
        },
        onActionClick = {
            if (code.value == "1234") {
                isError.value = false
                onSubmit(code.value)
            } else {
                isError.value = true
            }
        },
        onBackClick = onBack,
        showBackButton = true,
        isError = isError.value,
        errorMessage = if (isError.value) "Неправильный код подтверждения" else null,
        resendTimerText = if (isResendEnabled.value) null else timerText.value
    )

    if (isResendEnabled.value) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            TextButton(onClick = {
                isResendEnabled.value = false
            }) {
                Text(text = "Отправить код повторно", color = Color.White)
            }
        }
    }
}



@Composable
fun PhoneInputScreen(onNext: (String) -> Unit) {
    val phone = remember { mutableStateOf("+7") }
    val maxLength = 12

    AuthScreen(
        title = stringResource(R.string.telephone),
        value = phone.value,
        onValueChange = { input ->
            if (input.length <= maxLength) {
                val validatedInput = input.filterIndexed { index, char ->
                    when (index) {
                        0 -> char == '+'
                        1 -> char == '7'
                        else -> char.isDigit()
                    }
                }
                if (validatedInput.startsWith("+7")) {
                    phone.value = validatedInput
                }
            }
        },
        onActionClick = { onNext(phone.value) }
    )
}

@Composable
fun AuthScreen(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    onActionClick: () -> Unit,
    onBackClick: (() -> Unit)? = null,
    showBackButton: Boolean = false,
    isError: Boolean = false,
    errorMessage: String? = null,
    resendTimerText: String? = null,
) {
    val backgroundImage = painterResource(R.drawable.authorization_background_image)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.matchParentSize(),
        )

        if (showBackButton && onBackClick != null) {
            TextButton(onClick = onBackClick) {
                Text(text = "Назад", color = Color.White)
            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            LogoFrame()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 14.sp
                )

                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        errorContainerColor = Color.White,
                        focusedBorderColor = if (isError) Color.Red else Color.White,
                        unfocusedBorderColor = if (isError) Color.Red else Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true,
                    isError = isError,
                )

                if (isError && errorMessage != null) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                if (resendTimerText != null) {
                    Text(
                        text = resendTimerText,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                TextButton(onClick = onActionClick) {
                    Text(text = "Далее", color = Color.White)
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            UserAgreementText()
        }
    }
}


@Composable
fun LogoFrame() {
    val logo = painterResource(R.drawable.logo)

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 170.dp, bottom = 40.dp)
    ) {
        Box {
            Image(
                painter = logo,
                contentDescription = null,
                modifier = Modifier
                    .padding(10.dp)
                    .size(150.dp),
            )
        }
        Text(
            text = stringResource(R.string.logo_main_name),
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.White
        )
        Text(
            text = stringResource(R.string.logo_under_main_name),
            color = Color.White
        )
    }
}

@Composable
fun UserAgreementText() {
    val annotatedString = buildAnnotatedString {
        append("Продолжая, я принимаю ")
        withLink(
            LinkAnnotation.Url(
                url = stringResource(R.string.user_agreement_url),
                styles = TextLinkStyles(SpanStyle(color = Color.White))
            )
        ) {
            append("пользовательское соглашение")
        }

        append(" и подтверждаю, что прочел ")

        withLink(
            LinkAnnotation.Url(
                url = stringResource(R.string.privacy_policy_url),
                styles = TextLinkStyles(SpanStyle(color = Color.White))
            )
        ) {
            append("политику конфиденциальности")
        }
    }

    Text(
        text = annotatedString,
        style = TextStyle(
            color = Color.Gray,
            fontSize = 14.sp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        textAlign = TextAlign.Center
    )
}
