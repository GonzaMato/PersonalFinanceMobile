package com.example.diseomobile.navigation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diseomobile.ui.theme.BodyRegular
import com.example.diseomobile.ui.theme.Primary200
import com.example.diseomobile.ui.theme.PrimaryColor

@Composable
fun BottomBar(
    onNavigate : (String) -> Unit
) {

    val homeItem = IconBarItems(
        icon = Icons.Default.Home,
        title = WiseRipOffScreens.Home.name
    )

    val graphItem = IconBarItems(
        icon = Icons.Default.DateRange,
        title = WiseRipOffScreens.Graphs.name
    )

    val dolarItem = IconBarItems(
        icon = Icons.Default.LocationOn,
        title = WiseRipOffScreens.Currencies.name
    )

    val barItems = listOf(dolarItem, homeItem, graphItem)

    TabView(barItems = barItems, onNavigate = onNavigate)
}

data class IconBarItems(
    val icon : ImageVector,
    val title : String
)


@Composable
fun TabView(barItems: List<IconBarItems>, onNavigate: (String) -> Unit) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(1) }

    NavigationBar {
        barItems.forEachIndexed { index, item ->
            NavigationBarItem(selected = selectedTabIndex == index, onClick = {
                selectedTabIndex = index
                onNavigate(item.title)
            }, icon = {
                IconView(isSelected = selectedTabIndex == index, title = item.title, icon = item.icon)
            },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.Black,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color.Transparent
                ))
        }
    }
}


@Composable
fun IconView(
    isSelected: Boolean,
    title: String,
    icon: ImageVector
) {
    val backgroundColor = if (isSelected) PrimaryColor else Color.Transparent
    val borderColor = if (isSelected) Primary200 else Color.Transparent
    val iconTint = if (isSelected) Color.Black else Color.Gray

    Box(
        modifier = Modifier
            .padding(8.dp)
            .border(
                BorderStroke(2.dp, borderColor),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.width(72.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = iconTint,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                style = BodyRegular.copy(fontSize = 12.sp),
                color = iconTint
            )
        }
    }
}


