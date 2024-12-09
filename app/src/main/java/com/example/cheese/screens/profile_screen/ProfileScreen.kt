package com.example.cheese.screens.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cheese.R
import com.example.cheese.ui.theme.DividerColor
import com.example.cheese.ui.theme.IconColor
import com.example.cheese.ui.theme.TextColor

@Composable
fun ProfileScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    val showDeleteDialog = remember { mutableStateOf(false) }
    val showLogoutDialog = remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {
        Text(
            text = stringResource(id = R.string.profile_heading),
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            modifier = Modifier
                .padding(top = 88.dp, start = 32.dp, bottom = 58.dp),
            fontFamily = FontFamily(Font(R.font.font_sf_pro_bold))
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile_no_photo),//Временно TODO
                contentDescription = "Фото профиля",
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            )
            Text(
                text = "Имя Фамилия Отчество",//Временно TODO
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(top = 14.dp),
                fontFamily = FontFamily(Font(R.font.font_sf_pro_regular))
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            val items = listOf(
                stringResource(id = R.string.profile_personal_data),
                stringResource(id = R.string.profile_saved_addresses),
                stringResource(id = R.string.profile_history),
                stringResource(id = R.string.profile_my_tickets),
                stringResource(id = R.string.profile_about)
            )
            items.forEachIndexed { index, title ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            if (index == 0) {
                                navController.navigate("screen_personal_data")
                            } else {
                                // TODO Другие действия при клике
                            }
                        }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().height(66.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val icon = when (index) {
                            0 -> painterResource(id = R.drawable.profile_perm_identity)
                            1 -> painterResource(id = R.drawable.profile_local_shipping)
                            2 -> painterResource(id = R.drawable.profile_history)
                            3 -> painterResource(id = R.drawable.profile_frame)
                            4 -> painterResource(id = R.drawable.profile_info)
                            else -> painterResource(id = R.drawable.profile_info)
                        }
                        Image(
                            painter = icon,
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .padding(start = 8.dp),
                            colorFilter = ColorFilter.tint(IconColor)
                        )
                        Text(
                            text = title,
                            fontSize = 14.sp,
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 16.dp),
                            color = TextColor,
                            fontWeight = FontWeight.W600,
                            fontFamily = FontFamily(Font(R.font.font_inter))
                        )
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(28.dp),
                            tint = IconColor
                        )
                    }
                }
                if (index < items.size - 1) {
                    HorizontalDivider(thickness = 1.dp, color = DividerColor)
                }
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.profile_exit),
                fontSize = 12.sp,
                fontWeight = FontWeight.W600,
                color = Color.Red,
                fontFamily = FontFamily(Font(R.font.font_inter)),
                modifier = Modifier
                    .clickable {

                        showLogoutDialog.value = true
                    }
                    .padding(bottom = 8.dp)
            )

            Text(
                text = stringResource(id = R.string.profile_delete_acc),
                fontSize = 10.sp,
                fontWeight = FontWeight.W600,
                color = Color.Gray,
                fontFamily = FontFamily(Font(R.font.font_inter)),
                modifier = Modifier
                    .clickable {
                        // TODO
                        showDeleteDialog.value = true
                    }
            )
        }
    }


    if (showDeleteDialog.value) {
        ConfirmationDialog(
            title = stringResource(id = R.string.profile_delete_dialog_heading),
            message = stringResource(id = R.string.profile_delete_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_button_text_yes),
            dismissButtonText = stringResource(id = R.string.confirm_button_text_no),
            onConfirm = {
                showDeleteDialog.value = false
                navController.navigate("screen_enter_profile")
            }, // TODO: Добавить логику удаления
            onDismiss = { showDeleteDialog.value = false }
        )
    }


    if (showLogoutDialog.value) {
        ConfirmationDialog(
            title = stringResource(id = R.string.profile_exit_dialog_heading),
            message = stringResource(id = R.string.profile_exit_dialog_text),
            confirmButtonText = stringResource(id = R.string.confirm_button_text_yes),
            dismissButtonText = stringResource(id = R.string.confirm_button_text_no),
            onConfirm = {
                showLogoutDialog.value = false
                navController.navigate("screen_enter_profile")
            }, // TODO: Добавить логику выхода
            onDismiss = { showLogoutDialog.value = false }
        )
    }
}

@Composable
fun ConfirmationDialog(
    title: String,
    message: String,
    confirmButtonText: String,
    dismissButtonText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = message,
                fontSize = 16.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(confirmButtonText, color = Color.Red)
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(dismissButtonText, color = Color(0xFF007AFF))
            }
        },
        containerColor = Color.White
    )
}