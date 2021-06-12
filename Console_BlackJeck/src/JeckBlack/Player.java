/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JeckBlack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Vitalik
 */
public class Player {

    public Table t = new Table();
    public PrintCard R = new PrintCard();
    public static ArrayList<String> cards = new ArrayList<>(Arrays.asList("♠ Two 2", "♠ Three 3", "♠ Four 4", "♠ Five 5", "♠ Six 6", "♠ Seven 7", "♠ Eight 8", "♠ Nine 9", "♠ Ten 10", "♠ Jack 10", "♠ Queen 10", "♠ King 10", "♠ Ace 11", "♥ Two 2", "♥ Three 3", "♥ Four 4", "♥ Five 5", "♥ Six 6", "♥ Seven 7", "♥ Eight 8", "♥ Nine 9", "♥ Ten 10", "♥ Jack 10", "♥ Queen 10", "♥ King 10", "♥ Ace 11", "♣ Two 2", "♣ Three 3", "♣ Four 4", "♣ Five 5", "♣ Six 6", "♣ Seven 7", "♣ Eight 8", "♣ Nine 9", "♣ Ten 10", "♣ Jack 10", "♣ Queen 10", "♣ King 10", "♣ Ace 11", "♦ Two 2", "♦ Three 3", "♦ Four 4", "♦ Five 5", "♦ Six 6", "♦ Seven 7", "♦ Eight 8", "♦ Nine 9", "♦ Ten 10", "♦ Jack 10", "♦ Queen 10", "♦ King 10", "♦ Ace 11"));
    public static ArrayList<String> cardreal = new ArrayList<>(Arrays.asList("♠ Two 2", "♠ Three 3", "♠ Four 4", "♠ Five 5", "♠ Six 6", "♠ Seven 7", "♠ Eight 8", "♠ Nine 9", "♠ Ten 10", "♠ Jack 10", "♠ Queen 10", "♠ King 10", "♠ Ace 1", "♥ Two 2", "♥ Three 3", "♥ Four 4", "♥ Five 5", "♥ Six 6", "♥ Seven 7", "♥ Eight 8", "♥ Nine 9", "♥ Ten 10", "♥ Jack 10", "♥ Queen 10", "♥ King 10", "♥ Ace 1", "♣ Two 2", "♣ Three 3", "♣ Four 4", "♣ Five 5", "♣ Six 6", "♣ Seven 7", "♣ Eight 8", "♣ Nine 9", "♣ Ten 10", "♣ Jack 10", "♣ Queen 10", "♣ King 10", "♣ Ace 1", "♦ Two 2", "♦ Three 3", "♦ Four 4", "♦ Five 5", "♦ Six 6", "♦ Seven 7", "♦ Eight 8", "♦ Nine 9", "♦ Ten 10", "♦ Jack 10", "♦ Queen 10", "♦ King 10", "♦ Ace 1"));
    
    public static ArrayList<String> PrintCards = new ArrayList<>();
    public static int stavka;
    public static int balance = 1000;
    public static int NumPlayer;
    public String Name;
    public String card1;
    public String card2;

    public void firstCard() throws IOException {
        card1 = t.rand(cards);
        card2 = t.rand(cards);

        NumPlayer = t.sum(card1) + t.sum(card2);

        PrintCards.add(card1);
        PrintCards.add(card2);
        R.WriteCard(PrintCards);
    }

}
