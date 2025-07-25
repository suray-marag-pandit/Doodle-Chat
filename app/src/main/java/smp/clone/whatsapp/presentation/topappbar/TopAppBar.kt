package smp.clone.whatsapp.presentation.topappbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import smp.clone.whatsapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    text :String,
){
    TopAppBar(
        title = {
            Text(
                text = text,
                fontSize = 28.sp,
                color = colorResource(R.color.orangeboldtheme) // App name in white
            )
        },
        actions = {
            // Camera Icon
            IconButton(onClick = { /* TODO: Handle camera click */ }) {
                Icon(
                    painter = painterResource(R.drawable.baseline_camera_alt_24), // Corrected: Using Camera icon for broader compatibility
                    contentDescription = "Camera",
                    tint = colorResource(R.color.white)
                    // Icon in white
                )
            }
            // Search Icon
            IconButton(onClick = { /* TODO: Handle search click */ }) {
                Icon(
                    imageVector = Icons.Default.Search, // Search icon
                    contentDescription = "Search",
                    tint = colorResource(R.color.white) // Icon in white
                )
            }
            // 3-dot Menu Icon
            IconButton(onClick = { /* TODO: Handle menu click */ }) {
                Icon(
                    imageVector = Icons.Default.MoreVert, // 3-dot menu icon
                    contentDescription = "More options",
                    tint = colorResource(R.color.white) // Icon in white
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.black) // Background color of the top app bar
        )
    )
}