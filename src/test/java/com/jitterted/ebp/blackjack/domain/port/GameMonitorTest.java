package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import com.jitterted.ebp.blackjack.domain.StubDeck;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;

class GameMonitorTest {

    @Test
    public void playerStandsCompletesGameIsSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = Mockito.spy(GameMonitor.class);
        Game game = new Game(new Deck(), gameMonitorSpy);
        game.initialDeal();

        game.playerStands();

        Mockito.verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsAndGoesBustResultsInGameSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = Mockito.spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerHitsAndGoesBust(), gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        Mockito.verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

    @Test
    public void playerHitsDoesNotBustResultsInNothingSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = Mockito.spy(GameMonitor.class);
        Game game = new Game(StubDeck.playerHitsAndDoesNotGoBust(), gameMonitorSpy);
        game.initialDeal();

        game.playerHits();

        Mockito.verify(gameMonitorSpy, never()).roundCompleted(any(Game.class));
    }

}