package smp.clone.whatsapp.presentation.callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import smp.clone.whatsapp.R
import smp.clone.whatsapp.presentation.components.GenericTopAppBar

// Data class for a Favorite Contact
data class FavoriteContact(
    val id: String,
    val name: String,
    val profileImageResId: Int
)

// Data class for a Call Item
data class CallItem(
    val id: String,
    val contactName: String,
    val callTime: String,
    val profileImageResId: Int,
    val isIncoming: Boolean, // true for incoming, false for outgoing
    val isMissed: Boolean = false // true if missed call
)

// Sample data for Favorites
val sampleFavorites = listOf(
    FavoriteContact("f1", "MrBeast", R.drawable.mrbeast),
    FavoriteContact("f2", "Bhuvan Bam", R.drawable.bhuvan_bam),
    FavoriteContact("f3", "Tripti Dimri", R.drawable.tripti_dimri),
    FavoriteContact("f4", "Akshay Kumar", R.drawable.akshay_kumar),
    FavoriteContact("f5", "Virat Kohli", R.drawable.rajkummar_rao),
    FavoriteContact("f6", "Anushka Sharma", R.drawable.kartik_aaryan)
)

// Sample data for Recent Calls
val sampleRecentCalls = listOf(
    CallItem(
        "rc1",
        "Salman Khan",
        "Yesterday, 8:30 PM",
        R.drawable.salmankhan,
        isIncoming = true,
        false
    ),
    CallItem(
        "rc2",
        "Shah Rukh Khan",
        "Today, 10:15 AM",
        R.drawable.sharukh_khan,
        isIncoming = false,
        false
    ),
    CallItem(
        "rc3",
        "Ashish Chanchlani",
        "Today, 2:00 PM",
        R.drawable.carryminati,
        isIncoming = true,
        true
    ), // Missed call
    CallItem(
        "rc4",
        "Hrithik Roshan",
        "Nov, 8:30 PM",
        R.drawable.hrithik_roshan,
        isIncoming = false,
        false
    ),
    CallItem(
        "rc5",
        "Akshay Kumar",
        "Oct, 10:15 AM",
        R.drawable.akshay_kumar,
        isIncoming = true,
        false
    ),
    CallItem(
        "rc6",
        "Virat Kohli",
        "Oct, 10:15 AM",
        R.drawable.rajkummar_rao,
        isIncoming = false,
        false
    ),
    CallItem(
        "rc7",
        "Anushka Sharma",
        "Oct, 10:15 AM",
        R.drawable.kartik_aaryan,
        isIncoming = true,
        true
    ), // Missed call
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CallsScreen() {
    Scaffold(
        // --- START OF CHANGES ---
        containerColor = colorResource(R.color.black), // Scaffold background to black
        // --- END OF CHANGES ---
        topBar = {
            GenericTopAppBar(
                title = "Calls",
                onSearchClicked = { /* Handle search for calls */ },
                menuItems = listOf("Clear call log", "Settings"),
                onMenuItemClicked = { item ->
                    println("Calls Screen Menu Item Clicked: $item")
                    // TODO: Implement specific actions for Calls menu items
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Start a new call */ },
                // --- START OF CHANGES ---
                containerColor = colorResource(R.color.orangeboldtheme), // FAB background to orangeboldtheme
                // --- END OF CHANGES ---
                modifier = Modifier.size(65.dp),
                // --- START OF CHANGES ---
                contentColor = colorResource(R.color.white) // FAB icon/content to white
                // --- END OF CHANGES ---
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New Call",
                    modifier = Modifier.size(30.dp)
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                // --- START OF CHANGES ---
                .background(colorResource(R.color.black)) // Column background to black
                // --- END OF CHANGES ---
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // "Start a new call" Button
            Button(
                onClick = { /* TODO: Navigate to start a new call screen */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp), // Pill-shaped button
                colors = ButtonDefaults.buttonColors(
                    // --- START OF CHANGES ---
                    containerColor = colorResource(R.color.orangeboldtheme), // Button background to orangeboldtheme
                    contentColor = colorResource(R.color.white) // Button text color to white
                    // --- END OF CHANGES ---
                )
            ) {
                Text(
                    text = "Start a new call",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Favorites Section
            Text(
                text = "Favorites",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                // --- START OF CHANGES ---
                color = colorResource(R.color.white), // Favorites header text to white
                // --- END OF CHANGES ---
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyRow( // Horizontal scrolling for favorites
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp) // Spacing between items
            ) {
                items(sampleFavorites) { contact ->
                    FavoriteContactItem(contact = contact) {
                        /* TODO: Handle clicking on a favorite contact */
                    }
                }
            }

            // "Recent Calls" Header
            Text(
                text = "Recent Calls", // Changed header text
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                // --- START OF CHANGES ---
                color = colorResource(R.color.white), // Recent Calls header text to white
                // --- END OF CHANGES ---
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // List of Recent Calls
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleRecentCalls) { call ->
                    CallItemRow(call = call) {
                        /* TODO: Handle clicking on a call item */
                    }
                }
            }
        }
    }
}

@Composable
fun FavoriteContactItem(contact: FavoriteContact, onClick: (FavoriteContact) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick(contact) }
            .width(72.dp), // Fixed width for each favorite item
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = contact.profileImageResId),
            contentDescription = "Profile picture of ${contact.name}",
            modifier = Modifier
                .size(64.dp) // Size for favorite avatar
                .clip(CircleShape)
                // --- START OF CHANGES ---
                .background(colorResource(R.color.white).copy(alpha = 0.1f)), // Subtle background for image
            // --- END OF CHANGES ---
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = contact.name,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            // --- START OF CHANGES ---
            color = colorResource(R.color.white), // Favorite contact name to white
            // --- END OF CHANGES ---
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun CallItemRow(call: CallItem, onClick: (CallItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(call) }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image
        Image(
            painter = painterResource(id = call.profileImageResId),
            contentDescription = "Profile picture of ${call.contactName}",
            modifier = Modifier
                .size(56.dp) // Size for call avatar
                .clip(CircleShape)
                // --- START OF CHANGES ---
                .background(colorResource(R.color.white).copy(alpha = 0.1f)), // Subtle background for image
            // --- END OF CHANGES ---
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = call.contactName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                // --- START OF CHANGES ---
                color = if (call.isMissed) Color.Red else colorResource(R.color.white), // Call contact name to white (red for missed)
                // --- END OF CHANGES ---
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Call direction/missed icon (you might need specific drawables for these)
                // For simplicity, using a generic icon and color for missed calls
                if (call.isMissed) {
                    Icon(
                        painter = painterResource(id = R.drawable.outline_phone_missed_24), // Placeholder for missed call icon
                        contentDescription = "Missed Call",
                        tint = Color.Red,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                } else {
                    // You could add icons for incoming/outgoing calls here if desired
                    // For example: Icons.Default.ArrowUpward for outgoing, Icons.Default.ArrowDownward for incoming
                }
                Text(
                    text = call.callTime,
                    style = MaterialTheme.typography.bodySmall,
                    // --- START OF CHANGES ---
                    color = if (call.isMissed) Color.Red.copy(alpha = 0.8f) else colorResource(R.color.white).copy(alpha = 0.7f), // Call time to white (red for missed)
                    // --- END OF CHANGES ---
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }

        // Call Icon on the right
        IconButton(onClick = { /* TODO: Initiate call to this contact */ }) {
            Icon(
                imageVector = Icons.Default.Call,
                contentDescription = "Call ${call.contactName}",
                // --- START OF CHANGES ---
                tint = colorResource(R.color.orangeboldtheme) // Call icon tint to orangeboldtheme
                // --- END OF CHANGES ---
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCallsScreen() {
    MaterialTheme {
        CallsScreen()
    }
}
