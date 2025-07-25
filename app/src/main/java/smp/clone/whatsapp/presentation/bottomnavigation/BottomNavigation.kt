package smp.clone.whatsapp.presentation.bottomnavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import smp.clone.whatsapp.R


@SuppressLint("SuspiciousIndentation")
@Composable
fun BottomNavigation(
    selectedItem:Int
){
    // State to keep track of the selected item in the bottom navigation
    var selectedItem by remember { mutableIntStateOf(selectedItem) } // 0 for Chats, 1 for Updates, etc.

    val items = listOf("Chats", "Updates", "Communities", "Calls")
    val icons = listOf(
        R.drawable.outline_chat_bubble_24, // Your drawable for Chats
        R.drawable.outline_donut_large_24,              // Default Material Icon for Updates
        R.drawable.baseline_people_alt_24, // Your drawable for Communities
        R.drawable.outline_call_24        // Default Material Icon for Calls
    )


        BottomAppBar(
            // --- START OF CHANGES ---
            // Increased tonalElevation for a more pronounced floating effect
//            tonalElevation = 16.dp, // Increased elevation for a more "floating" look
            containerColor = colorResource(R.color.black).copy(alpha = 0.9f),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(48.dp)
            ),
            tonalElevation = 32.dp,

//
            // The BottomAppBar itself doesn't have a 'shape' parameter directly for its background,
            // so we use the shadow modifier to apply the rounded corners to its visual representation.
            // If you want the actual background to be rounded and not just the shadow,
            // you might need to wrap it in a Card or a Box with a custom shape.
            // For a typical floating bottom bar, shadow and elevation are key.
            // --- END OF CHANGES ---
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = icons[index]),
                            contentDescription = item
                        )
                    },
                    label = { Text(item) },
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = colorResource(R.color.orangeboldtheme).copy(alpha = 0.9f),
                        selectedTextColor = colorResource(R.color.orangeboldtheme).copy(alpha = 0.9f),
                        unselectedIconColor = colorResource(R.color.white).copy(alpha = 0.6f),
                        unselectedTextColor = colorResource(R.color.white).copy(alpha = 0.6f),
                        indicatorColor = colorResource(R.color.white).copy(alpha = 0.2f)
                    )
                )
            }

    }
}
