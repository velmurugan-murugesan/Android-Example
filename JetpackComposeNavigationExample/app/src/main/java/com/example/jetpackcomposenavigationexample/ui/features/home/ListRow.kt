package com.example.jetpackcomposenavigationexample.ui.features.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposenavigationexample.R

sealed class TrailingContentType {
    object DropDown : TrailingContentType()
    data class Checkbox(val isChecked: Boolean) : TrailingContentType()
    data class Radio(val isSelected: Boolean) : TrailingContentType()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListRow(
    modifier: Modifier = Modifier, title: String,
    subTitle: String, trailingContentType: TrailingContentType,
    onAction:(Boolean?) -> Unit = {}
) {

    var state: Boolean? by remember {
        mutableStateOf(null)
    }

    when(trailingContentType) {
        is TrailingContentType.Checkbox -> {
            state = trailingContentType.isChecked
        }

        is TrailingContentType.Radio -> {
            state = trailingContentType.isSelected
        }

        else -> {

        }
    }

    ListItem(
        headlineText = {
            Text(text = title)
        },
        trailingContent = {
            // ListRowTrailingContent(text = subTitle, trailingContentType = trailingContentType, actionValue)

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = subTitle)
                when (trailingContentType) {
                    is TrailingContentType.DropDown -> {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "dropdown"
                        )
                    }

                    is TrailingContentType.Checkbox -> {

                        Checkbox(checked = state ?: false, onCheckedChange = {
                            state = it
                            onAction.invoke(it)
                        })
                    }

                    is TrailingContentType.Radio -> {
                        RadioButton(selected = state ?: false, onClick = {
                            state = state?.not()
                            onAction.invoke(state)
                        })
                    }
                }
            }
        },
        modifier = modifier.clickable {
            state = state?.not()
            onAction.invoke(state)
        }
    )
}

@Composable
fun ListRowTrailingContent(
    text: String,
    trailingContentType: TrailingContentType,
    actionValue: MutableState<Any?>
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = text)
        when (trailingContentType) {
            is TrailingContentType.DropDown -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = "dropdown"
                )
            }

            is TrailingContentType.Checkbox -> {
                actionValue.value = trailingContentType.isChecked
                var isChecked by remember {
                    mutableStateOf(trailingContentType.isChecked)
                }
                Checkbox(checked = isChecked, onCheckedChange = {
                    //  actionValue.value = it
                    isChecked = it
                })
            }

            is TrailingContentType.Radio -> {
                var isSelected by remember {
                    mutableStateOf(trailingContentType.isSelected)
                }
                actionValue.value = trailingContentType.isSelected
                RadioButton(selected = isSelected, onClick = {
                    isSelected = !isSelected
                    //   actionValue.value = !(actionValue.value as Boolean)
                })
            }
        }
    }
}

@Preview
@Composable
fun ListRowPreview() {
    Column {
        ListRow(
            title = "Main Title",
            subTitle = "Sub Title",
            trailingContentType = TrailingContentType.Checkbox(true),
            onAction = {
                Log.d("Checkbox : Result ", (it ?: false).toString())
            }
        )

        ListRow(
            title = "Main Title",
            subTitle = "Sub Title",
            trailingContentType = TrailingContentType.DropDown,
            onAction = {
                Log.d("DropDown : Result ", "DropDown")
            }
        )

        ListRow(
            title = "Main Title",
            subTitle = "Sub Title",
            trailingContentType = TrailingContentType.Radio(false),
            onAction = {
                Log.d("Radio : Result ", (it ?: false).toString())
            }
        )
    }

}