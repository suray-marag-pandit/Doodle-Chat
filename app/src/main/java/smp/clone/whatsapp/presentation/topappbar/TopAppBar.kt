package smp.clone.whatsapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import smp.clone.whatsapp.R // Ensure R is correctly imported from your project

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenericTopAppBar(
    title: String,
    // Callbacks for actions, now optional if a screen doesn't need them
    onCameraClicked: (() -> Unit)? = null,
    onSearchClicked: (() -> Unit)? = null,
    onMoreOptionsClicked: (() -> Unit)? = null, // Generic callback for any menu item clicked
    onSearchClosed: (() -> Unit)? = null,
    onSearchQueryChanged: ((String) -> Unit)? = null,
    // List of menu item texts for the 3-dot menu
    menuItems: List<String> = emptyList(),
    // Callback when a specific menu item is clicked
    onMenuItemClicked: ((String) -> Unit)? = null
) {
    var isSearchActive by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            if (isSearchActive) {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                        onSearchQueryChanged?.invoke(it) // Invoke if not null
                    },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search...", color = Color.White.copy(alpha = 0.7f)) },
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        cursorColor = Color.White
                    )
                )
            } else {
                Text(
                    text = title,
                    color = colorResource(R.color.orangeboldtheme),
                    fontWeight = FontWeight.Bold
                )
            }
        },
        actions = {
            if (isSearchActive) {
                IconButton(onClick = {
                    isSearchActive = false
                    searchQuery = ""
                    onSearchClosed?.invoke() // Invoke if not null
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close search",
                        tint = colorResource(R.color.white)
                    )
                }
            } else {
                // Camera Icon (only show if onCameraClicked is provided)
                onCameraClicked?.let {
                    IconButton(onClick = it) {
                        Icon(
                            painter = painterResource(R.drawable.baseline_camera_alt_24), // Using your drawable
                            contentDescription = "Camera",
                            tint = colorResource(R.color.white)
                        )
                    }
                }

                // Search Icon (only show if onSearchClicked is provided)
                onSearchClicked?.let {
                    IconButton(onClick = {
                        isSearchActive = true
                        it.invoke() // Notify parent that search is activated
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = colorResource(R.color.white)
                        )
                    }
                }

                // More Options Menu (only show if menuItems are provided)
                if (menuItems.isNotEmpty()) {
                    Box {
                        IconButton(onClick = { showMenu = true }) {
                            Icon(
                                imageVector = Icons.Default.MoreVert,
                                contentDescription = "More options",
                                tint = colorResource(R.color.white)
                            )
                        }
                        DropdownMenu(
                            expanded = showMenu,
                            onDismissRequest = { showMenu = false }
                        ) {
                            menuItems.forEach { itemText ->
                                DropdownMenuItem(
                                    text = { Text(itemText) },
                                    onClick = {
                                        showMenu = false
                                        onMenuItemClicked?.invoke(itemText) // Pass clicked item text
                                    }
                                )
                            }
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = colorResource(R.color.black)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewGenericTopAppBar() {
    // Preview for Chats/Home screen
    GenericTopAppBar(
        title = "Doodle",
        onCameraClicked = {},
        onSearchClicked = {},
        menuItems = listOf("New group", "Linked devices", "Settings"),
        onMenuItemClicked = { item -> println("Clicked: $item") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewUpdatesTopAppBar() {
    // Preview for Updates screen
    GenericTopAppBar(
        title = "Updates",
        onCameraClicked = {},
        onSearchClicked = {},
        menuItems = listOf("Status privacy", "Create Channel", "Settings"),
        onMenuItemClicked = { item -> println("Clicked: $item") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCallsTopAppBar() {
    // Preview for Calls screen
    GenericTopAppBar(
        title = "Calls",
        onSearchClicked = {}, // Calls might not have a camera icon directly
        menuItems = listOf("Clear call log", "Settings"),
        onMenuItemClicked = { item -> println("Clicked: $item") }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewCommunitiesTopAppBar() {
    // Preview for Communities screen
    GenericTopAppBar(
        title = "Communities",
        onSearchClicked = {},
        menuItems = listOf("New community", "Settings"),
        onMenuItemClicked = { item -> println("Clicked: $item") }
    )
}
