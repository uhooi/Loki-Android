package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.components.LogCompositions
import com.theuhooi.totonoi.core.ui.components.LogType

@Composable
fun SakatsuListItem(
    title: String,
    dateText: String,
    modifier: Modifier = Modifier,
    description: String? = null,
    saunaTimeText: String? = null,
    coolBathTimeText: String? = null,
    relaxationTimeText: String? = null
) {
    LogCompositions(tag = LogType.SAKATSU_LIST_ITEM)
    Card(modifier = modifier.padding(vertical = 8.dp)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, start = 16.dp, end = 16.dp),
            text = dateText,
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp),
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            if (saunaTimeText != null) {
                Text(
                    text = stringResource(id = R.string.emoji_sauna) + saunaTimeText,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = stringResource(id = R.string.timeunit_minute),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            if (coolBathTimeText != null) {
                ArrowText(modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    text = stringResource(id = R.string.emoji_cool_bath) + coolBathTimeText,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = stringResource(id = R.string.timeunit_second),
                    style = MaterialTheme.typography.labelMedium
                )
            }
            if (relaxationTimeText != null) {
                ArrowText(modifier = Modifier.padding(horizontal = 4.dp))
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = stringResource(id = R.string.emoji_relaxation) + relaxationTimeText,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    modifier = Modifier.align(Alignment.Bottom),
                    text = stringResource(id = R.string.timeunit_minute),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        }
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun ArrowText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.arrow_right),
        style = MaterialTheme.typography.labelMedium
    )
}