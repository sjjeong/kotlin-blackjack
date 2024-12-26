package blackjack.domain

data class Dealer(
    override val name: String = DEFAULT_DEALER_NAME,
) : Player(name = name) {

    override val canReceiveCard: Boolean
        get() = score < 17

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"

        fun createDealer(blackjackShoe: BlackjackShoe): Dealer {
            return Dealer().apply {
                receiveCard(blackjackShoe.draw())
            }
        }
    }
}
