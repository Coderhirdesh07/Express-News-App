package com.example.newsexpress.presentation.components

import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenuSelector(
    itemList: List<Pair>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val selectedRegion = itemList.find { it.id == selectedItem }?.region ?: ""

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
    ) {
        TextField(
            value = selectedRegion,
            onValueChange = {},
            readOnly = true,
            label = { Text("Select Language") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            modifier = Modifier.menuAnchor(enabled = expanded, type = MenuAnchorType.PrimaryNotEditable),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            itemList.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.region) },
                    onClick = {
                        onItemSelected(item.id)
                        expanded = false
                    },
                )
            }
        }
    }
}

data class Pair(
    val id: String,
    val region: String,
)

fun RegionalList(): List<Pair> {
    val regionalList =
        listOf<Pair>(
            Pair("ae", "United Arab Emirates"),
            Pair("ar", "Argentina"),
            Pair("at", "Austria"),
            Pair("au", "Australia"),
            Pair("be", "Belgium"),
            Pair("bg", "Bulgaria"),
            Pair("br", "Brazil"),
            Pair("ca", "Canada"),
            Pair("ch", "Switzerland"),
            Pair("cn", "China"),
            Pair("co", "Colombia"),
            Pair("cu", "Cuba"),
            Pair("cz", "Czech Republic"),
            Pair("de", "Germany"),
            Pair("eg", "Egypt"),
            Pair("fr", "France"),
            Pair("gb", "United Kingdom"),
            Pair("gr", "Greece"),
            Pair("hk", "Hong Kong"),
            Pair("hu", "Hungary"),
            Pair("id", "Indonesia"),
            Pair("ie", "Ireland"),
            Pair("il", "Israel"),
            Pair("in", "India"),
            Pair("it", "Italy"),
            Pair("jp", "Japan"),
            Pair("kr", "South Korea"),
            Pair("lt", "Lithuania"),
            Pair("lv", "Latvia"),
            Pair("ma", "Morocco"),
            Pair("mx", "Mexico"),
            Pair("my", "Malaysia"),
            Pair("ng", "Nigeria"),
            Pair("nl", "Netherlands"),
            Pair("no", "Norway"),
            Pair("nz", "New Zealand"),
            Pair("ph", "Philippines"),
            Pair("pl", "Poland"),
            Pair("pt", "Portugal"),
            Pair("ro", "Romania"),
            Pair("rs", "Serbia"),
            Pair("ru", "Russia"),
            Pair("sa", "Saudi Arabia"),
            Pair("sg", "Singapore"),
            Pair("se", "Sweden"),
            Pair("si", "Slovenia"),
            Pair("sk", "Slovakia"),
            Pair("th", "Thailand"),
            Pair("tr", "Turkey"),
            Pair("tw", "Taiwan"),
            Pair("ua", "Ukraine"),
            Pair("us", "United States"),
            Pair("ve", "Venezuela"),
            Pair("za", "South Africa"),
        )
    return regionalList
}

fun LanguageList(): List<Pair> {
    val languageList =
        listOf<Pair>(
            Pair("ar", "Arabic"),
            Pair("de", "German"),
            Pair("en", "English"),
            Pair("es", "Spanish"),
            Pair("fr", "French"),
            Pair("he", "Hebrew"),
            Pair("it", "Italian"),
            Pair("nl", "Dutch"),
            Pair("no", "Norwegian"),
            Pair("pt", "Portuguese"),
            Pair("ru", "Russian"),
            Pair("sv", "Swedish"),
            Pair("ud", "Urdu"),
            Pair("zh", "Chinese"),
        )
    return languageList
}
