package blackjack.domain

class BlackjackResult(
    val dealer: Dealer,
    val participants: List<Participant>,
) {

    val result: Map<Match, List<Participant>> = run {
        val dealerScore = dealer.score
        participants.groupBy { participant ->
            val participantScore = participant.score

            when {
                participantScore > dealerScore -> Match.WIN
                participantScore < dealerScore -> Match.LOSE
                else -> Match.DRAW
            }
        }
    }

    enum class Match {
        WIN,
        LOSE,
        DRAW,
    }
}
