package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.theuhooi.totonoi.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SakatsuInputScreen(
    onNavigationClick: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.sakatsu_input_title))
                },
                navigationIcon = {
                    IconButton(onClick = onNavigationClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.talkback_back)
                        )
                    }
                }
            )
        }
    ) {

    }
}