package com.example.cheese.screens.profile_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cheese.R

@Composable
fun EnterProfileScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text(
            text = stringResource(id = R.string.profile_heading),
            fontWeight = FontWeight.Bold,
            fontSize = 34.sp,
            modifier = Modifier
                .padding(top = 88.dp, start = 32.dp),
            fontFamily = FontFamily(Font(R.font.font_sf_pro_bold))
        )


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .wrapContentSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(80.dp)
            ) {

                Box(
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile_no_photo), // Временно TODO
                        contentDescription = "Фото профиля",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }


                Button(
                    onClick = {navController.navigate("screen_profile"){
                        popUpTo("screen_profile"){
                            inclusive = true                        //Временная очистка стека TODO
                        }
                    } },
                    modifier = Modifier
                        .width(140.dp)
                        .height(52.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFFDB87),
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = stringResource(R.string.profile_login),
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}


