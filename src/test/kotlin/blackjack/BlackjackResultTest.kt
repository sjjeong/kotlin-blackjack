package blackjack

import blackjack.domain.BlackjackResult
import blackjack.domain.Card
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Rank
import blackjack.domain.Suit
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackResultTest {
    @Test
    fun `딜러가 참가자보다 점수가 높으면 참가자가 패배`() {
        val blackjackResult = BlackjackResult(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.NINE))
                }
            )
        )

        val actual = blackjackResult.result[BlackjackResult.Match.LOSE]?.size
        actual.shouldNotBeNull()
        actual shouldBe 1
    }

    @Test
    fun `딜러가 참가자보다 점수가 낮으면 참가자가 승리`() {
        val blackjackResult = BlackjackResult(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.NINE))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                }
            )
        )

        val actual = blackjackResult.result[BlackjackResult.Match.WIN]?.size
        actual.shouldNotBeNull()
        actual shouldBe 1
    }

    @Test
    fun `딜러가 참가자와 점수가 동일하면 무승부`() {
        val blackjackResult = BlackjackResult(
            Dealer().apply {
                receiveCard(Card(Suit.SPADE, Rank.KING))
                receiveCard(Card(Suit.SPADE, Rank.KING))
            },
            listOf(
                Participant("a").apply {
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                    receiveCard(Card(Suit.SPADE, Rank.KING))
                }
            )
        )

        val actual = blackjackResult.result[BlackjackResult.Match.DRAW]?.size
        actual.shouldNotBeNull()
        actual shouldBe 1
    }
}
