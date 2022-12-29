@file:OptIn(ExperimentalMaterial3Api::class)

package com.theuhooi.totonoi.feature.sakatsu.sakatsu_input

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.android.material.datepicker.MaterialDatePicker
import com.theuhooi.totonoi.R
import com.theuhooi.totonoi.core.ui.components.LogCompositions
import com.theuhooi.totonoi.core.ui.components.LogType

@Composable
fun SakatsuInputSections(
    facilityName: String?,
    visitingDateText: String,
    saunaSetList: List<SaunaSetUiState>,
    description: String?,
    onFacilityNameChange: (String) -> Unit,
    onVisitingDateChange: (Long) -> Unit,
    onSaunaTimeChange: (saunaSetIndex: Int, saunaTime: String) -> Unit,
    onCoolBathTimeChange: (saunaSetIndex: Int, coolBathTime: String) -> Unit,
    onRelaxationTimeChange: (saunaSetIndex: Int, relaxationTime: String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onAddSaunaSetClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.SAKATSU_INPUT_SECTIONS)
    val bottomInsets = with(LocalDensity.current) {
        WindowInsets.safeDrawing.getBottom(this).toDp()
    }
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(
            top = 8.dp,
            bottom = 32.dp + bottomInsets,
            start = 16.dp,
            end = 16.dp
        )
    ) {
        item {
            FacilityNameSection(
                modifier = Modifier.fillMaxWidth(),
                facilityName = facilityName,
                onFacilityNameChange = onFacilityNameChange
            )
        }
        item {
            VisitingDateSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                visitingDateText = visitingDateText,
                onVisitingDateChange = onVisitingDateChange
            )
        }
        items(saunaSetList.size) {
            SaunaSetSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                index = it,
                saunaTime = saunaSetList[it].saunaTime,
                coolBathTime = saunaSetList[it].coolBathTime,
                relaxationTime = saunaSetList[it].relaxationTime,
                onSaunaTimeChange = onSaunaTimeChange,
                onCoolBathTimeChange = onCoolBathTimeChange,
                onRelaxationTimeChange = onRelaxationTimeChange
            )
        }
        item {
            Text(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clickable(onClick = onAddSaunaSetClick),
                text = stringResource(id = R.string.sakatsu_input_add_sauna_set),
                color = MaterialTheme.colorScheme.primary
            )
        }
        item {
            DescriptionSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                description = description,
                onDescriptionChange = onDescriptionChange
            )
        }
    }
}

@Composable
private fun FacilityNameSection(
    facilityName: String?,
    onFacilityNameChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.FacilityNameSection)
    OutlinedTextField(
        modifier = modifier,
        value = facilityName.orEmpty(),
        onValueChange = onFacilityNameChange,
        label = {
            Text(text = stringResource(id = R.string.sakatsu_input_facility_name))
        }
    )
}

@Composable
private fun VisitingDateSection(
    visitingDateText: String,
    onVisitingDateChange: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.VISITING_DATE_SECTION)
    val activity = LocalContext.current as AppCompatActivity
    Box(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(4.dp)
            )
            .clip(shape = RoundedCornerShape(4.dp))
            .clickable {
                MaterialDatePicker.Builder
                    .datePicker()
                    .setTheme(R.style.MaterialCalendarTheme)
                    .build()
                    .apply {
                        show(activity.supportFragmentManager, "DatePicker")
                        addOnPositiveButtonClickListener {
                            onVisitingDateChange(it)
                        }
                    }
            }
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 12.dp),
            text = stringResource(id = R.string.sakatsu_input_visiting_date)
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 20.dp),
            text = visitingDateText,
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun SaunaSetSection(
    index: Int,
    saunaTime: String?,
    coolBathTime: String?,
    relaxationTime: String?,
    onSaunaTimeChange: (saunaSetIndex: Int, saunaTime: String) -> Unit,
    onCoolBathTimeChange: (saunaSetIndex: Int, coolBathTime: String) -> Unit,
    onRelaxationTimeChange: (saunaSetIndex: Int, relaxationTime: String) -> Unit,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.SAUNA_SET_SECTION)
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.sakatsu_input_sauna_set_label, index + 1),
            style = MaterialTheme.typography.labelSmall,
        )
        Spacer(modifier = Modifier.height(2.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = saunaTime.orEmpty(),
                onValueChange = { onSaunaTimeChange(index, it) },
                label = {
                    Text(text = stringResource(id = R.string.sakatsu_input_sauna_label))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.option),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = stringResource(id = R.string.timeunit_minute)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = coolBathTime.orEmpty(),
                onValueChange = { onCoolBathTimeChange(index, it) },
                label = {
                    Text(text = stringResource(id = R.string.sakatsu_input_cool_bath_label))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.option),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = stringResource(id = R.string.timeunit_second)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = relaxationTime.orEmpty(),
                onValueChange = { onRelaxationTimeChange(index, it) },
                label = {
                    Text(text = stringResource(id = R.string.sakatsu_input_relaxation_label))
                },
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.option),
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(2.dp))
            Text(
                modifier = Modifier.align(Alignment.Bottom),
                text = stringResource(id = R.string.timeunit_minute)
            )
        }

    }
}

@Composable
private fun DescriptionSection(
    description: String?,
    onDescriptionChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LogCompositions(tag = LogType.DESCRIPTION_SECTION)
    OutlinedTextField(
        modifier = modifier,
        value = description.orEmpty(),
        onValueChange = onDescriptionChange,
        label = {
            Text(
                text = stringResource(id = R.string.sakatsu_input_comment)
            )
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.option),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    )
}