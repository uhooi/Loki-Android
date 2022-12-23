package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.components.LogCompositions
import com.theuhooi.totonoi.core.ui.components.LogType

@Composable
fun SakatsuListSections(
    sakatsuListStatus: SakatsuListStatus,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.SAKATSU_LIST_SECTIONS)
    when (sakatsuListStatus) {
        SakatsuListStatus.Empty -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(
                    text = stringResource(id = R.string.sakatsu_list_empty_label),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        is SakatsuListStatus.Loaded -> {
            val bottomInsets = with(LocalDensity.current) {
                WindowInsets.safeDrawing.getBottom(this).toDp()
            }
            LazyColumn(
                modifier = modifier,
                contentPadding = PaddingValues(
                    top = 8.dp,
                    bottom = 8.dp + bottomInsets,
                    start = 16.dp,
                    end = 16.dp
                ),
            ) {
                items(sakatsuListStatus.sakatsuList) {
                    SakatsuListItem(
                        modifier = Modifier.fillMaxWidth(),
                        title = it.title,
                        description = it.description,
                        dateText = it.visitingDateText,
                        saunaTimeText = it.saunaTimeText,
                        coolBathTimeText = it.coolBathTimeText,
                        relaxationTimeText = it.relaxationTimeText
                    )
                }
            }

        }
        SakatsuListStatus.Loading -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        SakatsuListStatus.Error -> {
            Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = stringResource(id = R.string.error))
            }
        }
    }
}