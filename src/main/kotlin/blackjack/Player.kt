package blackjack

data class Player(
    val name: String,
) {
    val cardList: MutableList<Card> = mutableListOf()

    val score: Int
        get() {
            var score = cardList.sumOf { it.rank.point }
            if (cardList.any { it.rank == Rank.ACE } && score <= 11) {
                score += 10
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
        fun of(playerString: String): List<Player> {
            return playerString.split(",")
                .map { Player(it.trim()) }
        }
    }

}
