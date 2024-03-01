package com.example.dontstarvetogether.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.dontstarvetogether.R
import com.example.dontstarvetogether.Routes
import com.example.dontstarvetogether.model.character.Data
import com.example.dontstarvetogether.model.character.DataItem
import com.example.dontstarvetogether.model.crockpot_recipes.DataRecepies
import com.example.dontstarvetogether.model.crockpot_recipes.Result
import com.example.dontstarvetogether.viewmodel.APIViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScrollScreen(myViewModel: APIViewModel, navController: NavController) {
    val showLoading: Boolean by myViewModel.loading.observeAsState(true)
    val chosen: Boolean by myViewModel.chosen.observeAsState(false)
    val showFood: Boolean by myViewModel.show.observeAsState(false)
    val scaryFontFamily = FontFamily(Font(R.font.deathrattlebb_reg, FontWeight.Bold))
    val searchText by myViewModel.searchText.observeAsState("")
    val showSearchBar: Boolean by myViewModel.isSearchBarVisible.observeAsState(false)
    if (showFood == true && chosen == true) {
        myViewModel.getRecepies()
    } else if (chosen == true) {
        myViewModel.getCharacters()
    }
    if (chosen == false) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )
            Text(text = "Chose what you want to see:")
            Spacer(modifier = Modifier.fillMaxHeight(0.05f))
            Row {
                Button(onClick = {
                    myViewModel.setShow(false)
                    myViewModel.getCharacters()
                    myViewModel.setChosen(true)
                }) {
                    Text(text = "Characters")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(onClick = {
                    myViewModel.setShow(true)
                    myViewModel.getRecepies()
                    myViewModel.setChosen(true)
                }) {
                    Text(text = "Recepies")

                }
            }
        }
    } else {
        Scaffold(
            topBar = {
                MyTopAppBar(myViewModel, navController, showSearchBar, scaryFontFamily)
            },
            bottomBar = {
                MyBottomBar(myViewModel)
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier.padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateTopPadding()
                )
            ) {
                if (showLoading) {
                    Loading()
                } else if (showFood == false) {
                    Characters(
                        myViewModel = myViewModel,
                        navController = navController,
                        serchText = searchText
                    )
                } else {
                    Recepies(myViewModel = myViewModel, navController = navController, searchText)
                }
            }

        }
    }
}

@Composable
fun Loading() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        CircularProgressIndicator(
            color = Color.Gray,
            strokeWidth = 8.dp
        )
    }
}

@Composable
fun Characters(myViewModel: APIViewModel, navController: NavController, serchText: String) {
    val characters: Data by myViewModel.characters.observeAsState(Data())
    Column() {
        LazyColumn {
            if (myViewModel.isSearchBarVisible.value == true) {
                items(characters.filter {
                    it.name.uppercase().contains(serchText.uppercase())
                }) { character ->
                    CharacterItem(character = character) {
                        navController.navigate(
                            Routes.DetailScreenCharacters.createRoute(
                                character.name
                            )
                        )
                    }
                }
            } else {
                items(characters) { character ->
                    CharacterItem(character = character) {
                        navController.navigate(
                            Routes.DetailScreenCharacters.createRoute(
                                character.name
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Recepies(myViewModel: APIViewModel, navController: NavController, serchText: String) {
    val recepies = myViewModel.recepies.observeAsState(DataRecepies(0, listOf(), 0))
    Column() {
        LazyColumn {
            if (myViewModel.isSearchBarVisible.value == true) {
                items(recepies.value.results.filter {
                    it.name.uppercase().contains(serchText.uppercase())
                }) { recepie ->
                    RecepieItem(recepie = recepie) {
                        navController.navigate(
                            Routes.DetailScreenRecepies.createRoute(
                                recepie.name
                            )
                        )
                    }
                }
            } else {
                items(recepies.value.results) { recepie ->
                    RecepieItem(recepie = recepie) {
                        navController.navigate(
                            Routes.DetailScreenRecepies.createRoute(
                                recepie.name
                            )
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    myViewModel: APIViewModel,
    navController: NavController,
    showSearchBar: Boolean,
    scaryFontFamily: FontFamily = FontFamily(Font(R.font.deathrattlebb_reg, FontWeight.Bold))
) {
    val searchText by myViewModel.searchText.observeAsState("")

    TopAppBar(
        title = {
            if (showSearchBar == true) {
                SearchBar(
                    myViewModel = myViewModel
                )
            } else {
                Text(
                    text = "Don't Starve Together",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = scaryFontFamily
                )
            }
        },
        actions = {
            IconButton(onClick = {
                myViewModel.changeSearchBarVisibility()
            }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    myViewModel: APIViewModel
) {
    val searchText by myViewModel.searchText.observeAsState("")
    SearchBar(
        query = searchText,
        onQueryChange = { myViewModel.onSearchTextChange(it) },
        onSearch = { myViewModel.onSearchTextChange(it) },
        active = true,
        placeholder = { Text("What are you looking for?") },
        onActiveChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.13f)
            .clip(RectangleShape)
    ) {
    }
}


@Composable
fun MyBottomBar(myViewModel: APIViewModel) {
    SwitchChanger(myViewModel = myViewModel)
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CharacterItem(character: DataItem, onItemSelected: (String) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemSelected(character.name) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
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

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RecepieItem(recepie: Result, onItemSelected: (String) -> Unit) {
    Card(
        border = BorderStroke(2.dp, Color.LightGray),
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .padding(8.dp)
            .clickable { onItemSelected(recepie.name) }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            GlideImage(
                model = recepie.asset,
                contentDescription = "Recepie Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text = recepie.name,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center, modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun SwitchChanger(myViewModel: APIViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Switch(
            checked = myViewModel.show.value!!,
            onCheckedChange = {
                myViewModel.setShow(it)
            },
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}