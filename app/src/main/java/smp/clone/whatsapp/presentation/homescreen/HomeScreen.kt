package smp.clone.whatsapp.presentation.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import smp.clone.whatsapp.R
import smp.clone.whatsapp.presentation.bottomnavigation.BottomNavigation
import smp.clone.whatsapp.presentation.homescreen.components.ChatListModel
import smp.clone.whatsapp.presentation.homescreen.components.ReactionMessageBubble


@OptIn(ExperimentalMaterial3Api::class) // Opt-in for experimental Material 3 APIs like TopAppBar
@Composable
@Preview(showSystemUi = true)
fun HomeScreen() {

    val chatdata = listOf(
        ChatListModel(
            "r1",
            "Reacted 😥 to \"📄 Sticker\"",
            "Rashmina",
            "Yesterday",
            false,
            R.drawable.rashmika // Ensure R.drawable.rashmika exists
        ),
        ChatListModel(
            "c2",
            "Hey, are you free for a call later?",
            "John Doe",
            "10:30 AM",
            false,
            R.drawable.salmankhan // Ensure this drawable exists
        ),
        ChatListModel(
            "c3",
            "Yes, I'll send you the details soon.",
            "Jane Smith",
            "09:15 AM",
            true,
            R.drawable.sharukhkhan // Ensure this drawable exists
        ),
        ChatListModel(
            "r2",
            "Reacted 😂 to your last message",
            "Team Updates",
            "Mon",
            false,
            R.drawable.sharadhakapoor // Ensure this drawable exists
        ),
        ChatListModel(
            "c4",
            "Don't forget the meeting at 2 PM.",
            "Meeting Reminder",
            "1:45 PM",
            false,
            R.drawable.hrithik_roshan // Ensure this drawable exists
        ),
        ChatListModel(
            "c5",
            "Got it, thanks!",
            "You", // Assuming 'You' is the current user
            "1:46 PM",
            true,
            R.drawable.girl // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

        ChatListModel(
            "c6",
            "Check out this new doodle! 🎨",
            "Doodle Group",
            "Wed",
            false,
            R.drawable.girl2 // Ensure this drawable exists
        ),

    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Doodle",
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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                containerColor = colorResource(R.color.orangeboldtheme),
                modifier = Modifier.size(65.dp),
                contentColor = colorResource(R.color.white)
            ) {
                Icon(
                    imageVector = Icons.Default.Create,
                    contentDescription = "Add",
                    modifier = Modifier.size(30.dp)
                )
            }
        },
        bottomBar = { BottomNavigation() }
    ) { paddingValues -> // Add paddingValues to the Scaffold content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(colorResource(R.color.black))
                .padding(paddingValues) // Apply padding from Scaffold
        ) {
            items(chatdata.size) { index ->
                ReactionMessageBubble(chatdesignmodel = chatdata[index])
            }

        }
    }
}
