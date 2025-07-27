package smp.clone.doodle.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import smp.clone.doodle.presentation.callscreen.CallsScreen
import smp.clone.doodle.presentation.communitiesscreen.CommunitiesScreen
import smp.clone.doodle.presentation.homescreen.HomeScreen
import smp.clone.doodle.presentation.splashscreen.SplashScreen
import smp.clone.doodle.presentation.welcomescreen.WelcomeScreen
import smp.clone.doodle.presentation.userregistrationscreen.UserRegistrationScreen
@Composable
fun DoodleNavigationSystem(){
    val navController = rememberNavController()
    NavHost(startDestination = Routes.SplashScreen, navController = navController){
        composable<Routes.SplashScreen>{
            SplashScreen(navController)
        }
        composable<Routes.WelcomeScreen>{
            WelcomeScreen(navController)
        }

        composable<Routes.UserRegistrationScreen>{
            UserRegistrationScreen()
        }

        composable<Routes.HomeScreen>{
            HomeScreen(navController)
        }
        composable<Routes.CommunitiesScreen> {
            CommunitiesScreen()
        }
        composable<Routes.CallsScreen> {
            CallsScreen()
        }
        composable<Routes.UpdateScreen> {
//            UpdateStatus()
        }
    }

}