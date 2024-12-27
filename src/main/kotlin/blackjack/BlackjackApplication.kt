package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.BlackjackShoe
import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        val blackjackShoe = BlackjackShoe()
        val (dealer, participantList) = ready(blackjackShoe)
        play(dealer = dealer, participantList = participantList, blackjackShoe = blackjackShoe)
        finish(dealer = dealer, participantList = participantList)
    }

    private fun ready(blackjackShoe: BlackjackShoe): Pair<Dealer, List<Participant>> {
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

    private fun play(dealer: Dealer, participantList: List<Participant>, blackjackShoe: BlackjackShoe) {
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
        outputView.showResult(BlackjackResults(dealer = dealer, participants = participantList))
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
