package blackjack.domain

data class Participant(
    override val name: String
) : Player(name = name) {
    override val canReceiveCard: Boolean
        get() {
            return score <= CAN_RECEIVE_CARD_SCORE
        }

    companion object {
        private const val CAN_RECEIVE_CARD_SCORE = 21

        fun createParticipants(participantString: String, blackjackShoe: BlackjackShoe): List<Participant> {
            return participantString.split(",")
                .map { name ->
                    Participant(name.trim()).apply {
                        repeat(2) {
                            receiveCard(blackjackShoe.draw())
                        }
                    }
                }
        }
    }
}
