package com.theuhooi.totonoi.feature.sakatsu.sakatsu_list

import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.navigator.TopDestination

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLifecycleComposeApi::class)
@Composable
fun SakatsuListScreen(viewModel: SakatsuListViewModel = viewModel(), onFabClick: () -> Unit) {
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
                    Icon(imageVector = Icons.Filled.Add, contentDescription = null)
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