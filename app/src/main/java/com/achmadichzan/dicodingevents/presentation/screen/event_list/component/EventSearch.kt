package com.achmadichzan.dicodingevents.presentation.screen.event_list.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.achmadichzan.dicodingevents.R

@Composable
fun EventSearch() {
    var expanded by remember { mutableStateOf(false) }
    val events = listOf("Semua", "Mendatang", "Selesai")
    val currentValue = remember { mutableStateOf(events[0]) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = "state.searchText",
            onValueChange = {"onValueChange"},
            placeholder = {
                Text(text = "Cari event")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null
                )
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            textStyle = TextStyle(
                fontSize = 12.sp
            ),
            modifier = Modifier.weight(1f)
                .padding(vertical = 5.dp)
                .height(50.dp)
        )

        IconButton(
            onClick = { expanded = !expanded },
        ) {
            Icon(
                painter = painterResource(R.drawable.menu),
                contentDescription = "More options",
                modifier = Modifier.size(20.dp).offset(x = 10.dp)
            )
        }

        Box(
            contentAlignment = Alignment.CenterEnd
        ) {
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                offset = DpOffset(x = 0.dp, y = 50.dp)
            ) {
                events.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            currentValue.value = it
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}