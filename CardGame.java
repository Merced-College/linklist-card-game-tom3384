// Tom Nguyen
// November 29, 2024
// Linked List Card Game

// Added code for it to account for a second player's hand. 
// Maybe make a 2 player game where it adds up the total value of both of the players' hand by their total number value, and then the higher total value wins. 
// Added calculateHandValue method. 
// Also added a scanner to accept the user input: has the user guess which of the two players will win, and checks if their prediction was right. 

// Seems like the deck is still not shuffled yet, so I added a removeRandomCards method to remove 40 cards from the deck to act as a shuffle. 
// 
// But an array is probably easier to shuffle compared to a linked list. We might have to convert the linked list to an array or list, then shuffle it, then convert it back to a linked list.


//package linkedLists;

import java.io.BufferedReader;
// import java.io.File;
// import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import java.util.Scanner;
import java.util.Random;
// import java.util.LinkedList;



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

        // Remove random cards from the deck before starting the game, to act as a sort of shuffle.
        removeRandomCards(41); // Remove certain number of random cards
        // cardList.remove(1);

        // Print the loaded cards after removal
        System.out.println("Cards loaded after removal:");
        cardList.displayList();


		
        // The player's hand, an array of objects
		Card[] playerHand = new Card[5];
        Card[] player2Hand = new Card[5];
		for(int i = 0; i < playerHand.length; i++) {
			playerHand[i] = cardList.getFirst();
            player2Hand[i] = cardList.getFirst();
            // 
        }
            
            // The getFirst method removes cards from the start of the cardList to fill the player's hand (5 cards)
            // The getFirst method is from the LinkList.java file

        // Prints out the player's hand for the first three cards
		System.out.println("player 1's hand (first three cards revealed)");
		for(int i = 0; i < 3; i++)
			System.out.println(playerHand[i]);
            
        System.out.println();

        // Prints out the player 2's hand for the first three cards
		System.out.println("player 2's hand (first three cards revealed)");
		for(int i = 0; i < 3; i++)
			System.out.println(player2Hand[i]);

        System.out.println();

        // Letting the user input 1 or 2 to guess who the winner is. 
        Scanner userInputScanner = new Scanner(System.in); // Creates a scanner object
        System.out.println("Two players are given 5 cards each, and are battling it out to see who has the higher total card hand value.");
        System.out.println("The first 3 cards of each players hands have been shown, they still have 2 more cards to reveal.");
        System.out.println();
        System.out.println("Enter your guess on which of the two players will win (and have a higher card hand value): (Please enter '1' or '2')");
        // Takes the user's input and stores it as the userGuess, so we can use it later on to check if the user was right in their winner prediction.
        int userGuess = userInputScanner.nextInt();
        // Closes the scanner so that it won't be let open. 
        userInputScanner.close();


        // Prints out the remaining deck 
		System.out.println();
		System.out.println("the remaining deck: ");
		cardList.displayList();



        // Prints out the player's hand
		System.out.println("player 1's hand");
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
        System.out.println("The total value of the player 1's hand is: " + totalValuePlayer1);
        System.out.println("The total value of the player 2's hand is: " + totalValuePlayer2);

        System.out.println();

        String winner; 
        if (totalValuePlayer1 > totalValuePlayer2) {
            winner = "Player 1";           
            System.out.println("Player 1 wins and is an incredible card game player.");
        } else if (totalValuePlayer2 > totalValuePlayer1) {
            winner = "Player 2";
            System.out.println("Player 2 wins and is the victor! He is the undefeated champion!"); 
        } else {
            winner = "Tie";
            System.out.println("It's a tie, what a rare occasion.");
        }

        if ((userGuess == 1 && winner.equals("Player 1")) || (userGuess == 2 && winner.equals("Player 2")) ) {
            System.out.println("Your guess was correct and you predicted the winner!");
        } else if (winner.equals("Tie")) {
            System.out.println("It was a tie, so nobody won."); 
        } else {
            System.out.println("You lost! Your prediction was wrong! Try again!");
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

    // Method to remove random cards from the deck
    private static void removeRandomCards(int numCardsToRemove) {
        // Have to import the java.util.random class, like scanner 
        Random random = new Random();
        // int size = 0; 
        for (int i = 0; i < numCardsToRemove; i++) {
            // Generates a random index and remove that card at the index
            // nextInt will choose a random number up to the number in the parenthesis, this way we can make sure we don't go out of bounds 
            int randomIndex = random.nextInt(cardList.size()); 
            // We could also choose 10, but it might not be a fair shuffle, since it would just remove the top 1-10 index cards 
            // int randomIndex = random.nextInt(10); 
            
            cardList.deleteAtIndex(randomIndex); 
            // cardList.remove(randomIndex);
            // size--; 
            // System.out.println("Removed card at index: " + randomIndex);
        }
    }

}

//end class
