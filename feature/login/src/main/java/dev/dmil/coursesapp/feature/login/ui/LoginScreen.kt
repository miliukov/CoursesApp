package dev.dmil.coursesapp.feature.login.ui

import android.content.Intent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.dmil.coursesapp.core.R
import androidx.core.net.toUri

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onLoginSuccess: () -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isButtonEnabled by viewModel.isButtonEnabled.collectAsStateWithLifecycle()

    LoginContent(
        uiState = uiState,
        onEmailChanged = viewModel::onEmailChanged,
        onPasswordChanged = viewModel::onPasswordChanged,
        onLoginClick = { onLoginSuccess() },
        isButtonEnabled = isButtonEnabled,
    )

}

@Composable
private fun LoginContent(
    uiState: LoginUiState,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    onLoginClick: () -> Unit,
    isButtonEnabled: Boolean,
) {

    val context = LocalContext.current

    val openUrl = { url: String ->
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        context.startActivity(intent)
    }

    Column() {
        Text(text = "Вход")
        Text(text = "Email")
        TextField(
            value = uiState.email,
            onValueChange = { onEmailChanged(it) },
            placeholder = { Text("example@gmail.com") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Text(text = "Пароль")
        TextField(
            value = uiState.password,
            onValueChange = { onPasswordChanged(it) },
            placeholder = { Text("Введите пароль") },
            visualTransformation = PasswordVisualTransformation()
        )
        Button(
            onClick = onLoginClick,
            enabled = isButtonEnabled
        ) {
            Text(text = "Вход")
        }
        Row {
            Text(text = "Нет аккаунта?")
            TextButton(
                onClick = {  }
            ) {
                Text(text = "Регистрация")
            }
        }
        TextButton(
            onClick = {  }
        ) {
            Text(text = "Забыл пароль")
        }
        HorizontalDivider()
        Row {
            Button(
                onClick = { openUrl("https://vk.com/") }
            ) {
                Icon(
                    painter = painterResource(R.drawable.vk_logo),
                    contentDescription = "VK logo"
                )
            }
            Button(
                onClick = { openUrl("https://ok.ru/") }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ok_logo),
                    contentDescription = "OK logo"
                )
            }
        }
    }
}