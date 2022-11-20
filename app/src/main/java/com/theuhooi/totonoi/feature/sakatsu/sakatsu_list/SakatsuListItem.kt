package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SakatsuListItem(
    title: String,
    modifier: Modifier = Modifier
) {

    Card(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
    }

}