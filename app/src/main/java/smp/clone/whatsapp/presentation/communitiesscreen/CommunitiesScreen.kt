package smp.clone.whatsapp.presentation.communitiesscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import smp.clone.whatsapp.presentation.components.GenericTopAppBar // Import your generic TopAppBar

// Data class for a Community item
data class Community(
    val id: String,
    val name: String,
    val membersCount: Int,
    val iconResId: Int // Drawable resource ID for the community icon (puzzle pieces or similar)
)

// Sample data for communities
val sampleCommunities = listOf(
    Community("c1", "Tech Enthusiasts", 256, R.drawable.community1), // Placeholder drawables
    Community("c2", "Photography Lovers", 128, R.drawable.community2),
    Community("c3", "Travelers United", 512, R.drawable.community3),
    Community("c4", "Doodle Artists", 88, R.drawable.community4),
    Community("c5", "Bookworms Club", 300, R.drawable.community5),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunitiesScreen() {
    Scaffold(
        containerColor = colorResource(R.color.black),
        topBar = {
            GenericTopAppBar(
                title = "Communities",
                onSearchClicked = { /* Handle search for communities */ },
                menuItems = listOf("New community", "Settings"),
                onMenuItemClicked = { item ->
                    println("Communities Screen Menu Item Clicked: $item")
                    // TODO: Implement specific actions for Communities menu items
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(colorResource(R.color.black))
                .padding(horizontal = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // "Start a new community" Button
            Button(
                onClick = { /* TODO: Navigate to create new community screen */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp), // Pill-shaped button
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.orangeboldtheme), // WhatsApp green-like color
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Start a new community",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // "Your Communities" Header
            Text(
                text = "Your Communities",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white),
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // List of Communities
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(sampleCommunities) { community ->
                    CommunityItemRow(community = community) {
                        /* TODO: Handle clicking on a community item */
                    }
                }
            }
        }
    }
}

@Composable
fun CommunityItemRow(community: Community, onClick: (Community) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(community) }
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Community Icon (e.g., puzzle pieces)
        Image(
            painter = painterResource(id = community.iconResId),
            contentDescription = "Icon for ${community.name} community",
            modifier = Modifier
                .size(56.dp) // Size for community icon
                .clip(RoundedCornerShape(12.dp)) // Slightly rounded corners for the icon
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)), // Subtle background
            contentScale = ContentScale.Crop // Or Fit, depending on icon style
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(
                text = community.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "${community.membersCount} members",
                style = MaterialTheme.typography.bodySmall,
                color = colorResource(R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewCommunitiesScreen() {
    MaterialTheme {
        CommunitiesScreen()
    }
}
