package smp.clone.whatsapp.presentation.updatescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import smp.clone.whatsapp.R
import smp.clone.whatsapp.presentation.bottomnavigation.BottomNavigation
import smp.clone.whatsapp.presentation.components.GenericTopAppBar


// Enum to define the status of an update (for the ring effect)
enum class UpdateStatus {
    UNSEEN,
    SEEN,
    NONE // No updates, or for "My Status" when no new updates are available
}

// Data class for an individual update item
data class UpdateItem(
    val id: String,
    val contactName: String,
    val lastUpdateTime: String,
    val imageUrl: Int, // Drawable resource ID for profile picture
    val status: UpdateStatus,
    val numberOfUpdates: Int = 1 // How many updates this contact has
)

// Sample data for updates
val sampleUpdates = listOf(
    UpdateItem("u1", "Friend A", "10 minutes ago", R.drawable.sharukh_khan, UpdateStatus.UNSEEN, 2),
    UpdateItem("u2", "Family Group", "30 minutes ago", R.drawable.tripti_dimri, UpdateStatus.UNSEEN, 1),
    UpdateItem("u3", "Work Colleague", "1 hour ago", R.drawable.rajkummar_rao, UpdateStatus.SEEN, 3),
    UpdateItem("u4", "Bestie", "Today, 9:00 AM", R.drawable.rashmika, UpdateStatus.UNSEEN, 1),
    UpdateItem("u5", "Old Friend", "Yesterday, 5:00 PM", R.drawable.ajay_devgn, UpdateStatus.SEEN, 2),
    UpdateItem("u6", "New Contact", "Yesterday, 10:00 AM", R.drawable.bhuvan_bam, UpdateStatus.SEEN, 1),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatesScreen() {
    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            GenericTopAppBar(
                title = "Updates",
                onCameraClicked = {},
                onSearchClicked = {},
                menuItems = listOf("Status privacy", "Create Channel", "Settings"),
                onMenuItemClicked = { item -> println("Clicked: $item") }
            )
        },
        bottomBar = { BottomNavigation(1) },
        floatingActionButton = {
            Column(horizontalAlignment = Alignment.End) {
                // Secondary FAB for text status
                FloatingActionButton(
                    onClick = { /* TODO: Open text status editor */ },
                    modifier = Modifier.size(48.dp), // Smaller size
                    containerColor = colorResource(R.color.orangeboldtheme), // Lighter background
                    contentColor = colorResource(R.color.white) // Darker icon
                ) {
                    Icon(
                        imageVector = Icons.Default.Create,
                        contentDescription = "New Text Status",
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                // Primary FAB for camera status
                FloatingActionButton(
                    onClick = { /* TODO: Open camera for status */ },
                    modifier = Modifier.size(65.dp), // Standard FAB size
                    containerColor = colorResource(R.color.orangeboldtheme),
                    contentColor = colorResource(R.color.white)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_camera_alt_24), // Camera icon
                        contentDescription = "New Photo/Video Status",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colorResource(R.color.black))
        ) {
            item {
                // My Status Section
                MyStatusItem(
                    profileImageResId = R.drawable.doodlewelcome, // Your profile image
                    hasUnseenUpdates = false, // Set to true if you have updates that haven't been seen by others
                    lastUpdateTime = "Tap to add status"
                ) {
                    /* TODO: Handle tap on My Status to add new update */
                }
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = colorResource(R.color.white))
            }

            item {
                Text(
                    text = "Recent updates",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(R.color.white),
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                )
            }

            items(sampleUpdates.filter { it.status == UpdateStatus.UNSEEN }) { update ->
                UpdateItemRow(update = update) {
                    /* TODO: Handle clicking on an unseen update */
                }
            }

            item {
                // Only show "Viewed updates" if there are any viewed updates
                if (sampleUpdates.any { it.status == UpdateStatus.SEEN }) {
                    Text(
                        text = "Viewed updates",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color =colorResource(R.color.white),
                        modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp), color = colorResource(R.color.white))
                }
            }

            items(sampleUpdates.filter { it.status == UpdateStatus.SEEN }) { update ->
                UpdateItemRow(update = update) {
                    /* TODO: Handle clicking on a seen update */
                }
            }
        }
    }
}

@Composable
fun MyStatusItem(
    profileImageResId: Int,
    hasUnseenUpdates: Boolean,
    lastUpdateTime: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(64.dp) // Larger size for My Status avatar
        ) {
            // Profile Image
            Image(
                painter = painterResource(id = profileImageResId),
                contentDescription = "My Profile Picture",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                contentScale = ContentScale.Crop
            )
            // Plus icon for adding new status (positioned at bottom right)
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.orangeboldtheme))
                    .border(2.dp, MaterialTheme.colorScheme.background, CircleShape) // Border to separate from profile pic
                    .align(Alignment.BottomEnd)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24), // Use a plus icon drawable
                    contentDescription = "Add New Status",
                    tint = Color.White,
                    modifier = Modifier.fillMaxSize(0.8f).align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = "My status",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
            Text(
                text = lastUpdateTime,
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


@Composable
fun UpdateItemRow(update: UpdateItem, onClick: (UpdateItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(update) }
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile Image with Status Ring
        Box(
            modifier = Modifier
                .size(64.dp) // Size for update avatar
                .background(Color.Transparent) // Needed for border to show
                .border(
                    width = when (update.status) {
                        UpdateStatus.UNSEEN -> 3.dp // Thicker border for unseen
                        UpdateStatus.SEEN -> 1.dp // Thinner border for seen
                        UpdateStatus.NONE -> 0.dp
                    },
                    brush = when (update.status) {
                        UpdateStatus.UNSEEN -> Brush.sweepGradient(listOf(colorResource(R.color.orangeboldtheme), colorResource(R.color.yellowboldtheme))) // Gradient for unseen
                        UpdateStatus.SEEN -> SolidColor(Color.Gray.copy(alpha = 0.5f)) // Gray for seen
                        UpdateStatus.NONE -> SolidColor(Color.Transparent) // Transparent for none
                    },
                    shape = CircleShape
                )
                .padding(3.dp) // Padding inside the border so image doesn't touch it
        ) {
            Image(
                painter = painterResource(id = update.imageUrl),
                contentDescription = "Profile picture of ${update.contactName}",
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = update.contactName,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white)
            )
            Text(
                text = update.lastUpdateTime,
                style = MaterialTheme.typography.bodySmall,
                color =colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewUpdatesScreen() {
    MaterialTheme {
        UpdatesScreen()
    }
}
