package com.WorldClock.worldclock

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.WorldClock.worldclock.ui.theme.WorldClockTheme
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Enable WebView debugging (for viewing WebView logs)
        WebView.setWebContentsDebuggingEnabled(true)

        enableEdgeToEdge()  // Optional, to enable edge-to-edge content
        setContent {
            WorldClockTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    // Remember a state to track if the Start button is clicked
    var isButtonClicked by remember { mutableStateOf(false) }

    // Log the state value whenever it changes
    LaunchedEffect(isButtonClicked) {
        Log.d("MainActivity", "isButtonClicked: $isButtonClicked")
    }

    if (isButtonClicked) {
        // Display WebView with the specified URL when the button is clicked
        val webViewState = rememberWebViewState(url = "https://sujanpokharel.info.np/")

        WebView(
            state = webViewState,
            modifier = modifier.fillMaxSize(),
            onCreated = { webView ->
                // Enable JavaScript and other WebView settings
                webView.settings.javaScriptEnabled = true
                webView.settings.domStorageEnabled = true
            }
        )
    } else {
        // Display Welcome Text and Start Button with larger sizes
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Make the welcome text larger
            Text(
                text = "Welcome to World Clock App!",
                style = TextStyle(fontSize = 30.sp)  // Set font size here
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    // Set the state to show the WebView with the specified URL
                    isButtonClicked = true
                },
                modifier = Modifier.padding(16.dp)  // Optional: Add padding around the button
            ) {
                // Make the text inside the button larger
                Text(
                    text = "Start",
                    style = TextStyle(fontSize = 20.sp)  // Set font size for button text
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WorldClockTheme {
        Greeting()
    }
}
