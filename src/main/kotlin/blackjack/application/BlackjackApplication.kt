package blackjack.application

import blackjack.BlackjackShoe
import blackjack.Player
import blackjack.application.view.InputView
import blackjack.application.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val blackjackShoe: BlackjackShoe = BlackjackShoe(),
) {

    fun run() {
        val playerNameList = inputView.getPlayers()

        val playerList = Player.createPlayers(playerNameList)

        playerList.forEach { player ->
            repeat(2) {
                val card = blackjackShoe.draw()
                player.receiveCard(card)
            }
        }
        outputView.showDefaultDrawToPlayers(playerList)

        playerList.forEach { player ->
            while (player.canReceiveCard && inputView.getMoreCard(player)) {
                val card = blackjackShoe.draw()
                player.receiveCard(card)
                outputView.showPlayerCardList(player)
            }
        }

        outputView.showPlayersInfo(playerList)
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
