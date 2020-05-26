package elvengame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck //balicek karet
 */
public class Deck {
    //do not have to change during the game, deckSize can simulate removing the cards

    private List<Card> deckCards;
    private int deckSize; //actual deck size

    public Deck(String[] symbols, String[] values, int[] nPoints) {
        deckCards = new ArrayList<>();
        generateAllCards(symbols, values, nPoints);
        this.deckSize = deckCards.size();
        shuffle();
    }

    /**
     * Generate List of all cards e.g. using DataStore class with arrays of symbols, values, nPoints
     */
    private void generateAllCards(String[] symbols, String[] values, int[] nPoints) {
        values = DataStore.loadValues();
        symbols = DataStore.loadSymbols();
        nPoints = DataStore.loadNPoints();

        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < symbols.length; j++) {
                deckCards.add(new Card(symbols[j],values[i],nPoints[i]));
            }
        }
    }

    /**
     * Shuffle list of cards
     * An algorithm to permute an array.
     */
    private void shuffle() {
        Collections.shuffle(deckCards);
    }

    /**
     * Deal one card
     * Get the card at deckSize-1 index and decrement deckSize by one
     *
     * @return the dealt card, or null if all the cards have been
     * previously dealt.
     */
   public Card deal() {
        Card card;
        if (deckCards.isEmpty()){
            return null;
        }
        card = deckCards.get(deckSize-1);
        deckCards.remove(deckSize-1);
        deckSize--;
        return card;
    }

    public int getDeckSize() {
        return deckSize;
    }

    public boolean isEmpty() {
        return getDeckSize() == 0;
    }

   /* public static void main(String[] args) {

        Deck deck = new Deck(DataStore.loadSymbols(),DataStore.loadValues(),DataStore.loadNPoints());
        for (Card card: deck.deckCards) {
            System.out.println(card.getSymbol() + " " + card.getValue() + " " + card.getnPoints());
        }
        System.out.println();
        Card card;
        card = deck.deal();
        System.out.println(card.getSymbol() + " " + card.getValue() + " " + card.getnPoints());

        card = deck.deal();
        System.out.println(card.getSymbol() + " " + card.getValue() + " " + card.getnPoints());

        card = deck.deal();
        System.out.println(card.getSymbol() + " " + card.getValue() + " " + card.getnPoints());
        System.out.println(deck.getDeckSize());
    }*/
}
