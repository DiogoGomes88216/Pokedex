package com.example.pokedex.presentation.pokemonList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.diogopokemon.R

@Composable
fun PokemonListScreen(
  viewModel: PokemonListViewModel = hiltViewModel(),
  onNavigateToDetails: (String, Int) -> Unit
) {
  //val uiState by viewModel.pokemonListState.collectAsState()

  val pokemonList = viewModel.pokemonPager.collectAsLazyPagingItems()

  Column(
    modifier = Modifier
      .fillMaxSize(),
    verticalArrangement = Arrangement.Center,
  ) {
    if (pokemonList.loadState.refresh == LoadState.Loading) {
      CircularProgressIndicator(
        modifier = Modifier.align(Alignment.CenterHorizontally)
      )
    } else {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally)
          .clip(shape = RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
          .background(Color.Red)
      ) {
        Image(
          painter = painterResource(id = R.drawable.pngwing_com),
          contentDescription = "Pokédex",
          modifier = Modifier.size(75.dp)
        )

        Text(
          text = "Pokédex",
          fontWeight = FontWeight.SemiBold,
          fontSize = 30.sp,
          color = MaterialTheme.colorScheme.onBackground,
          maxLines = 1,
          modifier = Modifier.align(Alignment.CenterVertically)
        )
      }
      LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(12.dp),
      ){
        items(
          count = pokemonList.itemCount,
          key = {
            pokemonList[it]?.name ?: it
          }
        ){item ->
          pokemonList[item]?.let {
            PokemonItem(
              pokemon = it,
              onNavigateToDetails = onNavigateToDetails,
              modifier = Modifier
            )
          }
        }
      }
      if(pokemonList.loadState.append is LoadState.Loading) {
        CircularProgressIndicator(
          modifier = Modifier
            .padding(vertical= 10.dp)
            .align(Alignment.CenterHorizontally)
            .scale(0.5f)
            .weight(1f)
        )
      }
    }
  }
}