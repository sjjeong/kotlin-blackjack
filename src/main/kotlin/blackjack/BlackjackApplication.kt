package blackjack

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
        val participantList = ready()
        play(participantList)
        finish(participantList)
    }

    private fun ready(): List<Participant> {
        val participantNameString = inputView.getParticipantNames()

        val dealer = Dealer.createDealer(blackjackShoe = blackjackShoe)
        val participants = Participant.createParticipants(
            participantString = participantNameString,
            blackjackShoe = blackjackShoe
        )
        outputView.showReady(dealer = dealer, participants = participants)
        return participants
    }

    private fun play(participantList: List<Participant>) {
        participantList.forEach { participant ->
            while (participant.canReceiveCard && inputView.getMoreCard(participant)) {
                val card = blackjackShoe.draw()
                participant.receiveCard(card)
                outputView.showParticipantCardList(participant)
            }
        }
    }

    private fun finish(participantList: List<Participant>) {
        outputView.showParticipantsInfo(participantList)
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
