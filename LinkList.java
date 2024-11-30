// Tom Nguyen
// November 29, 2024
// Linked List Card Game

// With a linked list, the deck can grow or shrink as cards are added/removed. 

// import java.util.LinkedList;

public class LinkList
{
	private Link first;            // ref to first link on list
	private int size = 52; 		   // track the size of the list for when we delete cards at the start

	//-------------------------------------------------------------
	public LinkList()              // constructor
	{
		first = null;               // no links on list yet
		// keeps track of the first link (head of the list)
	}
	//-------------------------------------------------------------
	// Adds a card to the start of the list.
	public void insertFirst(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
	}
	// Adds a card to the list.
	public void add(Card card)
	{                           // make new link
		Link newLink = new Link(card);
		newLink.next = first;       // it points to old first link
		first = newLink;            // now first points to this
	}
	//-------------------------------------------------------------
	// Searches for a card in the list.
	public Link find(Card cardToFind)      // find link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // start at 'first'
		while(!current.cardLink.equals(cardToFind))        // while no match,
		{
			if(current.next == null)        // if end of list,
				return null;                 // didn't find it
			else                            // not end of list,
				current = current.next;      // go to next link
		}
		return current;                    // found it
	}
	//-------------------------------------------------------------
	// Removes a specific card from the list.
	public Link delete(Card cardToFind)    // delete link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // search for link
		Link previous = first;
		while(!current.cardLink.equals(cardToFind))
		{
			if(!current.cardLink.equals(cardToFind))
				return null;                 // didn't find it
			else
			{
				previous = current;          // go to next link
				current = current.next;
			}
		}                               // found it
		// Fixed bug and changed it to current.cardLink.equals, instead of current.equals
		if(current.cardLink.equals(cardToFind))               // if first link,
			first = first.next;             //    change first
		else                               // otherwise,
			previous.next = current.next;   //    bypass it
		return current;
	}
	//-------------------------------------------------------------
	// Displays all cards in the list.
	// .displayLink is in the Link.java file, and this prints out all the cards in the list using that method. 
	public void displayList()      // display the list
	{
		System.out.print("List (first-->last): ");
		Link current = first;       // start at beginning of list
		while(current != null)      // until end of list,
		{
			current.displayLink();   // print data
			current = current.next;  // move to next link
		}
		System.out.println("");
	}
	//-------------------------------------------------------------

	//-------------------------------------------------------------
	// Removes and returns the card at the start of the list.
	public Card getFirst()    // delete link with given key
	{                           // (assumes non-empty list)
		Link current = first;              // search for link
		first = first.next;             //    change first
		return current.cardLink;
	}

	public void deleteAtIndex(int index) {
		// making sure the index is in bounds
		if (index < 0 || index >= size) {
			// Prints out an error message
			System.out.println("Index out of bounds."); 
			return; 
		}

		Link current = first;
		Link previous = null; 

		// If the card to delete is the first card, then: 
		if (index == 0) {
			first = first.next;
		} else {
			// Find the code at the given index to delete
			for (int i = 0; i < index; i++) {
				previous = current; 
				current = current.next; 
			}

			// Bypass the current node 
			if (previous != null) {
				previous.next = current.next;
			}
		}
		// keep track of the size of the deck as it's being deleted/whittled down
		size--; 


	}

	public int size() {
		return size; 
	}



}  // end class LinkList
////////////////////////////////////////////////////////////////
/*class LinkedLists
{
	public static void main(String[] args)
	{
		LinkList theList = new LinkList();  // make list

		theList.insertFirst(new Card("heart", "ace", 11,"ah.gif"));      // insert 4 items
		theList.insertFirst(new Card("Spade", "ace", 11,"as.gif"));
		//theList.insertFirst(66, 6.99);
		//theList.insertFirst(88, 8.99);

		theList.displayList();              // display list

		Link f = theList.find(new Card("heart", "ace", 11,"ah.gif"));          // find item
		if( f != null)
			System.out.println("Found link with key " + f.cardLink);
		else
			System.out.println("Can't find link");

		Link d = theList.delete(new Card("heart", "ace", 11,"ah.gif"));        // delete item
		if( d != null )
			System.out.println("Deleted link with key " + d.cardLink);
		else
			System.out.println("Can't delete link");

		theList.displayList();              // display list
	}  // end main()
}  // end class LinkList2App
////////////////////////////////////////////////////////////////
/// */
