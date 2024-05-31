package com.example.gradecursos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gradecursos.data.DataSource
import com.example.gradecursos.model.Topic
import com.example.gradecursos.ui.theme.GradeCursosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GradeCursosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    // Preenche o tamanho total da tela e adiciona padding
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding(),
                    // Define a cor de fundo da superfície com base no esquema de cores do tema
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Chama a função TopicGrid com um modificador de padding
                    TopicGrid(
                        modifier = Modifier.padding(
                            // Adiciona padding para as margens esquerda, superior e direita
                            start = dimensionResource(R.dimen.padding_small),
                            top = dimensionResource(R.dimen.padding_small),
                            end = dimensionResource(R.dimen.padding_small),
                        )
                    )
                }
            }
        }
    }
}

// Função que define a grid do tópicos
@Composable
fun TopicGrid(modifier: Modifier = Modifier) {
    // Conteúdo da grid de tópicos
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        // Espaçamento vertical entre os itens da grid
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        // Espaçamento horizontal entre os itens da grid
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        // Adiciona os itens da lista de tópicos na LazyVerticalGrid
        items(DataSource.topics) { topic ->
            // Para cada tópico, cria um TopicCard
            TopicCard(topic)
        }
    }
}
// Função que define o card do tópicos
@Composable
fun TopicCard(topic: Topic, modifier: Modifier = Modifier) {
    Card {
        Row {
            Box {
                // Exibe a imagem do tópico
                Image(
                    painter = painterResource(id = topic.imageRes),
                    contentDescription = null,
                    modifier = modifier
                        .size(width = 68.dp, height = 68.dp)
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop
                )
            }
            // Coluna para conter o texto e ícone do cartão
            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(
                        start = dimensionResource(R.dimen.padding_medium),
                        top = dimensionResource(R.dimen.padding_medium),
                        end = dimensionResource(R.dimen.padding_medium),
                        bottom = dimensionResource(R.dimen.padding_small)
                    )
                )
                // Linha que contem o ícone e o texto do número de cursos
                Row(verticalAlignment = Alignment.CenterVertically) {
                    // Exibe o ícone
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = topic.availableCourses.toString(),
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_small))
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopicPreview() {
    GradeCursosTheme {
        val topic = Topic(R.string.photography, 321, R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopicCard(topic = topic)
        }
    }
}