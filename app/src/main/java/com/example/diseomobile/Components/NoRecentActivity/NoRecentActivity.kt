package com.example.diseomobile.Components.NoRecentActivity

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diseomobile.R
import com.example.diseomobile.ui.theme.SubtitleRegular
import com.example.diseomobile.ui.theme.currencyImageSize
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.roundedCorners

@Composable
fun NoRecentActivity () {

    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .border(
            width = mediumBorder,
            color = Color.Black, //Esto es aproposito para que en el DarkTheme no se vea raro
            shape = RoundedCornerShape(roundedCorners)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(mediumDP))
            Image(
                painter = painterResource(id = R.drawable.wiselogo),
                contentDescription = null,
                modifier = Modifier
                    .size(currencyImageSize)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(mediumDP))
            Text(text = stringResource(id = R.string.noRecentActivity), style = SubtitleRegular)
            Spacer(modifier = Modifier.height(mediumDP))

        }

    }
}

@Preview
@Composable
fun NoRecentActivityPreview() {
    Box(modifier = Modifier
        .width(250.dp)
        .height(200.dp)
        .background(Color.White)) {
        NoRecentActivity()
    }
}