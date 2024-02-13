package com.example.dontstarvetogether

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.model.Backstory
import com.example.dontstarvetogether.model.Data
import com.example.dontstarvetogether.model.DataItem
import com.example.dontstarvetogether.model.Stats
import com.example.dontstarvetogether.ui.theme.DontStarveTogetherTheme
import com.example.dontstarvetogether.viewmodel.APIViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myViewModel by viewModels<APIViewModel>()
        setContent {
            DontStarveTogetherTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyRecyclerView(myViewModel)
                }
            }
        }
    }
}

@Composable
fun MyRecyclerView(myViewModel: APIViewModel) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val defaultBackstory = Backstory(
        body = "",
        title = ""
    )
    val defaultStats = Stats(
        health = 0,
        hunger = 0,
        sanity = 0
    )
    val defaultDataItem = DataItem(
        animatedShort = "",
        backstory = defaultBackstory,
        bigportrait = "",
        birthdate = "",
        description = "",
        entersTheConstantWith = listOf(),
        favoriteFood = "",
        name = "",
        nickname = "",
        perks = listOf(),
        portrait = "",
        quote = "",
        stats = defaultStats
    )
    val characters: Data by myViewModel.characters.observeAsState(Data())
    myViewModel.getCharacters()
    if(showLoading){
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary
        )
    }
    else{
        LazyColumn {
            items(characters) { character ->
                CharacterItem(character = character)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: DataItem) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(8.dp)
    ) {
        Row(modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()) {
            GlideImage(
                model = character.bigportrait,
                contentDescription = "Character Portrait",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = character.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize()
            )
        }
    }
}