package blackjack.domain

class BlackjackResults(
    val dealer: Dealer,
    val participants: List<Participant>,
) {

    val result: Map<BlackjackResult, List<Participant>> = run {
        val dealerScore = dealer.score
        participants.groupBy { participant ->
            val participantScore = participant.score

            when {
                (participantScore <= Const.NUMBER_BLACKJACK && participantScore > dealerScore) || dealerScore > Const.NUMBER_BLACKJACK -> BlackjackResult.WIN
                (dealerScore <= Const.NUMBER_BLACKJACK && participantScore < dealerScore) || participantScore > Const.NUMBER_BLACKJACK -> BlackjackResult.LOSE
                else -> BlackjackResult.DRAW
            }
        }
    }

    enum class BlackjackResult {
        WIN,
        LOSE,
        DRAW,
    }
}
