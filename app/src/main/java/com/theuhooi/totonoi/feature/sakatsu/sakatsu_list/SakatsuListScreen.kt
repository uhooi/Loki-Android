package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.components.LogCompositions
import com.theuhooi.totonoi.core.ui.components.LogType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SakatsuListScreen(viewModel: SakatsuListViewModel = hiltViewModel(), onFabClick: () -> Unit) {
    LogCompositions(tag = LogType.SAKATSU_LIST_SCREEN)
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                modifier = Modifier,
                title = {
                    Text(text = stringResource(id = R.string.sakatsu_list_title))
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                content = {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = stringResource(id = R.string.talkback_add_sakatsu))
                },
                onClick = onFabClick
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(top = contentPadding.calculateTopPadding())) {
            SakatsuListSections(
                modifier = Modifier.fillMaxSize(),
                sakatsuListStatus = uiState.data
            )
        }

    }
}