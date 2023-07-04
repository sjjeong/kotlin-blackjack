package blackjack.application.view

import blackjack.Player

class OutputView {
    fun showDefaultDrawToPlayers(playerList: List<Player>) {
        println("${playerList.joinToString { it.name }}에게 2장의 나누었습니다.")
        playerList.forEach {
            showPlayerCardList(it)
        }
    }

    fun showPlayerCardList(it: Player) {
        println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }}")
    }

    fun showPlayersInfo(playerList: List<Player>) {
        playerList.forEach {
            println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }} - 결과: ${it.score}")
        }
    }
}
