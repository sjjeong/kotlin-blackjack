package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.Participant

class OutputView {
    fun showReady(dealer: Dealer, participants: List<Participant>) {
        println("\n${dealer.name}와 ${participants.joinToString { it.name }}에게 2장의 나누었습니다.")

        showDealerCard(dealer)
        participants.forEach {
            showParticipantCardList(it)
        }
        println()
    }

    private fun showDealerCard(dealer: Dealer) {
        println("딜러: ${dealer.cardList.joinToString { card -> card.toString() }}")
    }

    fun showParticipantCardList(it: Participant) {
        println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }}")
    }

    fun showParticipantsInfo(participantList: List<Participant>) {
        participantList.forEach {
            println("${it.name}카드: ${it.cardList.joinToString { card -> card.toString() }} - 결과: ${it.score}")
        }
    }
}
