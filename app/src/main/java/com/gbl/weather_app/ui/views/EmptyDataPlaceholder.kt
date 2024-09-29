package com.gbl.weather_app.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.gbl.weather_app.R

@Composable
fun EmptyDataPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.lets_search),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
private fun PreviewEmptyDataPlaceHolder() {
    EmptyDataPlaceholder()
}