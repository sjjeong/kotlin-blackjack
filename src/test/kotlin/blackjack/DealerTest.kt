package blackjack

import blackjack.domain.BlackjackShoe
import blackjack.domain.Dealer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DealerTest {
    private val blackjackShoe = BlackjackShoe()

    @Test
    fun `딜러는 최초에 1장의 카드를 가진다`() {
        val dealer = Dealer.createDealer(blackjackShoe)

        val actual = dealer.cardList.size

        actual shouldBe 1
    }
}
