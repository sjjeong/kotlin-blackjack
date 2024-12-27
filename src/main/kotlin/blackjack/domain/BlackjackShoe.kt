package blackjack.domain

class BlackjackShoe(deck: Int = DEFAULT_DECK_OF_CARDS) {
    private val cards: MutableList<Card> = mutableListOf()

    init {
        require(deck in MIN_DECK_OF_CARDS..MAX_DECK_OF_CARDS) {
            "블랙잭 슈에는 최소${MIN_DECK_OF_CARDS}개, 최대${MAX_DECK_OF_CARDS}개의 덱이 들어갈 수 있습니다."
        }
        for (suit in Suit.entries) {
            for (rank in Rank.entries) {
                cards.addAll(List(deck) { Card(suit, rank) })
            }
        }

        cards.shuffle()
    }

    fun draw(): Card {
        return cards.removeFirst()
    }

    companion object {
        private const val DEFAULT_DECK_OF_CARDS = 6
        private const val MIN_DECK_OF_CARDS = 1
        private const val MAX_DECK_OF_CARDS = 8
    }
}
