import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.wear.compose.material.AutoCenteringParams
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.items
import coil.compose.AsyncImage
import com.croumy.nbascores.R
import com.croumy.nbascores.presentation.helpers.TIME
import com.croumy.nbascores.presentation.helpers.asString
import com.croumy.nbascores.presentation.models.enums.GameStatus
import com.croumy.nbascores.presentation.theme.Dimensions
import com.croumy.nbascores.presentation.theme.red
import com.croumy.nbascores.presentation.ui.MainViewModel
import java.util.Calendar

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
        item {
            Text(Calendar.getInstance().time.asString())
            Spacer(Modifier.height(Dimensions.xsPadding))
        }
        items(viewModel.games.value) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.surface, CircleShape)
                    .padding(horizontal = Dimensions.sPadding)
                    .padding(bottom = Dimensions.sPadding),
            ) {
                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Spacer(Modifier.height(Dimensions.xsPadding))
                    when (it.gameStatusValue) {
                        GameStatus.NOT_STARTED -> Text(
                            text = it.gameTime.asString(TIME),
                            style = MaterialTheme.typography.body2,
                        )
                        GameStatus.LIVE -> {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    Modifier
                                        .size(Dimensions.xxsIcon)
                                        .background(red, CircleShape)
                                )
                                Spacer(Modifier.width(Dimensions.xxsPadding))
                                Text(
                                    text = it.period.toString(),
                                    style = MaterialTheme.typography.body2,
                                )
                            }
                        }

                        GameStatus.FINISHED -> Text(
                            text = stringResource(id = R.string.done),
                            style = MaterialTheme.typography.body2,
                        )
                    }
                    Spacer(Modifier.height(Dimensions.xxsPadding))
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        AsyncImage(
                            model = it.homeTeam.logo,
                            contentDescription = "",
                            modifier = Modifier.size(Dimensions.sIcon)
                        )
                        Spacer(Modifier.width(Dimensions.xxsPadding))
                        Text(text = it.homeTeam.teamName)
                    }
                    if (it.gameStatusValue == GameStatus.LIVE) {
                        Row {
                            Spacer(Modifier.width(Dimensions.xsPadding))
                            Text(text = it.homeTeam.score.toString())
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row {
                        AsyncImage(
                            model = it.awayTeam.logo,
                            contentDescription = "",
                            modifier = Modifier.size(Dimensions.sIcon)
                        )
                        Spacer(Modifier.width(Dimensions.xxsPadding))
                        Text(text = it.awayTeam.teamName)
                    }
                    if (it.gameStatusValue == GameStatus.LIVE) {
                        Row {
                            Spacer(Modifier.width(Dimensions.xsPadding))
                            Text(text = it.homeTeam.score.toString())
                        }
                    }
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