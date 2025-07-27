package smp.clone.doodle.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    data object SplashScreen:Routes()

    @Serializable
    data object WelcomeScreen:Routes()

  @Serializable
    data object UserRegistrationScreen:Routes()

    @Serializable
    data object HomeScreen:Routes()

    @Serializable
    data object ChatScreen:Routes()

    @Serializable
    data object UpdateScreen:Routes()

    @Serializable
    data object CallsScreen:Routes()

    @Serializable
    data object CommunitiesScreen:Routes()


}