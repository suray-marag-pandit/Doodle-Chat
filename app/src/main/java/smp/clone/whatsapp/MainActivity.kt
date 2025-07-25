package smp.clone.whatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import smp.clone.whatsapp.presentation.userregistrationscreen.PhoneNumberLoginScreen
import smp.clone.whatsapp.ui.theme.WhatsappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WhatsappTheme {
                PhoneNumberLoginScreen { selectedCountry, phoneNumber -> }
            }
        }
    }
}
