package com.jitterted.ebp.blackjack.domain.port;

import com.jitterted.ebp.blackjack.domain.Deck;
import com.jitterted.ebp.blackjack.domain.Game;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;

class GameMonitorTest {

    @Test
    public void playerStandsCompletesGameIsSentToMonitor() throws Exception {
        GameMonitor gameMonitorSpy = Mockito.spy(GameMonitor.class);
        Game game = new Game(new Deck(), gameMonitorSpy);
        game.initialDeal();

        game.playerStands();

        Mockito.verify(gameMonitorSpy).roundCompleted(any(Game.class));
    }

}