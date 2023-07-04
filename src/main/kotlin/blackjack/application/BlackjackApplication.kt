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
        val playerList = ready()
        play(playerList)
        finish(playerList)
    }

    private fun ready(): List<Player> {
        val playerNameList = inputView.getPlayers()

        val playerList = Player.createPlayers(playerNameList)

        playerList.forEach { player ->
            repeat(2) {
                val card = blackjackShoe.draw()
                player.receiveCard(card)
            }
        }
        outputView.showDefaultDrawToPlayers(playerList)
        return playerList
    }

    private fun play(playerList: List<Player>) {
        playerList.forEach { player ->
            while (player.canReceiveCard && inputView.getMoreCard(player)) {
                val card = blackjackShoe.draw()
                player.receiveCard(card)
                outputView.showPlayerCardList(player)
            }
        }
    }

    private fun finish(playerList: List<Player>) {
        outputView.showPlayersInfo(playerList)
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
