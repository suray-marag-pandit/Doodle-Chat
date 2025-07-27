package smp.clone.doodle.presentation.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import smp.clone.doodle.R
import smp.clone.doodle.presentation.navigation.Routes

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.doodlewelcome),
            contentDescription = "Welcome Doodle Screen",
            modifier = Modifier
                .clip(CircleShape)
                .size(250.dp)
        )
        Text(
            text = "Welcome to Doodle!",
            modifier = Modifier.padding(top = 16.dp),
            fontWeight = FontWeight.Black,
            fontSize = 30.sp,
            color = colorResource(R.color.yellowboldtheme)
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    fontSize = 15.sp
                )) {
                    append("Hey there, future doodler!\nBefore we jump into the fun, take a quick peek at our ")
                }

                pushStringAnnotation(tag = "URL", annotation = "https://yourwebsite.com/privacy")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.purple_200),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp
                    )
                ) {
                    append("Privacy Policy")
                }
                pop()

                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.8f),
                    fontSize = 15.sp
                )) {
                    append(". All set? Just tap 'Agree & Continue' to buzz past our ")
                }

                pushStringAnnotation(tag = "URL", annotation = "https://yourwebsite.com/terms")
                withStyle(
                    style = SpanStyle(
                        color = colorResource(R.color.purple_200),
                        textDecoration = TextDecoration.Underline,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 17.sp
                    )
                ) {
                    append("T&Cs")
                }
                pop()
            },
            modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp),
            style = MaterialTheme.typography.bodyMedium,
        )

        // --- Button Added Here ---
        Button(
            onClick = { navHostController.navigate(Routes.UserRegistrationScreen) },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top = 20.dp, bottom = 16.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.orangeboldtheme),
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Text(
                text = "Accept & Continue",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

    }
}