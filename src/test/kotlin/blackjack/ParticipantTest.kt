package blackjack

import blackjack.domain.Participant
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ParticipantTest {
    @Test
    internal fun `참가자 입력은 , 로 구분한다`() {
        val actual = Participant.createParticipants("정석준, Dino")
        val expected = listOf(Participant("정석준"), Participant("Dino"))
        actual shouldBe expected
    }

    @Test
    internal fun `참가자는 받은 카드를 가지고 있는다`() {
        val participant = Participant("정석준")
        participant.receiveCard(createCard("A스페이드"))
        participant.receiveCard(createCard("2스페이드"))
        participant.cardList shouldBe listOf(createCard("A스페이드"), createCard("2스페이드"))
    }

    @Test
    internal fun `참가자가 가지고 있는 카드는 score로 표현할 수 있다`() {
        data class TestCase(
            val participant: Participant,
            val expected: Int,
        )

        val testCaseList = listOf(
            TestCase(
                participant = Participant("정석준").apply {
                    receiveCard(createCard("10스페이드"))
                    receiveCard(createCard("10스페이드"))
                },
                expected = 20
            ),
            TestCase(
                participant = Participant("정석준").apply {
                    receiveCard(createCard("3스페이드"))
                    receiveCard(createCard("2스페이드"))
                },
                expected = 5
            ),
            )
        testCaseList.shouldForAll {
            it.participant.score shouldBe it.expected
        }

    }

    @Test
    internal fun `A는 1또는 11로 계산 된다`() {
        data class TestCase(
            val participant: Participant,
            val expected: Int,
        )

        val testCaseList = listOf(
            TestCase(
                participant = Participant("정석준").apply {
                    receiveCard(createCard("10스페이드"))
                    receiveCard(createCard("10스페이드"))
                    receiveCard(createCard("A스페이드"))
                },
                expected = 21
            ),
            TestCase(
                participant = Participant("정석준").apply {
                    receiveCard(createCard("10스페이드"))
                    receiveCard(createCard("A스페이드"))
                },
                expected = 21
            ),
            TestCase(
                participant = Participant("정석준").apply {
                    receiveCard(createCard("10스페이드"))
                    receiveCard(createCard("A스페이드"))
                    receiveCard(createCard("A스페이드"))
                },
                expected = 12
            ),
            )

        testCaseList.shouldForAll {
            it.participant.score shouldBe it.expected
        }
    }

    @Test
    internal fun `참가자가 가지고 있는 카드의 합이 21 미만이면 카드를 더 받을 수 있다`() {
        val participant = Participant("정석준").apply {
            receiveCard(createCard("10스페이드"))
            receiveCard(createCard("10스페이드"))
        }
        participant.canReceiveCard shouldBe true
    }

    @Test
    internal fun `참가자가 가지고 있는 카드의 합이 21 이상이면 카드를 더 받을 수 없다`() {
        val participant = Participant("정석준").apply {
            receiveCard(createCard("10스페이드"))
            receiveCard(createCard("10스페이드"))
            receiveCard(createCard("A스페이드"))
        }
        participant.canReceiveCard shouldBe false
    }


}
