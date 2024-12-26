package blackjack.view

import blackjack.domain.Participant

class OutputView {
    fun showDefaultDrawToParticipants(participantList: List<Participant>) {
        println("${participantList.joinToString { it.name }}에게 2장의 나누었습니다.")
        participantList.forEach {
            showParticipantCardList(it)
        }
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
