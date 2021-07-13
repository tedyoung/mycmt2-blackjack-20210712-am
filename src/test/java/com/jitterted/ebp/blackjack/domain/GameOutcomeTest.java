package com.jitterted.ebp.blackjack.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class GameOutcomeTest {

    @Test
    public void whenPlayerHasBetterHandThanDealerThenPlayerBeatsDealer() throws Exception {
        Deck playerBeatsDealerUponInitialDealDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                                                 Rank.QUEEN, Rank.JACK);
        Game game = new Game(playerBeatsDealerUponInitialDealDeck);
        game.initialDeal();

        game.playerStands();
        game.dealerTurn();

        assertThat(game.determineOutcome())
                .isEqualTo("You beat the Dealer! 💵");
    }

    @Test
    public void whenPlayerHitsAndGoesBustThenOutcomeIsPlayerBusted() throws Exception {
        Deck playerHitsAndGoesBustDeck = new StubDeck(Rank.TEN, Rank.EIGHT,
                                                      Rank.FIVE, Rank.JACK,
                                                      Rank.SEVEN);
        Game game = new Game(playerHitsAndGoesBustDeck);
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo("You Busted, so you lose.  💸");
    }
}