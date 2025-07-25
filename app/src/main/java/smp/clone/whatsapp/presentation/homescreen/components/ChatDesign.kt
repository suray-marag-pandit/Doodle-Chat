package smp.clone.whatsapp.presentation.homescreen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import smp.clone.whatsapp.R // Ensure R is correctly imported from your project


@Composable
fun ReactionMessageBubble(chatdesignmodel: ChatListModel) {
    // Use a placeholder image if senderImageResId is null
    val senderImageResId = chatdesignmodel.senderImageResId ?: R.drawable.doodle_logo // Ensure you have a profile_placeholder drawable

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 16.dp), // Adjusted padding for a tighter look
        horizontalArrangement = Arrangement.Start, // Al ways left-aligned
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Sender Image (Rounded)
        Image(
            painter = painterResource(id = senderImageResId),
            contentDescription = "Profile picture of ${chatdesignmodel.senderId}",
            modifier = Modifier
                .size(36.dp) // Slightly smaller avatar
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.1f)), // Subtle background
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(12.dp)) // Space between image and text bubble

        // Reaction Text and Time Bubble
        // This Column acts as the dark background bubble
        Column(
            modifier = Modifier
                .weight(1f) // Takes available width
                .background(
                    color = Color(0xFF3C3C3C), // A darker grey for the bubble background
                    shape = RoundedCornerShape(10.dp) // Rounded corners for the bubble
                )
                .padding(horizontal = 12.dp, vertical = 8.dp) // Padding inside the bubble
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Sender Name
                Text(
                    text = chatdesignmodel.senderId,
                    color = Color.White.copy(alpha = 0.9f), // Slightly brighter white for name
                    fontWeight = FontWeight.SemiBold, // Semi-bold for name
                    fontSize = 13.sp, // Smaller font for name
                    modifier = Modifier.weight(1f) // Allow name to take space
                )
                // Time
                Text(
                    text = chatdesignmodel.time,
                    color = Color.White.copy(alpha = 0.5f), // Subtler white for time
                    fontSize = 10.sp, // Small font for time
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(4.dp)) // Space between name/time and message text
            // Reaction Message Text
            Text(
                text = chatdesignmodel.text,
                color = Color.White, // Pure white for message text
                fontSize = 15.sp, // Standard font size for message
                fontWeight = FontWeight.Normal,
                lineHeight = 18.sp // Adjust line height for better readability
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewReactionMessageBubble() {
    MaterialTheme {
        Column(modifier = Modifier.background(Color(0xFF202020)).padding(16.dp)) { // Simulate a dark chat background
            ReactionMessageBubble(
                chatdesignmodel = ChatListModel(
                    id = "r1",
                    text = "Reacted 😥 to \"📄 Sticker\"",
                    senderId = "Triyan Bhat Dcrust CSE",
                    time = "Yesterday",
                    isSentByMe = false,
                    senderImageResId = R.drawable.doodlewelcome // Make sure this drawable exists
                )
            )
        }
    }
}
