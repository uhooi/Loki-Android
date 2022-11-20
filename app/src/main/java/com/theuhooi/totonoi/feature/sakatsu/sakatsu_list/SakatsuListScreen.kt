package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.theuhooi.totonoi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakatsuListScreen(viewModel: SakatsuListViewModel = viewModel()) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = stringResource(id = R.string.sakatsu_list_title))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navigationBarsPadding(),
                content = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
                },
                onClick = { /*TODO*/ }
            )
        }
    ) {
        SakatsuListSections()
    }
}