package elvengame;

import java.util.Arrays;

public class Board implements BoardInterface{
    private Card[] cards;
    private Deck deck;
    
    public Board(){
        //create deck
        deck = new Deck(DataStore.loadSymbols(),DataStore.loadValues(),DataStore.loadNPoints());
        //deal cards
        cards = new Card[9];
        for (int i = 0; i < 9; i++) {
            cards[i] = deck.deal();
        }
    }
    
    @Override
    public String gameName() {
        return "Hra jedenactka";
    }
    
    @Override
    public int nCards() {
        return cards.length;
    }

    @Override
    public int getDeckSize() {
        return deck.getDeckSize();
    }
    
    @Override
    public String getCardDescriptionAt(int index){
        if(cards[index] == null){
            return " ";
        }
        return cards[index].getSymbol() + "-" + cards[index].getValue();
    }

    @Override
    public boolean anotherPlayIsPossible() {
        //5 and 6; 4 and 7; 3 and 8; 2 and 9; Ace(1) and 10 or Q J K
        int [] nPoints = new int[9];
        String[] values = new String[9];
        for (int i = 0; i < cards.length; i++) {
            if (!cards[i].equals(null)) {
                nPoints[i] = cards[i].getnPoints();
                values[i] = cards[i].getValue();
            }
        }
        Arrays.sort(nPoints);
        Arrays.sort(values);
        if (nPoints[0] == 0 && nPoints[1] == 0 && nPoints[2] == 0){
            if (Arrays.binarySearch(values,"Q") >= 0 && Arrays.binarySearch(values,"J") >= 0
            && Arrays.binarySearch(values,"K") >= 0){
                return true;
            }
        }
        if (Arrays.binarySearch(nPoints,5) >= 0 && Arrays.binarySearch(nPoints,6) >= 0){
            return true;
        }
        if (Arrays.binarySearch(nPoints,4) >= 0 && Arrays.binarySearch(nPoints,7) >= 0){
            return true;
        }
        if (Arrays.binarySearch(nPoints,3) >= 0 && Arrays.binarySearch(nPoints,8) >= 0){
            return true;
        }
        if (Arrays.binarySearch(nPoints,2) >= 0 && Arrays.binarySearch(nPoints,9) >= 0){
            return true;
        }
        if (Arrays.binarySearch(nPoints,1) >= 0 && Arrays.binarySearch(nPoints,10) >= 0){
            return true;
        }

        return false;
    }

    @Override
    public boolean playAndReplace(int[] iSelectedCards) {
        int sum = 0;
        if (iSelectedCards.length == 3){
            for (int i = 0; i < 3; i++) {
                sum += cards[iSelectedCards[i]].getnPoints();
            }
            if (sum == 0){
                for (int i = 0; i < 3; i++) {
                    cards[iSelectedCards[i]] = deck.deal();
                }
                return true;
            }
        }
        if (iSelectedCards.length == 2){
            for (int i = 0; i < 2; i++) {
                sum += cards[iSelectedCards[i]].getnPoints();
            }
            if (sum == 11){
                for (int i = 0; i < 2; i++) {
                    cards[iSelectedCards[i]] = deck.deal();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isWon() {
        return cards.length == 0;
    }
}
