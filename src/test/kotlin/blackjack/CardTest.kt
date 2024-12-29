package blackjack

import blackjack.domain.Card
import blackjack.domain.Rank
import blackjack.domain.Suit
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

fun createCard(cardString: String): Card {
    val suit = Suit.of(cardString)
    val rank = Rank.of(cardString)
    return Card(suit, rank)
}

class CardTest {
    @Test
    internal fun `카드 이름이 객체로 잘 전환되는지 확인`() {
        val actual = createCard("A스페이드")
        val expected = Card(Suit.SPADE, Rank.ACE)
        actual shouldBe expected
    }
}
