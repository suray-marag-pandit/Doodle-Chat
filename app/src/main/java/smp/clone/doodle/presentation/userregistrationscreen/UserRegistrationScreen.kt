package smp.clone.doodle.presentation.userregistrationscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background // Import for background modifier
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer // Import for graphicsLayer
import androidx.compose.ui.layout.ContentScale // Import for ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource // Import for painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// Data class to represent a country
data class Country(
    val name: String,
    val dialCode: String,
    val flagEmoji: String,
    val minLength: Int,
    val maxLength: Int
)

// Sample list of countries (for demonstration)
val sampleCountries = listOf(
    Country("United States", "+1", "🇺🇸", 10, 10),
    Country("Canada", "+1", "🇨🇦", 10, 10),
    Country("United Kingdom", "+44", "🇬🇧", 10, 11),
    Country("India", "+91", "🇮🇳", 10, 10),
    Country("Germany", "+49", "🇩🇪", 9, 11),
    Country("France", "+33", "🇫🇷", 9, 9),
    Country("Australia", "+61", "🇦🇺", 9, 9),
    Country("Brazil", "+55", "🇧🇷", 10, 11),
    Country("Japan", "+81", "🇯🇵", 10, 11),
    Country("China", "+86", "🇨🇳", 11, 11),
    Country("Spain", "+34", "🇪🇸", 9, 9),
    Country("Sweden", "+46", "🇸🇪", 6, 9),
    Country("Pakistan", "+92", "🇵🇰", 7, 10)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserRegistrationScreen(
    onNextClicked: (selectedCountry: Country, phoneNumber: String) -> Unit = { _, _ -> }
) {
    var selectedCountry by remember { mutableStateOf(sampleCountries.first { it.name == "India" }) }
    var phoneNumber by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = R.drawable.bg_register),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer(alpha = 0.3f),
                contentScale = ContentScale.Crop
            )

            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.2f))
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Let's Get Doodling!",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.black),
                    modifier = Modifier.padding(bottom = 40.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 24.dp)
                ) {
                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { expanded = true },
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.outlinedCardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                            contentColor = MaterialTheme.colorScheme.onSurface
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = selectedCountry.flagEmoji, fontSize = 28.sp)
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = selectedCountry.name,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Select Country",
                                tint = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        sampleCountries.forEach { country ->
                            DropdownMenuItem(
                                text = {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = country.flagEmoji, fontSize = 22.sp)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Text(text = "${country.name} (${country.dialCode})")
                                    }
                                },
                                onClick = {
                                    selectedCountry = country
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                OutlinedTextField(
                    value = phoneNumber,
                    onValueChange = { newValue ->
                        val filteredValue = newValue.filter { it.isDigit() }
                        if (filteredValue.length <= selectedCountry.maxLength) {
                            phoneNumber = filteredValue
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(bottom = 40.dp),
                    label = {
                        Text(
                            "Your Phone Number",
                            fontWeight = FontWeight.Medium
                        )
                    },
                    leadingIcon = {
                        Text(
                            text = selectedCountry.dialCode,
                            modifier = Modifier.padding(start = 16.dp, end = 8.dp),
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = colorResource(R.color.black)
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true,
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = colorResource(R.color.black),
                        unfocusedBorderColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        cursorColor = colorResource(R.color.black),
                        focusedLabelColor = colorResource(R.color.black),
                        unfocusedLabelColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                        // --- START OF CHANGES ---
                        // Changed 'containerColor' to 'focusedContainerColor' and 'unfocusedContainerColor'
                        focusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                        unfocusedContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        // --- END OF CHANGES ---
                    )
                )

                Button(
                    onClick = {
                        val currentPhoneNumberLength = phoneNumber.length
                        if (currentPhoneNumberLength >= selectedCountry.minLength &&
                            currentPhoneNumberLength <= selectedCountry.maxLength) {
                            onNextClicked(selectedCountry, phoneNumber)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Oops! Phone number needs to be between ${selectedCountry.minLength} and ${selectedCountry.maxLength} digits for ${selectedCountry.name}. Let's fix that!",
                                    withDismissAction = true,
                                    duration = SnackbarDuration.Long
                                )
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .height(64.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(R.color.orangeboldtheme),
                        contentColor = Color.White
                    ),
                    enabled = phoneNumber.isNotBlank() &&
                            phoneNumber.length >= selectedCountry.minLength &&
                            phoneNumber.length <= selectedCountry.maxLength
                ) {
                    Text(
                        text = "Doodle On!",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Black
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewPhoneNumberLoginScreen() {
    MaterialTheme {
        UserRegistrationScreen()
    }
}
