package com.theuhooi.totonoi.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import timber.log.Timber

class Ref(var value: Int)

@Composable
inline fun LogCompositions(tag: String) {
    val ref = remember { Ref(0) }
    SideEffect { ref.value++ }
    Timber.d("$tag: Compositions: ${ref.value}")
}