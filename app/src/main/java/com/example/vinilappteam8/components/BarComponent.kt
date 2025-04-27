@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.vinilappteam8.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.vinilappteam8.R


@ExperimentalMaterial3Api
@Composable
fun ActionButton() {

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
            containerColor = colorResource(R.color.spot_green),
            contentColor = colorResource(R.color.black),
            modifier = Modifier.rotate(rotationAngle)
            ) {
            Icon(
                imageVector = if (isExpanded) Icons.Default.Close else Icons.Default.Add,
                contentDescription = "Add Icon",

            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewFabOption() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        FabOption("Add Album") {}
        FabOption("Add Artist") {}
        FabOption("Add Collection") {}
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