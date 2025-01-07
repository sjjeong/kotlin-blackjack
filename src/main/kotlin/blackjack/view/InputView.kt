package blackjack.view

import blackjack.domain.Participant
import blackjack.domain.Participants

class InputView {

    fun getParticipantNames(): List<String> {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        return readlnOrNull().orEmpty()
            .split(",")
    }

    fun getMoreCard(participant: Participant): Boolean {
        println("${participant.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
        return when (readlnOrNull()) {
            "y" -> true
            "n" -> false
            else -> false
        }
    }

    fun requestBettingMoney(participants: Participants) {
        println()
        participants.forEach { participant ->
            println("${participant.name}의 배팅 금액은?")
            val bettingMoney = readlnOrNull()?.toIntOrNull() ?: error("배팅은 ")
            participant.bettingMoney = bettingMoney
        }
    }

}
