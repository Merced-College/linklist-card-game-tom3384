// Tom Nguyen
// November 29, 2024
// Linked List Card Game

//package linkedLists;
//linkList2.java
//demonstrates linked list
//to run this program: C>java LinkList2App
////////////////////////////////////////////////////////////////
public class Link
{
	public Card cardLink;             // next link in list
	public Link next;
	//-------------------------------------------------------------
	public Link(Card card) // constructor
	{
		cardLink = card;
	}
	//-------------------------------------------------------------
	public void displayLink()      // display ourself
	{
		System.out.println(cardLink);
		// System.out.println("This is the link.java file");
		// So this is run every line when it prints out all the cards 
		// This is used in the LinkList.java file, with displayList method 
	}
}  // end class Link
////////////////////////////////////////////////////////////////
