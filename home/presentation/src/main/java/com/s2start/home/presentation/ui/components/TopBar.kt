package com.s2start.home.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.s2start.core.presentation.designsystem.R
import com.s2start.designsystem.urbanistFamily
import com.s2start.home.presentation.model.DropdownItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    onBackButton: (() -> Unit)? = null,
    onSearch: ((Boolean) -> Unit)? = null,
    menuItems: List<DropdownItem>? = null
) {
    val searchSave = remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontFamily = urbanistFamily,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            onSearch?.let {
                IconButton(onClick = {
                    searchSave.value = !searchSave.value
                    onSearch.invoke(searchSave.value)
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            if (!menuItems.isNullOrEmpty()) {
                var expanded by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dot_menu),
                            contentDescription = "More options",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        menuItems.map {
                            val leadingIcon: (@Composable () -> Unit)? = it.icon?.let { iconRes ->
                                {
                                    Icon(
                                        painter = painterResource(id = iconRes),
                                        contentDescription = null
                                    )
                                }
                            }
                            DropdownMenuItem(
                                text = { Text(it.label) },
                                onClick = {
                                    expanded = false
                                    it.onClick.invoke()
                                },
                                leadingIcon = leadingIcon
                            )
                        }
                    }
                }
            }

        },
        navigationIcon = {
            onBackButton?.let {
                IconButton(onClick = {
                    it.invoke()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_prev_small),
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
