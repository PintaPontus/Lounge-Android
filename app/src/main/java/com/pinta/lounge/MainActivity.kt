package com.pinta.lounge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.pinta.lounge.ui.theme.LoungeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoungeTheme {
                Chat("Android")
            }
        }
    }
}

@Composable
fun Chat(name: String) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (chatInfo, messages, chatCompose) = createRefs()

        ChatInfo(name = name,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(chatInfo) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(messages) {
                    top.linkTo(chatInfo.bottom)
                    bottom.linkTo(chatCompose.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    height = Dimension.fillToConstraints
                },
            reverseLayout = true
        ) {
            // Add 5 items
            items(15) { index ->
                Message("$index")
            }
        }

        ChatCompose(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(chatCompose) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
    }
}

@Composable
fun ChatInfo(name: String, modifier: Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(64.dp)
        .background(Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Text("Chat with $name",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatCompose(modifier: Modifier) {
    Row(modifier = modifier
        .fillMaxWidth()
        .height(64.dp)
        .background(Color.DarkGray),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Box (Modifier.fillMaxHeight()){
            OutlinedTextField(value = "", onValueChange = {}, modifier = Modifier.padding(0.dp))
        }
    }
}

@Composable
fun Message(content: String) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0f,
                    topEnd = 48f,
                    bottomStart = 48f ,
                    bottomEnd =  48f
                )
            )
            .background(Color.DarkGray)
    ) {
        Text(content, color = Color.White, modifier = Modifier.padding(8.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun ChatPreview() {
    LoungeTheme {
        Chat("Android")
    }
}