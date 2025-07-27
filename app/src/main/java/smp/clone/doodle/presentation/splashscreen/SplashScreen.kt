package smp.clone.doodle.presentation.splashscreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import smp.clone.doodle.R
import smp.clone.doodle.presentation.navigation.Routes

@Composable
fun SplashScreen(
    navHostController: NavHostController
){
    LaunchedEffect(key1 = Unit) {
        delay(1000)
        navHostController.navigate(Routes.WelcomeScreen){
            popUpTo(Routes.SplashScreen){ inclusive = true }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.doodle_logo),
            contentDescription = "Splash Screen",
            modifier = Modifier.size(200.dp).align(
                alignment = Alignment.Center
            )
        )

        Column(
            modifier = Modifier.align(alignment = Alignment.BottomCenter)
                .padding(38.dp)
        ) {
            Text(
                text = "From",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp).align(alignment = Alignment.CenterHorizontally)
            )
            Row(
                modifier = Modifier.padding(bottom = 16.dp)
            ){
                Icon(
                    painter = painterResource(R.drawable.notserious),
                    contentDescription = "Not Serious",
                    modifier = Modifier.size(34.dp)
                )
                Text(
                    text = "Not Serious",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 8.dp).align(
                        Alignment.CenterVertically
                    )
                )
            }
        }

    }

}