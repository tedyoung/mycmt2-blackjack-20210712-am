package com.jitterted.ebp.blackjack.adapter.in.console;

import com.jitterted.ebp.blackjack.domain.Card;
import com.jitterted.ebp.blackjack.domain.Rank;
import com.jitterted.ebp.blackjack.domain.Suit;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.fusesource.jansi.Ansi.ansi;

public class CardDisplayTest {

    private static final Rank DUMMY_RANK = Rank.TEN;

    @Test
    public void displayTenAsString() throws Exception {
        Card card = new Card(Suit.DIAMONDS, Rank.TEN);

        String display = ConsoleCard.display(card);

        assertThat(display)
                .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♦    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenAsString() throws Exception {
        Card card = new Card(Suit.SPADES, Rank.FOUR);

        String display = ConsoleCard.display(card);

        assertThat(display)
                .isEqualTo("[30m┌─────────┐[1B[11D│4        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        4│[1B[11D└─────────┘");
    }

    @Test
    public void suitOfHeartsOrDiamondsIsDisplayedInRed() throws Exception {
        // given a card with Hearts or Diamonds
        Card heartsCard = new Card(Suit.HEARTS, DUMMY_RANK);
        Card diamondsCard = new Card(Suit.DIAMONDS, DUMMY_RANK);

        // when we ask for its display representation
        String ansiRedString = ansi().fgRed().toString();

        // then we expect a red color ansi sequence
        assertThat(ConsoleCard.display(heartsCard))
                .contains(ansiRedString);
        assertThat(ConsoleCard.display(diamondsCard))
                .contains(ansiRedString);
    }

}
