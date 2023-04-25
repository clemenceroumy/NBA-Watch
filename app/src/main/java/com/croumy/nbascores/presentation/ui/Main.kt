import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import coil.compose.AsyncImage
import com.croumy.nbascores.presentation.models.enums.GameStatus
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.ui.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = MainViewModel(),
) {
    ScalingLazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(Dimensions.xsPadding),
        autoCentering = AutoCenteringParams(),
    ) {
        items(viewModel.games.value) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface, RoundedCornerShape(Dimensions.lRadius)),
            ) {
                Row {
                    AsyncImage(
                        model = it.homeTeam.logo,
                        contentDescription = "",
                        modifier = Modifier.size(Dimensions.sIcon)
                    )
                    Spacer(Modifier.width(Dimensions.xsPadding))
                    Text(text = it.homeTeam.teamName)
                }
                Row {
                    AsyncImage(
                        model = it.awayTeam.logo,
                        contentDescription = "",
                        modifier = Modifier.size(Dimensions.sIcon)
                    )
                    Spacer(Modifier.width(Dimensions.xsPadding))
                    Text(text = it.awayTeam.teamName)
                }
                if(it.gameStatusValue == GameStatus.NOT_STARTED) {
                    Text(text = it.gameTimeUTC)
                } else {
                    Text(text = it.gameScore)
                }
            }
        }
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}