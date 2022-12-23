package com.theuhooi.totonoi.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import timber.log.Timber

class Ref(var value: Int)

@Composable
inline fun LogCompositions(tag: LogType) {
    val ref = remember { Ref(0) }
    SideEffect { ref.value++ }
    Timber.d("${tag.name}: Compositions: ${ref.value}")
}

enum class LogType {
    SAKATSU_INPUT_SCREEN,
    SAKATSU_INPUT_SECTIONS,
    FacilityNameSection,
    VISITING_DATE_SECTION,
    SAUNA_SET_SECTION,
    DESCRIPTION_SECTION,
    SAKATSU_LIST_ITEM,
    SAKATSU_LIST_SCREEN,
    SAKATSU_LIST_SECTIONS
}