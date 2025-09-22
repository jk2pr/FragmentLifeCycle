package com.jk.flcd.ui.main

// import androidx.compose.ui.platform.LocalInspectionMode // Removed as unused
import android.util.TypedValue
import android.view.Gravity
import android.widget.FrameLayout
import android.widget.TextView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.jk.flcd.R

@Composable
fun MainScreenLayout(onFragmentAction: (String) -> Unit, paddingValues: PaddingValues, logText: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.7f)
                    .fillMaxHeight()
            ) {
                    val bgColor =  MaterialTheme.colorScheme.surfaceVariant.toArgb()
                    val textColor = MaterialTheme.colorScheme.onSurfaceVariant.toArgb()
                    AndroidView(
                        factory = { context ->
                            FrameLayout(context).apply {
                                id = R.id.compose_fragment_container
                                setBackgroundColor(bgColor)
                                val noFragmentTextView = TextView(context).apply {
                                    text = context.getText(R.string.no_fragment)
                                    setTextColor(textColor)
                                    gravity = Gravity.CENTER
                                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f) 
                                }
                                addView(noFragmentTextView)
                            }
                        },
                        modifier = Modifier.fillMaxSize()
                    )

            }
            Column(
                modifier = Modifier
                    .weight(0.3f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary, 
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
                Button(onClick = { onFragmentAction("add_frag") }, modifier = Modifier.fillMaxWidth(), colors = buttonColors) { Text("Add") }
                Button(onClick = { onFragmentAction("remove_frag") }, modifier = Modifier.fillMaxWidth(), colors = buttonColors) { Text("Remove") }
                Button(onClick = { onFragmentAction("hide_frag") }, modifier = Modifier.fillMaxWidth(), colors = buttonColors) { Text("Toggle") } 
                Button(onClick = { onFragmentAction("replace_frag") }, modifier = Modifier.fillMaxWidth(), colors = buttonColors) { Text("Replace") }
                Button(onClick = { onFragmentAction("clear_log") }, modifier = Modifier.fillMaxWidth(), colors = buttonColors) { Text("Clear") }
            }
        }
        Spacer(modifier = Modifier.height(16.dp)) 
        LogDisplay(
            logText = logText, 
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
        )
    }
}

@Composable
fun LogDisplay(logText: String, modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    LaunchedEffect(logText) {
        scrollState.animateScrollTo(scrollState.maxValue)
    }
    Box(modifier = modifier
        .fillMaxSize()
        .padding(4.dp)) {
        Text(
            text = logText,
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(8.dp),
            fontSize = 12.sp,
            fontFamily = FontFamily.Monospace,
            color = MaterialTheme.colorScheme.onSurface 
        )
    }
}

@Preview
@Composable
fun MainLayoutPreview(){
    com.jk.flcd.ui.theme.FragmentLifeCycleTheme {
        MainScreenLayout(
            onFragmentAction = {},
            paddingValues = PaddingValues(),
            logText = "Log Text\nMore log text\nAnd even more log text to see scrolling"
        )
    }
}