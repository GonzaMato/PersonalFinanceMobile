package com.example.diseomobile.pages.nameSelect

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.diseomobile.Components.Button.ButtonType
import com.example.diseomobile.Components.Button.FilledButton
import com.example.diseomobile.Components.TextField.TextFieldCustom
import com.example.diseomobile.R
import com.example.diseomobile.pages.homePage.ViewModelHomePage
import com.example.diseomobile.ui.theme.SubtitleSemiBold
import com.example.diseomobile.ui.theme.eightyPercentWidth
import com.example.diseomobile.ui.theme.largeDP
import com.example.diseomobile.ui.theme.mediumBorder
import com.example.diseomobile.ui.theme.mediumDP
import com.example.diseomobile.ui.theme.ninetyPercentWidth
import com.example.diseomobile.ui.theme.roundedCorners
import com.example.diseomobile.ui.theme.veryLargeDP

@Composable
fun NameSelect(context : Context, viewmodel : ViewModelHomePage) {
    val name = remember { mutableStateOf("") }
    val nameShouldNotBeEmpty = remember {
        mutableStateOf(false)
    }
    val errorText = stringResource(id = R.string.ErrorNameNotFilled)

    Box(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier =
            Modifier
                .border(
                    mediumBorder,
                    shape = RoundedCornerShape(roundedCorners),
                    color = MaterialTheme.colorScheme.primary
                )
                .fillMaxWidth(ninetyPercentWidth)
                .wrapContentHeight()  // Adjust height based on content
                .padding(largeDP)  // Add padding to give space inside the border
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.createName),
                    style = SubtitleSemiBold
                )

                Spacer(modifier = Modifier.height(mediumDP))

                Box(
                    modifier = Modifier.fillMaxWidth(eightyPercentWidth),
                    contentAlignment = Alignment.Center
                ) {
                    TextFieldCustom(
                        value = name.value,
                        onValueChange = { name.value = it },
                        placeHolder = stringResource(id = R.string.EnterName),
                        error = nameShouldNotBeEmpty.value
                    )
                }

                Spacer(modifier = Modifier.height(mediumDP))

                Box(
                    modifier = Modifier
                        .height(veryLargeDP)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Box(modifier = Modifier.fillMaxWidth(0.6f)) { // Assuming 60% width
                        FilledButton(
                            text = stringResource(id = R.string.accept),
                            type = ButtonType.PRIMARY
                        ) {
                            if (name.value == ""){
                                nameShouldNotBeEmpty.value = true
                                Toast.makeText(context , errorText , Toast.LENGTH_SHORT).show()
                            } else {
                                nameShouldNotBeEmpty.value = false
                                viewmodel.createProfileIfNonExistant(name.value)
                            }
                        }
                    }
                }
            }
        }
    }
}