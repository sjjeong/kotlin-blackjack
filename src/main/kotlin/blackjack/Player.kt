package blackjack

data class Player(
    val name: String,
) {
    val cardList: MutableList<Card> = mutableListOf()

    val score: Int
        get() {
            var score = cardList.sumOf { it.rank.point }

            if (cardList.any { it.rank == Rank.ACE } && score <= BLACKJACK_SCORE - ACE_EXTRA_POINT) {
                score += ACE_EXTRA_POINT
            }
            return score
        }

    val canReceiveCard: Boolean
        get() {
            return score < 21
        }

    fun receiveCard(card: Card) {
        cardList.add(card)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_EXTRA_POINT = 10
        fun of(playerString: String): List<Player> {
            return playerString.split(",")
                .map { Player(it.trim()) }
        }
    }

}
