package blackjack.domain

data class Card(
    val suit: Suit,
    val rank: Rank
) {
    override fun toString(): String {
        return "${rank.nickname}${suit.korName}"
    }
}
