package blackjack.domain

data class Dealer(
    override val name: String = DEFAULT_DEALER_NAME,
) : Player(name = name) {

    override val canReceiveCard: Boolean
        get() = score <= CAN_RECEIVE_CARD_SCORE

    companion object {
        private const val DEFAULT_DEALER_NAME = "딜러"
        private const val CAN_RECEIVE_CARD_SCORE = 16
    }
}
