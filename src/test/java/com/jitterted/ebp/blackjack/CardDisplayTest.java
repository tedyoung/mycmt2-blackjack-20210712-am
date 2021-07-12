package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class CardDisplayTest {

    @Test
    public void displayTenAsString() throws Exception {
        Card card = new Card(Suit.DIAMONDS, Rank.TEN);

        String display = card.display();

        assertThat(display)
                .isEqualTo("[31m┌─────────┐[1B[11D│10       │[1B[11D│         │[1B[11D│    ♦    │[1B[11D│         │[1B[11D│       10│[1B[11D└─────────┘");
    }

    @Test
    public void displayNonTenAsString() throws Exception {
        Card card = new Card(Suit.SPADES, Rank.FOUR);

        String display = card.display();

        assertThat(display)
                .isEqualTo("[30m┌─────────┐[1B[11D│4        │[1B[11D│         │[1B[11D│    ♠    │[1B[11D│         │[1B[11D│        4│[1B[11D└─────────┘");
    }
}
