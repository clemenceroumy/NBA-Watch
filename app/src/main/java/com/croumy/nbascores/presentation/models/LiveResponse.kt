import com.croumy.nbascores.presentation.data.TeamPicture
import com.croumy.nbascores.presentation.helpers.TIME
import com.croumy.nbascores.presentation.helpers.toDate
import com.croumy.nbascores.presentation.models.enums.GameStatus
import java.util.Date

data class TodayGames(
    val scoreboard: ScoreboardResponse
)

data class ScoreboardResponse(
    val gameDate: String,
    val leagueId: String,
    val leagueName: String,
    val games: List<Game>
)

data class Game(
    val gameId: String,
    val gameCode: String,
    val gameStatus: Int, //1 : Not Started; 2 : live; 3 : Finished
    val gameStatusText: String,
    val period: Int,
    val gameClock: String,
    val gameTimeUTC: String, //2023-04-23T17:00:00Z
    val gameEt: String,
    val regulationPeriods: Int,
    val ifNecessary: Boolean,
    val seriesGameNumber: String,
    val seriesText: String,
    val seriesConference: String,
    val poRoundDesc: String,
    val gameSubtype: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val gameLeaders: GameLeaders,
    val pbOdds: PBOdds
) {
    val gameStatusValue: GameStatus get() = when (gameStatus) {
        1 -> GameStatus.NOT_STARTED
        2 -> GameStatus.LIVE
        3 -> GameStatus.FINISHED
        else -> GameStatus.NOT_STARTED
    }

    val gameTime: Date get() = gameTimeUTC.toDate()
    val gameScore: String get() = "${homeTeam.score} - ${awayTeam.score}"
}

data class Team(
    val teamId: Int,
    val teamName: String,
    val teamCity: String,
    val teamTricode: String,
    val wins: Int,
    val losses: Int,
    val score: Int,
    val seed: Int,
    val inBonus: String,
    val timeoutsRemaining: Int,
    val periods: List<Period>
) {
    val logo get() = TeamPicture.valueOf(teamTricode).logo
}

data class Period(
    val period: Int,
    val periodType: String,
    val score: Int
)

data class GameLeaders(
    val homeLeaders: HomeLeaders,
    val awayLeaders: AwayLeaders
)

data class HomeLeaders(
    val personId: Int,
    val name: String,
    val jerseyNum: String,
    val position: String,
    val teamTricode: String,
    val playerSlug: String,
    val points: Int,
    val rebounds: Int,
    val assists: Int
)

data class AwayLeaders(
    val personId: Int,
    val name: String,
    val jerseyNum: String,
    val position: String,
    val teamTricode: String,
    val playerSlug: String,
    val points: Int,
    val rebounds: Int,
    val assists: Int
)

data class PBOdds(
    val team: Any?,
    val odds: Double,
    val suspended: Int
)