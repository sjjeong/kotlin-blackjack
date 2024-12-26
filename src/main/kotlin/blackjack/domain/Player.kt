package blackjack.domain

abstract class Player(
    open val name: String
) {
    abstract val canReceiveCard: Boolean

    val cardList: MutableList<Card> = mutableListOf()

    val score: Int
        get() {
            var score = cardList.sumOf { it.rank.point }

            if (cardList.any { it.rank == Rank.ACE } && score <= BLACKJACK_SCORE - ACE_EXTRA_POINT) {
                score += ACE_EXTRA_POINT
            }
            return score
        }

    fun receiveCard(card: Card) {
        cardList.add(card)
    }

    companion object {
        private const val BLACKJACK_SCORE = 21
        private const val ACE_EXTRA_POINT = 10
    }
}
