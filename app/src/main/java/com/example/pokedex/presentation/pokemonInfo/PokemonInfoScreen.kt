package com.example.pokedex.presentation.pokemonInfo

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.pokedex.util.parseStatToAbbr
import com.example.pokedex.util.parseStatToColor
import com.example.pokedex.util.parseTypeToColor

@Composable
fun PokemonInfoScreen(
    viewModel: PokemonInfoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.pokemonInfoState.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = uiState.hasError) {
        if(uiState.hasError) {
            Toast.makeText(
                context,
                "Error: ",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        if (uiState.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        } else {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = uiState.dominantColor)
                    .padding(0.dp, 0.dp, 20.dp),
                verticalAlignment = Alignment.CenterVertically,


            ){
                IconButton(
                    onClick = {
                        onNavigateBack.invoke()
                    }
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "ArrowBack",
                        tint = MaterialTheme.colorScheme.onBackground,
                        modifier = Modifier.size(48.dp)
                    )
                }

                Spacer(modifier = Modifier.size(20.dp))

                Text(
                    text = "Details",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "# ${uiState.info.id.toString().padStart(4, '0')}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    maxLines = 1,
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                    .background(color = uiState.dominantColor)
            ) {
                AsyncImage(
                    model = uiState.info.imageUrl,
                    contentDescription = uiState.info.name,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(250.dp)
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            
            Text(
                text = uiState.info.name.replaceFirstChar {it.uppercase()},
                fontWeight = FontWeight.SemiBold,
                fontSize = 50.sp,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(20.dp))
            
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                for(type in uiState.info.types) {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .size(width = 130.dp, height = 30.dp)
                            .clip(RoundedCornerShape(30.dp))
                            .background(parseTypeToColor(type))
                    ){
                        Text(
                            text = type.name.replaceFirstChar {it.uppercase()},
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 24.sp,
                            color = MaterialTheme.colorScheme.onBackground,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.size(30.dp))

            Row( modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ){
                Text(
                    text = "Weight",
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 200.dp, height = 30.dp)
                        .padding(start = 60.dp, end = 25.dp)
                )
                Text(
                    text = "Height",
                    fontWeight = FontWeight.Normal,
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 200.dp, height = 30.dp)
                        .padding(start = 25.dp, end = 60.dp)
                )
            }

            Row( modifier = Modifier
                .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ){
                Text(
                    text = "${uiState.info.weight.toFloat() / 10} Kg",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 200.dp, height = 40.dp)
                        .padding(start = 60.dp, end = 25.dp)
                )
                Text(
                    text = "${uiState.info.height.toFloat() / 10} M",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 30.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .size(width = 200.dp, height = 40.dp)
                        .padding(start = 25.dp, end = 60.dp)
                )
            }

            Spacer(modifier = Modifier.size(25.dp))

            Text(
                text = "Base Stats",
                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.size(5.dp))

            for (stat in uiState.info.stats){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = parseStatToAbbr(stat),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp,
                        color = MaterialTheme.colorScheme.onBackground,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        modifier = Modifier
                            .size(width = 100.dp, height = 30.dp)
                            .padding(start = 5.dp, end = 10.dp)

                    )
                    CustomLinearProgressIndicator(
                        progress = stat.baseStat.toFloat() / 256,
                        color = parseStatToColor(stat),
                        trackColor = Color.LightGray,
                        modifier = Modifier.fillMaxWidth()
                            .padding(end = 10.dp)
                    )
                }
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
fun CustomLinearProgressIndicator(
    modifier: Modifier = Modifier,
    progress: Float, 
    color: Color = Color.DarkGray,
    trackColor: Color = Color.LightGray,
    clipShape: Shape = RoundedCornerShape(16.dp)
) {
    Box(
        modifier = modifier
            .clip(clipShape)
            .background(trackColor)
            .height(25.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(clipShape)
                .background(color)
                .fillMaxHeight()
                .fillMaxWidth(progress)
        ){
            Text(
                text = "${(progress * 256).toInt()} / 256",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onBackground,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 5.dp)
            )
        }
    }
}