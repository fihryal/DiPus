package com.faqiy.catpark

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.faqiy.catpark.data.Datasource
import com.faqiy.catpark.model.Affirmation
import com.faqiy.catpark.ui.theme.CatParkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatParkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Affirmationsapp()
                }
            }
        }
    }
}

@Composable
fun Affirmationsapp() {
    AffirmationList(affirmationList = Datasource().loadAffirmations())

}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    var expanded by remember { mutableStateOf(false) }
    val color by animateColorAsState(targetValue =  if (!expanded)  MaterialTheme.colorScheme.tertiaryContainer else MaterialTheme.colorScheme.secondaryContainer,label = "inspection label")
    ElevatedCard(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )
    ) {
        Column (
            modifier = Modifier
                .background(color = color)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
//                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(194.dp),
                contentScale = ContentScale.Inside
            )
            Text(
                text = LocalContext.current.getString(affirmation.stringResourceId),
                modifier = Modifier
                    .padding(16.dp)
                    .align(alignment = Alignment.CenterHorizontally),
                style = MaterialTheme.typography.headlineSmall
            )
            DogItemButton(
                expanded = expanded,
                onClick = { expanded = !expanded },
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally)
            )
            if (expanded) {

                DiceHobby(
                    affirmation.Hobies,
                    modifier = Modifier.padding(
                        start = (10.dp),
                        top = (5.dp),
                        end = (10.dp),
                        bottom = (10.dp)
                    )
                )
            }

        }
    }
}



@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
private fun DogItemButton(
    expanded: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ArrowDropDown else Icons.Filled.Clear,
            contentDescription = stringResource(R.string.move),
            tint = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
fun DiceHobby(
    @StringRes diceHobby: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.move),
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = stringResource(diceHobby),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CatParkTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Affirmationsapp()
        }
    }
}