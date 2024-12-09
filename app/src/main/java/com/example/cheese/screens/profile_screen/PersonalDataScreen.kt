package com.example.cheese.screens.profile_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cheese.R
import com.example.cheese.ui.theme.DividerColor

@Composable
fun PersonalDataScreen(navController: NavHostController) {

    var secondName by remember { mutableStateOf("") }
    var firstName by remember { mutableStateOf("") }
    var patronymic by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.padding(start = 6.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        navController.navigate("screen_profile") {
                            popUpTo("screen_profile") {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        modifier = Modifier
                            .size(38.dp)
                            .padding(end = 4.dp),
                        tint = Color.Black
                    )
                    Text(
                        text = stringResource(id = R.string.button_back),
                        fontSize = 18.sp,
                        color = Color.Black
                    )
                }
            }

        }

        HorizontalDivider(thickness = 1.dp, color = DividerColor)

        Text(
            text = stringResource(id = R.string.data_heading),
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            modifier = Modifier
                .padding(top = 30.dp, start = 13.dp, bottom = 30.dp),
            fontFamily = FontFamily(Font(R.font.font_sf_pro_bold))
        )

        CustomTextField(
            label = stringResource(id = R.string.data_second_name),
            value = secondName,
            onValueChange = { secondName = it }
        )
        CustomTextField(
            label = stringResource(id = R.string.data_name),
            value = firstName,
            onValueChange = { firstName = it }
        )
        CustomTextField(
            label = stringResource(id = R.string.data_patronymic),
            value = patronymic,
            onValueChange = { patronymic = it }
        )
        CustomTextField(
            label = stringResource(id = R.string.data_date),
            value = birthDate,
            onValueChange = { birthDate = it }

        )
        Text(
            modifier = Modifier.padding(start = 13.dp, top = 4.dp, bottom = 4.dp),
            text = stringResource(id = R.string.data_year_warning),
            color = Color.Gray,
            fontSize = 12.sp
        )
        CustomTextField(
            label = stringResource(id = R.string.data_phone),
            value = phone,
            onValueChange = { phone = it }

        )
        CustomTextField(
            label = stringResource(id = R.string.data_email),
            value = email,
            onValueChange = { email = it }
        )

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val bottomPadding = if (label == stringResource(id = R.string.data_date)) 0.dp else 13.dp
    Column(
        Modifier
            .fillMaxWidth()
            .padding(start = 13.dp, end = 13.dp, bottom = bottomPadding)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFF969696),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(
                fontSize = 18.sp,
                color = Color.Black
            ),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color.Transparent,
                focusedBorderColor = Color.Transparent,
                cursorColor = Color.Black
            )
        )
    }
}
