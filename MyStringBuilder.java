//package cs445proj2;
// CS 0445 Spring 2018
// Read this class and its comments very carefully to make sure you implement
// the class properly.  Note the items that are required and that cannot be
// altered!  Generally speaking you will implement your MyStringBuilder using
// a singly linked list of nodes.  See more comments below on the specific
// requirements for the class.

// For more details on the general functionality of most of these methods, 
// see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder
{
	// These are the only three instance variables you are allowed to have.
	// See details of CNode class below.  In other words, you MAY NOT add
	// any additional instance variables to this class.  However, you may
	// use any method variables that you need within individual methods.
	// But remember that you may NOT use any variables of any other
	// linked list class or of the predefined StringBuilder or 
	// or StringBuffer class in any place in your code.  You may only use the
	// String class where it is an argument or return type in a method.
	private CNode firstC;	// reference to front of list.  This reference is necessary
							// to keep track of the list
	private CNode lastC; 	// reference to last node of list.  This reference is
							// necessary to improve the efficiency of the append()
							// method
	private int length;  	// number of characters in the list

	// You may also add any additional private methods that you need to
	// help with your implementation of the public methods.

	// Create a new MyStringBuilder initialized with the chars in String s
	public MyStringBuilder(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		}
		else
		{
			// Create front node to get started
			lastC = firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				lastC = currNode;
				length++;
			}
		}
	}

	// Create a new MyStringBuilder initialized with the chars in array s
	public MyStringBuilder(char [] s)
	{
		firstC = new CNode(s[0]);
		length = 1;
		CNode currNode = firstC;
		//create remaining nodes
		for(int i = 1; i < s.length; i++) {
			CNode newNode = new CNode(s[i]);
			currNode.next = newNode;
			currNode = newNode;
			length++;
		}
		//keep last node in place
		lastC = currNode;
	}

	// Create a new empty MyStringBuilder
	public MyStringBuilder()
	{
		firstC = null;
		lastC = null;
		length = 0;
	}

	// Append MyStringBuilder b to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(MyStringBuilder b)
	{
		CNode currNode;
		if(this.length == 0) {
			firstC = new CNode(b.charAt(0));
			length = 1;
			currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < b.length(); i++)
			{
				CNode newNode = new CNode(b.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		
		} else {
			//adds to the end of the linked list
			currNode = lastC;
			for (int i = 0; i < b.length; i++)
			{
				CNode newNode = new CNode(b.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			lastC = currNode;
		}
		return this;
	}

	// Append String s to the end of the current MyStringBuilder, and return
	// the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(String s)
	{
		if(this.length == 0) {
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			//replace last node
			lastC = currNode;
		} else {
			//adds to the end of the lniked list
			CNode currNode = lastC;
			for (int i = 0; i < s.length(); i++)
			{
				CNode newNode = new CNode(s.charAt(i));
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			//replace last node
			lastC = currNode;
		}
		return this;
	}

	// Append char array c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char [] c)
	{	
		if(this.length == 0) {
			firstC = new CNode(c[0]);
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			for (int i = 1; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			//replace last node pointer reference
			lastC = currNode;
		} else {
			//adds to the end
			CNode currNode = lastC;
			for (int i = 0; i < c.length; i++)
			{
				CNode newNode = new CNode(c[i]);
				currNode.next = newNode;
				currNode = newNode;
				length++;
			}
			//change last pointer
			lastC = currNode;
		}
		return this;
	}

	// Append char c to the end of the current MyStringBuilder, and
	// return the current MyStringBuilder.  Be careful for special cases!
	public MyStringBuilder append(char c)
	{
		if(this.length == 0) {
			firstC = new CNode(c);
			length = 1;
			CNode currNode = firstC;
			// Create remaining nodes, copying from String.  Note
			// how each new node is simply added to the end of the
			// previous one.  Trace this to see what is going on.
			lastC = currNode;
		} else {
			//adds to the end
			CNode currNode = lastC;
			lastC = currNode;
			CNode newNode = new CNode(c);
			currNode.next = newNode;
			currNode = newNode;
			length++;
			//sets last to point  here
			lastC = currNode;
		}
		return this;
	}

	// Return the character at location "index" in the current MyStringBuilder.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index) throws IndexOutOfBoundsException
	{
		//throws index out of bounds
		if(index > length || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		//double check
		assert (firstC != null) && (1 <= index) && (index <= length);
		CNode currNode = firstC;
		//find the node
		for(int counter = 0; counter < index; counter++) {
			currNode = currNode.next;
		}
		//triple check 
		assert currNode != null;
		//return it
		return currNode.data;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder, and return the current MyStringBuilder.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder as is).  If "end" is past the end of the MyStringBuilder, 
	// only remove up until the end of the MyStringBuilder. Be careful for 
	// special cases!
	public MyStringBuilder delete(int start, int end)
	{
		if(start < 0 || end <= start || start > length) {
			//do nothing
			return this;
		} else {
			//checks to see if the end is greater than length
			if(end >= length || end == (length - 1)) {
				end = (length);
				if(start > 0) {
					lastC = getCNodeAt(start - 1);
				} else {
					lastC = null;
				}
			}
			//checks for first delete
			if(start == 0) {
				for(int counter = 0; counter < (end); counter++) {
					firstC = firstC.next;
					length--;
				}
			} else {
				//deletes in the middle notice you only traverse linked list once
				for(int i = start; i < end; i++) {
					
					CNode nodeBefore = getCNodeAt(start - 1);
					CNode nodeToRemove = nodeBefore.next;
					CNode nodeAfter = nodeToRemove.next;
					nodeBefore.next = nodeAfter;
					nodeToRemove = null;
					length--;
				}
				
			}
		}
		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder and return the current MyStringBuilder.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder as is).
	// Be careful for special cases!
	public MyStringBuilder deleteCharAt(int index)
	{
		if(index < 0 || index > (length - 1)) {
			return this;
		} else {
			//deletes first one
			if(index == 0) {
				firstC = firstC.next;
				length--;
				
				//else deletes at end
			} else if (index == (length - 1)) {
				CNode nodeBefore = getCNodeAt(index - 1);
				nodeBefore.next = null;
				lastC = nodeBefore;
				length--;
			} else {
				//else searches and deletes
				CNode nodeBefore = getCNodeAt(index - 1);
				CNode nodeToRemove = nodeBefore.next;
				CNode nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
				nodeToRemove = null;
				length--;
			}
		}
		return this;
	}

	// Find and return the index within the current MyStringBuilder where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder.  If str does not match any sequence of characters
	// within the current MyStringBuilder, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		//the method works by the following. Check to see if the first ndoe matches., if not it'll return -1. Contiunally checks to match the linkedlist data to the string
		for(int i = 0; i < length; i++) {
			if(getCNodeAt(i).data == (str.charAt(0))) {
				
				for(int k = 1; k < str.length(); k++) {
					if((i + k) >= length) {
						
						k = str.length();
						return - 1;
					} else {
						if(getCNodeAt(i + k).data == str.charAt(k)) {
							
							if(k == (str.length() - 1)) {
								return i;
							}
							
						} else if(getCNodeAt(i + k).data != str.charAt(k)){
							if(i == (length - 1)) {
								return -1;
							}
						}
					}
				
				}
			}
		} 
		return -1;
	}

	// Insert String str into the current MyStringBuilder starting at index
	// "offset" and return the current MyStringBuilder.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder insert(int offset, String str)
	{
		
		if(offset > length || offset < 0) {
			//do nothing
		} else if(offset == length) {
			//append if at the end
			this.append(str);
		}  else {
			if (offset == 0) {
				//add new nodes from the front
				CNode newNode = new CNode(str.charAt(0));
				newNode.next = firstC;
				firstC = newNode;
				offset++;
				length++;
				for(int i = 1; i < str.length(); i++) {
					CNode inserted = new CNode(str.charAt(i));
					CNode nodeBefore = getCNodeAt(offset + i - 2);
					CNode nodeAfter = nodeBefore.next;
					inserted.next = nodeAfter;
					nodeBefore.next = inserted;
					length++;
				}
				
			} else {
				//checks for the middle
				offset -= 1;
				CNode newNode;
				CNode nodeBefore = getCNodeAt(offset);
				CNode nodeAfter = nodeBefore.next;
				for(int i = 0; i < str.length(); i++) {
					newNode = new CNode(str.charAt(i));
					nodeBefore.next = newNode;
					nodeBefore = newNode;
					length++;
				}
				nodeBefore.next = nodeAfter;
			}
		
		}
		return this;
	}

	// Insert character c into the current MyStringBuilder at index
	// "offset" and return the current MyStringBuilder.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder insert(int offset, char c)
	{
		if(offset > length || offset < 0) {
			//do nothing
		} else if(offset == length) {
			//appends if the end
			this.append(c);
		}  else {
			if (offset == 0) {
				//if front append to the front
				CNode newNode = new CNode(c);
				newNode.next = firstC;
				firstC = newNode;
				offset++;
				length++;
			} else {
				//append to the middle
				offset -= 1;
				for(int i = 0; i < 1; i++) {
					CNode inserted = new CNode(c);
					CNode nodeBefore = getCNodeAt(offset + i);
					CNode nodeAfter = nodeBefore.next;
					inserted.next = nodeAfter;
					nodeBefore.next = inserted;
					length++;
				}
			}
		
		}
		return this;
	}

	// Insert char array c into the current MyStringBuilder starting at index
	// index "offset" and return the current MyStringBuilder.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder insert(int offset, char [] c)
	{
		if(offset > length || offset < 0) {
			//do nothing
		} else if(offset == length) {
			//append end
			this.append(c[0]);
		}  else {
			//add to front
			if (offset == 0) {
				CNode newNode = new CNode(c[0]);
				newNode.next = firstC;
				firstC = newNode;
				offset++;
				length++;
				for(int i = 1; i < c.length; i++) {
					CNode inserted = new CNode(c[i]);
					CNode nodeBefore = getCNodeAt(offset + i - 2);
					CNode nodeAfter = nodeBefore.next;
					inserted.next = nodeAfter;
					nodeBefore.next = inserted;
					length++;
				}
				//add to the middle
			} else {
				offset -= 1;
				for(int i = 0; i < c.length; i++) {
					CNode inserted = new CNode(c[i]);
					CNode nodeBefore = getCNodeAt(offset + i);
					CNode nodeAfter = nodeBefore.next;
					inserted.next = nodeAfter;
					nodeBefore.next = inserted;
					length++;
				}
			}
		
		}
		return this;
	}

	// Return the length of the current MyStringBuilder
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder, then insert String "str" into the current
	// MyStringBuilder starting at index "start", then return the current
	// MyStringBuilder.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder, only delete until the
	// end of the MyStringBuilder, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder replace(int start, int end, String str)
	{
		//if end is greater, add charcters to length
		if(end > length) {
			end = length - 1;
			for(int i = start; i < end; i++) {
				CNode desiredNode = getCNodeAt(i);
				char original = desiredNode.data;
				desiredNode.data = str.charAt(i - start);
			}
			if((end - start) < str.length()) {
				String add = "";
				for(int j = (end - start); j < str.length(); j++) {
					add += str.charAt(j);
				}
				this.append(add);
			}
		
		} else {
			//else just replace and check to see if more charcters are needed almsot like an insert and replace together
			if((start >= 1) && (start <= length)) {
				for(int i = start; i < end; i++) {
					CNode desiredNode = getCNodeAt(i);
					char original = desiredNode.data;
					desiredNode.data = str.charAt(i - start);
				}
				if((end - start) < str.length()) {
					String add = "";
					for(int j = (end - start); j < str.length(); j++) {
						add += str.charAt(j);
					}
					this.insert(end, add);
				}
			}
		}
		
		return this;
		
	}

	// Reverse the characters in the current MyStringBuilder and then
	// return the current MyStringBuilder.
	public MyStringBuilder reverse()
	{
		//reverses string builder
		CNode currNode = firstC;
		CNode nextNode = null;
		CNode prevNode = null;
		//reverees until currNode becomes null
		while(currNode!=null){
		     nextNode = currNode.next;
		     currNode.next = prevNode;
		     prevNode = currNode;
		     currNode = nextNode;
		}
		//change pointer
		firstC = prevNode;
		return this;
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder
	public String substring(int start, int end)
	{
		//get a substring by locating nodes and copying data
		String subString = "";
		for(int i = start; i < end; i++) {
			subString += getCNodeAt(i).data;
		}
		return subString;
	}

	// Return the entire contents of the current MyStringBuilder as a String
	public String toString()
	{
		//grabs data from list and returns it as a string made up of chars
		assert (firstC != null) && (1 <= length);
		CNode currNode = firstC;
		String contents = "";
		for(int counter = 0; counter < length; counter++) {
			if(currNode.next != null) {
			contents += currNode.data;
			currNode = currNode.next;
			} else {
				contents += currNode.data;
			}
		}
		lastC = currNode;
		assert currNode != null;
		
		return contents;
	}
	
	private CNode getCNodeAt(int index) {
		//searches for a node given an index based on length of linkedlist
		CNode currNode = firstC;
		for(int counter = 0; counter < index; counter++) {
			currNode = currNode.next;
			
		}
		
		return currNode;
		
	}

	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder class MAY access the
	// data and next fields directly.
	private class CNode
	{
		private char data;
		private CNode next;

		public CNode(char c)
		{
			data = c;
			next = null;
		}

		public CNode(char c, CNode n)
		{
			data = c;
			next = n;
		}
	}
}

