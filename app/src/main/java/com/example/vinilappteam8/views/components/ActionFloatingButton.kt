package com.example.vinilappteam8.views.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.vinilappteam8.ui.theme.spot_black
import com.example.vinilappteam8.ui.theme.spot_green


@Composable
fun ActionFloatingButton() {

    var isExpanded by remember { mutableStateOf(false) }
    val rotationAngle by animateFloatAsState(
        targetValue = if (isExpanded) 45f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.padding(16.dp)
    ) {
        AnimatedVisibility(
            visible = isExpanded,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                FabOption(text = "Add Album", onClick = { isExpanded = false;println("Add Album") })
                FabOption(
                    text = "Add Artist",
                    onClick = { isExpanded = false;println("Add Artist") })
                FabOption(
                    text = "Add Collection",
                    onClick = { isExpanded = false;println("Add Collection") })
            }
        }


        FloatingActionButton(
            onClick = { isExpanded = !isExpanded },

            shape = MaterialTheme.shapes.small,
            containerColor = spot_green,
            contentColor = spot_black,
            modifier = Modifier.rotate(rotationAngle)
        ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Add Icon",

                )
        }
    }
}

@Composable
fun FabOption(text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable {onClick()}
            .padding(8.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = Modifier.alpha(1f)
        )
        Spacer(modifier = Modifier.size(2.dp))
        Icon(
            imageVector = Icons.Default.AddBox,
            contentDescription = "Add Icon",
            modifier = Modifier.alpha(0.2f)

        )
    }
}