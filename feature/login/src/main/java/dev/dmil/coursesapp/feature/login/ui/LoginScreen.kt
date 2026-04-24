package dev.dmil.coursesapp.feature.login.ui

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.core.net.toUri
import dev.dmil.coursesapp.core.ui.theme.Blue
import dev.dmil.coursesapp.core.ui.theme.DarkGrey
import dev.dmil.coursesapp.core.ui.theme.Green
import dev.dmil.coursesapp.core.ui.theme.OrangeGradient
import dev.dmil.coursesapp.core.ui.theme.White
import dev.dmil.coursesapp.feature.login.R

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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Spacer(modifier = Modifier.height(160.dp))
        Text(
            text = "Вход",
            fontWeight = FontWeight(400),
            color = White,
            fontSize = 32.sp,
        )
        Spacer(modifier = Modifier.height(50.dp))
        Text(
            text = "Email",
            fontWeight = FontWeight(400),
            color = White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = uiState.email,
            onValueChange = { onEmailChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "example@gmail.com",
                    color = White.copy(alpha = 0.5f)
                )
                          },
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = DarkGrey,
                unfocusedContainerColor = DarkGrey,
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "Пароль",
            fontWeight = FontWeight(400),
            color = White,
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(5.dp))
        OutlinedTextField(
            value = uiState.password,
            onValueChange = { onPasswordChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = DarkGrey,
                unfocusedContainerColor = DarkGrey,
            ),
            placeholder = {
                Text(
                    text = "Введите пароль",
                    color = White.copy(alpha = 0.5f)
                )
                          },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = onLoginClick,
            enabled = isButtonEnabled,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonColors(
                disabledContentColor = White,
                containerColor = Green,
                contentColor = White,
                disabledContainerColor = Green
            )
        ) {
            Text(
                text = "Вход",
                modifier = Modifier.padding(5.dp),
                fontWeight = FontWeight(500),
                color = White,
                fontSize = 20.sp
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Нет аккаунта?",
                fontWeight = FontWeight(600),
                color = White,
                fontSize = 14.sp
            )
            TextButton(
                onClick = {  }
            ) {
                Text(
                    text = "Регистрация",
                    fontWeight = FontWeight(600),
                    color = Green,
                    fontSize = 14.sp
                )
            }
        }
        Text(
            text = "Забыл пароль",
            color = Green,
            fontWeight = FontWeight(600),
            fontSize = 14.sp,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { }
                .wrapContentWidth(Alignment.CenterHorizontally)
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(
                onClick = { openUrl("https://vk.com/") },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonColors(
                    containerColor = Blue,
                    contentColor = White,
                    disabledContainerColor = Blue,
                    disabledContentColor = White
                ),
                modifier = Modifier.weight(1f).height(48.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.vk_logo),
                    contentDescription = "VK logo"
                )
            }
            Button(
                onClick = { openUrl("https://ok.ru/") },
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .weight(1f)
                    .height(48.dp)
                    .background(OrangeGradient, RoundedCornerShape(50.dp))
            ) {
                Icon(
                    painter = painterResource(R.drawable.ok_logo),
                    contentDescription = "OK logo"
                )
            }
        }
    }
}