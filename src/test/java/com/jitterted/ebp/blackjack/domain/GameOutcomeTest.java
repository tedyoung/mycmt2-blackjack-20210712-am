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

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BEATS_DEALER);
    }

    @Test
    public void whenPlayerHitsAndGoesBustThenOutcomeIsPlayerBusted() throws Exception {
        Deck playerHitsAndGoesBustDeck = StubDeck.playerHitsAndGoesBust();
        Game game = new Game(playerHitsAndGoesBustDeck);
        game.initialDeal();

        game.playerHits();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_BUSTED);
    }

    @Test
    public void playerDealtBlackjackThenOutcomeIsWinsBlackjack() throws Exception {
        Deck playerDealtBlackjackDeck = new StubDeck(Rank.ACE,  Rank.QUEEN,
                                                     Rank.JACK, Rank.NINE);
        Game game = new Game(playerDealtBlackjackDeck);

        game.initialDeal();

        assertThat(game.determineOutcome())
                .isEqualTo(GameOutcome.PLAYER_WINS_BLACKJACK);
    }

    @Test
    public void playerStandsResultsInDealerTakesItsTurn() throws Exception {
        Deck dealerBeatsPlayerAfterDrawingAdditionalCardDeck =
                new StubDeck(Rank.TEN,   Rank.QUEEN,
                             Rank.NINE,  Rank.FIVE,
                             Rank.SIX);
        Game game = new Game(dealerBeatsPlayerAfterDrawingAdditionalCardDeck);
        game.initialDeal();

        game.playerStands();

        assertThat(game.dealerHand().cards())
                .hasSize(3);
    }


}