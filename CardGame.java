// Tom Nguyen
// November 29, 2024
// Linked List Card Game

// Maybe make a 2 player game where it adds up the total value of the player's hand by their number value, and then the higher total value wins. 

// Seems like the deck is still not shuffled yet, so might need to add that. 
// But an array is probably easier to shuffle compared to a linked list. We might have to convert the linked list to an array or list, then shuffle it, then convert it back to a linked list.

// Maybe we can also add code for it to account for multiple players. 




//package linkedLists;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;



public class CardGame {
	
	private static LinkList cardList = new LinkList();  // make list

	public static void main(String[] args) {

		// File name to read from
        String fileName = "cards.txt"; // Ensure the file is in the working directory or specify the full path

        // The cards.txt file is read line by line, and is split into (suit, name, value, and picture) 
        // A new object is created for each line and added to the linked list (cardList)
        // Read the file and create Card objects
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Split the line into components
                String[] details = line.split(","); // Assuming comma-separated values
                if (details.length == 4) {
                    // Parse card details
                    String suit = details[0].trim();
                    String name = details[1].trim();
                    int value = Integer.parseInt(details[2].trim());
                    String pic = details[3].trim();

                    // Create a new Card object
                    Card card = new Card(suit, name, value, pic);

                    // Add the Card object to the list
                    cardList.add(card);
                } else {
                    System.err.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        // Print the loaded cards
        System.out.println("Cards loaded:");
        cardList.displayList();


		
        // The player's hand, an array of objects
		Card[] playerHand = new Card[5];
        Card[] player2Hand = new Card[5];
		for(int i = 0; i < playerHand.length; i++) {
			playerHand[i] = cardList.getFirst();
            player2Hand[i] = cardList.getFirst();
        }
            
            // The getFirst method removes cards from the start of the cardList to fill the player's hand (5 cards)
            // The getFirst method is from the LinkList.java file


        // Prints out the remaining deck 
		System.out.println();
		System.out.println("the deck");
		cardList.displayList();

        // Prints out the player's hand
		System.out.println("players hand");
		for(int i = 0; i < playerHand.length; i++)
			System.out.println(playerHand[i]);
            
        System.out.println();

        // Prints out the player 2's hand
		System.out.println("player 2's hand");
		for(int i = 0; i < player2Hand.length; i++)
			System.out.println(player2Hand[i]);
            
        // Add some extra space for clarity 
        System.out.println();
		
        // Grab the total value of the player's hand after adding it up.
        int totalValuePlayer1 = calculateHandValue(playerHand);
        int totalValuePlayer2 = calculateHandValue(player2Hand);
        System.out.println("The total value of the player's hand is: " + totalValuePlayer1);
        System.out.println("The total value of the player 2's hand is: " + totalValuePlayer2);

        System.out.println();

        if (totalValuePlayer1 > totalValuePlayer2) {
            System.out.println("Player 1 wins and is an incredible card game player.");
        } else if (totalValuePlayer2 > totalValuePlayer1) {
            System.out.println("Player 2 wins and is the spectacular victor."); 
        } else {
            System.out.println("It's a tie, what a rare occasion.");
        }


	}//end main

    // Calculate the total value of a hand, we're going to first insert the player's hand into it. (Then additional players if we add it)
    // Takes an array of Card objects as an input (hand)
    // Iterates through the array and grabs each card's value, then adds it to the total. 
    private static int calculateHandValue(Card[] hand) {
        // Initialize it, and start it off at 0
        int total = 0;
        // A for-each loop, used exclusively to loop through elements in an array (or other data sets)
        // for (type variableName : arrayName)
        for (Card card : hand) {
            total += card.getCardValue();
            // get the card value, and add it to the total
        }

        return total;
    }

}

//end class
