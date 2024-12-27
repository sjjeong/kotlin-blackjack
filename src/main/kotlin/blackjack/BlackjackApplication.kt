package blackjack

import blackjack.domain.BlackjackResult
import blackjack.domain.BlackjackShoe
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val blackjackShoe: BlackjackShoe = BlackjackShoe(),
) {

    fun run() {
        val (dealer, participantList) = ready()
        play(dealer = dealer, participantList = participantList)
        finish(dealer = dealer, participantList = participantList)
    }

    private fun ready(): Pair<Dealer, List<Participant>> {
        val participantNames = inputView.getParticipantNames()

        val dealer = Dealer()
        val participants = participantNames.map { name -> Participant(name.trim()) }

        dealer.receiveCard(blackjackShoe.draw())
        participants.forEach { participant ->
            repeat(2) {
                participant.receiveCard(blackjackShoe.draw())
            }
        }

        outputView.showReady(dealer = dealer, participants = participants)
        return dealer to participants
    }

    private fun play(dealer: Dealer, participantList: List<Participant>) {
        participantList.forEach { participant ->
            while (participant.canReceiveCard && inputView.getMoreCard(participant)) {
                val card = blackjackShoe.draw()
                participant.receiveCard(card)
                outputView.showParticipantCardList(participant)
            }
        }

        while (dealer.canReceiveCard) {
            val card = blackjackShoe.draw()
            dealer.receiveCard(card)
            outputView.showDealerReceivedCard()
        }
    }

    private fun finish(dealer: Dealer, participantList: List<Participant>) {
        outputView.showDealerInfo(dealer = dealer)
        outputView.showParticipantsInfo(participantList)
        outputView.showResult(BlackjackResult(dealer = dealer, participants = participantList))
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
