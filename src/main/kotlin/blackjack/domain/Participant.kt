package blackjack.domain

data class Participant(
    override val name: String
) : Player(name = name) {
    override val canReceiveCard: Boolean
        get() {
            return score < 21
        }

    companion object {
        fun createParticipants(participantString: String): List<Participant> {
            return participantString.split(",")
                .map { Participant(it.trim()) }
        }
    }
}
