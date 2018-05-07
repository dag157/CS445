//package cs445proj3;


//CS 0445 Spring 2018
//Read this class and its comments very carefully to make sure you implement
//the class properly.  The data and public methods in this class are identical
//to those MyStringBuilder, with the exception of the two additional methods
//shown at the end.  You cannot change the data or add any instance
//variables.  However, you may (and will need to) add some private methods.
//No iteration is allowed in this implementation. 

//For more details on the general functionality of most of these methods, 
//see the specifications of the similar method in the StringBuilder class.  
public class MyStringBuilder2
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

	// Create a new MyStringBuilder2 initialized with the chars in String s
	public MyStringBuilder2(String s)
	{
		if (s == null || s.length() == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		} else {
		this.append(s);
		}
	}

	// Create a new MyStringBuilder2 initialized with the chars in array s
	public MyStringBuilder2(char [] s)
	{
		if (s == null || s.length == 0) // Special case for empty String
		{					 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		} else {
		this.append(s);
		}
	}

	// Create a new empty MyStringBuilder2
	public MyStringBuilder2()
	{ 			  // or null reference
			firstC = null;
			lastC = null;
			length = 0;
		
	}

	// Append MyStringBuilder2 b to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(MyStringBuilder2 b)
	{
		String str = b.toString();
		
		if(length == 0 && str != null) {
			CNode newNode = new CNode(str.charAt(0));
			firstC = newNode;
			lastC = newNode;
			length++;
			recursiveAppendString(str, 1);
		} else {
			recursiveAppendString(str, 0);
		}
		
		append(str);
		return this;
		
	}


	// Append String s to the end of the current MyStringBuilder2, and return
	// the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(String s)
	{
		
		if(length == 0) {
			
			firstC = new CNode(s.charAt(0));
			length = 1;
			CNode currNode = firstC;
			
			lastC = currNode;
			recursiveAppendString(s, 1);
			
		}  else {
			
			recursiveAppendString(s, 0);
		}
		return this;
		
	}

	// Append char array c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char [] c)
	{
		if(length == 0) {
			firstC = new CNode(c[0]);
			length = 1;
			CNode currNode = firstC;
			
			lastC = currNode;
			recursiveAppendChar(c, 1);
			
		}  else {
			
			recursiveAppendChar(c, 0);
		}
		return this;
	}

	// Append char c to the end of the current MyStringBuilder2, and
	// return the current MyStringBuilder2.  Be careful for special cases!
	public MyStringBuilder2 append(char c)
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

	// Return the character at location "index" in the current MyStringBuilder2.
	// If index is invalid, throw an IndexOutOfBoundsException.
	public char charAt(int index)
	{
		if (index < 0 || index > (length - 1))
			throw new IndexOutOfBoundsException();
		CNode character = getCNodeAt(index, firstC);
		return character.data;
	}

	// Delete the characters from index "start" to index "end" - 1 in the
	// current MyStringBuilder2, and return the current MyStringBuilder2.
	// If "start" is invalid or "end" <= "start" do nothing (just return the
	// MyStringBuilder2 as is).  If "end" is past the end of the MyStringBuilder2, 
	// only remove up until the end of the MyStringBuilder2. Be careful for 
	// special cases!
	public MyStringBuilder2 delete(int start, int end)
	{
		if(start < 0 || end <= start || start > length) {
			//do nothing
			return this;
		} else {
			//checks to see if the end is greater than length
			if(end >= length || end == (length - 1)) {
				end = (length);
				if(start > 0) {
					lastC = getCNodeAt(start - 1, firstC);
				} else {
					lastC = null;
				}
			}
			//checks for first delete
			if(start == 0) {
				CNode temp = getCNodeAt(end - 1, firstC);
				firstC = temp.next;
				length = length - end;
				return this;
			} else {
				
				CNode temp = getCNodeAt(end - 1, firstC);
				recursiveDelete(end - start, start, 1);
				
			}
		}
		return this;
	}

	// Delete the character at location "index" from the current
	// MyStringBuilder2 and return the current MyStringBuilder2.  If "index" is
	// invalid, do nothing (just return the MyStringBuilder2 as is).
	// Be careful for special cases!
	public MyStringBuilder2 deleteCharAt(int index)
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
				CNode nodeBefore = getCNodeAt(index - 1, firstC);
				nodeBefore.next = null;
				lastC = nodeBefore;
				length--;
			} else {
				//else searches and deletes
				CNode nodeBefore = getCNodeAt(index - 1, firstC);
				CNode nodeToRemove = nodeBefore.next;
				CNode nodeAfter = nodeToRemove.next;
				nodeBefore.next = nodeAfter;
				nodeToRemove = null;
				length--;
			}
		}
		return this;
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str first matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.
	public int indexOf(String str)
	{
		int indexOf = recursiveIndexOf(firstC, length, 0, -1, str);
		return indexOf;
	}

	// Insert String str into the current MyStringBuilder2 starting at index
	// "offset" and return the current MyStringBuilder2.  if "offset" == 
	// length, this is the same as append.  If "offset" is invalid
	// do nothing.
	public MyStringBuilder2 insert(int offset, String str)
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
				recursiveInsert(offset, 1, str);
			} else {
				offset -= 1;
				CNode nodeBefore = getCNodeAt(offset, firstC);
				CNode nodeAfter = nodeBefore.next;
				recursiveInsertMiddle(0, str, nodeBefore, nodeAfter);
			}
		
		}
		return this;
	}

	// Insert character c into the current MyStringBuilder2 at index
	// "offset" and return the current MyStringBuilder2.  If "offset" ==
	// length, this is the same as append.  If "offset" is invalid, 
	// do nothing.
	public MyStringBuilder2 insert(int offset, char c)
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
				CNode inserted = new CNode(c);
				CNode nodeBefore = getCNodeAt(offset + 0, firstC);
				CNode nodeAfter = nodeBefore.next;
				inserted.next = nodeAfter;
				nodeBefore.next = inserted;
				length++;
			}
		
		}
		return this;
	}

	// Insert char array c into the current MyStringBuilder2 starting at index
	// index "offset" and return the current MyStringBuilder2.  If "offset" is
	// invalid, do nothing.
	public MyStringBuilder2 insert(int offset, char [] c)
	{
		String str = new String(c);
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
				recursiveInsert(offset, 1, str);
			} else {
				offset -= 1;
				CNode nodeBefore = getCNodeAt(offset, firstC);
				CNode nodeAfter = nodeBefore.next;
				recursiveInsertMiddle(0, str, nodeBefore, nodeAfter);
			}
		
		}
		return this;
		
	}

	// Return the length of the current MyStringBuilder2
	public int length()
	{
		return length;
	}

	// Delete the substring from "start" to "end" - 1 in the current
	// MyStringBuilder2, then insert String "str" into the current
	// MyStringBuilder2 starting at index "start", then return the current
	// MyStringBuilder2.  If "start" is invalid or "end" <= "start", do nothing.
	// If "end" is past the end of the MyStringBuilder2, only delete until the
	// end of the MyStringBuilder2, then insert.  This method should be done
	// as efficiently as possible.  In particular, you may NOT simply call
	// the delete() method followed by the insert() method, since that will
	// require an extra traversal of the linked list.
	public MyStringBuilder2 replace(int start, int end, String str)
	{
		if (start < 0 || end <= start || start > length) {
			return this;
		} else if (end > length) {
			CNode curr = getCNodeAt(start, firstC);
			recursiveReplace((length - start), curr, str, 0);
			append(str.substring(length-start, str.length()));
			return this;
		} else {
			CNode curr = getCNodeAt(start, firstC);
			recursiveReplace((end-start), curr, str, 0); //replace characters
			insert(end, str.substring((end-start), str.length()));
			return this;
		}
	}

	// Reverse the characters in the current MyStringBuilder2 and then
	// return the current MyStringBuilder2.
	public MyStringBuilder2 reverse()
	{
				CNode currNode = firstC;
				CNode nextNode = null;
				CNode prevNode = null;
				recursiveReverse(currNode, nextNode, prevNode);
				return this;
	}
	
	private void recursiveReverse(CNode currNode, CNode nextNode, CNode prevNode) {
		if(currNode != null) {
			nextNode = currNode.next;
		     currNode.next = prevNode;
		     prevNode = currNode;
		     currNode = nextNode;
		     recursiveReverse(currNode, nextNode, prevNode);
		} else {
			//do nothing
			firstC = prevNode;
		}
	}
	
	// Return as a String the substring of characters from index "start" to
	// index "end" - 1 within the current MyStringBuilder2
	public String substring(int start, int end)
	{
		CNode beginngNode = getCNodeAt(start, firstC);
		char[] subString = new char[end - start];
		recursiveSubstring(subString, (end-start), 0, beginngNode);
		String result = new String(subString);
		return result;
	}
	
	private void recursiveSubstring(char[] subString, int lengthOfSubstring, int count, CNode currNode)
	{
		if (count == lengthOfSubstring - 1){
			subString[count] = currNode.data;
		} else {
			subString[count] = currNode.data;
			recursiveSubstring(subString, lengthOfSubstring, count + 1, currNode.next);
		}
	}

	// Return the entire contents of the current MyStringBuilder2 as a String
	public String toString()
	{
		char [] ToStringChar = new char[length];
        recursiveToString(0, ToStringChar, firstC);
        String contents = new String(ToStringChar);
        return contents;
		
	}

	// Find and return the index within the current MyStringBuilder2 where
	// String str LAST matches a sequence of characters within the current
	// MyStringBuilder2.  If str does not match any sequence of characters
	// within the current MyStringBuilder2, return -1.  Think carefully about
	// what you need to do for this method before implementing it.  For some
	// help with this see the Assignment 3 specifications.
	public int lastIndexOf(String str)
	{
		int indexOf = recursiveLastIndexOf(firstC, length, 0, -1, str);
		return indexOf;
	}
	
	public int lastIndexOf(char c)
	{
		String str = c + "";
		int indexOf = recursiveLastIndexOf(firstC, length, 0, -1, str);
		return indexOf;
	}
	
	// Find and return an array of MyStringBuilder2, where each MyStringBuilder2
	// in the return array corresponds to a part of the match of the array of
	// patterns in the argument.  If the overall match does not succeed, return
	// null.  For much more detail on the requirements of this method, see the
	// Assignment 3 specifications.
	public MyStringBuilder2 [] regMatch(String [] pats)
	{
		//System.out.print("hi");
			MyStringBuilder2[] answers = new MyStringBuilder2[pats.length];
			MyStringBuilder2[] finals = new MyStringBuilder2[pats.length];
			finals = recursiveArrayAdd(finals, pats, 0);
			if(finals[0] == null) {
				finals = null;
			}
			return finals;
		
	}
	
	private MyStringBuilder2 [] recursiveArrayAdd(MyStringBuilder2[] answers, String[] pats, int count) {
		//System.out.print("hi");
		
		if(count == pats.length || count > pats.length) {
			//do nothing
			answers = answersFix(answers, 0);
		} else {
			MyStringBuilder2 finalAnswer = new MyStringBuilder2("");
			answers[count] = recursiveRegMatch(0, count, 0, pats, finalAnswer, answers);
			if(answers[count].length < 1) {
				answers[count] = null;
				//answers[count].append("No Matches Found");
				//answers[count].replace(0, 100, "No Matches Found");
			}
			if(answers[count] == null) {
				//answers[count - 1] = null;
				//answers[count - 2] = null;
				answers[0] = null;
			}
			recursiveArrayAdd(answers, pats, (count + 1));
		}
		return answers;
	}
	
	//char array index not needed
	private MyStringBuilder2 recursiveRegMatch(int index, int arrayIndex, int charArrayIndex, String[] pats, MyStringBuilder2 finalAnswer, MyStringBuilder2[] answers) {
		char [] charArray; 
		char [] charArrayNext;
		if(arrayIndex == pats.length || arrayIndex > pats.length) {
			charArray = pats[0].toCharArray();
			charArrayNext = pats[0].toCharArray();
		} else {
			charArray = pats[arrayIndex].toCharArray();
			if(pats.length > 1 && arrayIndex < (pats.length - 1)) {
				charArrayNext = pats[arrayIndex + 1].toCharArray();
			} else {
				charArrayNext = null;
			}
		}
		if(arrayIndex == pats.length || arrayIndex > pats.length) {
			//do nothing
			//System.out.println(finalAnswer);
			return finalAnswer;
		} else {
			//System.out.println(finalAnswer);
			//finalAnswer.append((traverseString(0, 0, charArray, "", false)));
			//if(arrayIndex > 0) {
			//	if(answers[arrayIndex - 1] != null) {
			//		index = (answers[arrayIndex - 1].length - 1);
			//	}
			//}
			
			finalAnswer = new MyStringBuilder2(traverseString(index, 0, charArray, charArrayNext, "", false).toString());
			//finalAnswer.append(" ");
			//System.out.println(finalAnswer);
			return recursiveRegMatch(0, (arrayIndex+100), charArrayIndex, pats, finalAnswer, answers);
		}
		
		//return finalAnswer;
		
	}
	
	private MyStringBuilder2 traverseString(int index, int charArrayIndex, char[] charArray, char[] charArrayNext, String matchComp, boolean foundMatch) {
		boolean foundMatchOne = false;
		foundMatchOne = foundMatch;
		MyStringBuilder2 desiredOutcome = new MyStringBuilder2(matchComp);
		if(index == length) {
			//done
			if(matchComp.equals(" ")) {
				return null;
			}
			//System.out.println(desiredOutcome);
			//desiredOutcome.append(matchComp);
			return desiredOutcome;
		} else {
			if(this.contains(charArray, index, 0)) {
				
				matchComp = matchComp + this.charAt(index);
				foundMatchOne = true;
				//System.out.println(this.charAt(index));
				//System.out.println(desiredOutcome);
				 return traverseString((index+1), 0, charArray, charArrayNext, matchComp, foundMatchOne);
			} else {
				if(foundMatchOne == true) {
					//System.out.println(desiredOutcome);
					return desiredOutcome;
				} else {
				//System.out.print(matchComp);
				//System.out.println(desiredOutcome);
					return traverseString((index+1), 0, charArray, charArrayNext, matchComp, false);
				}
			}
		}
		
		//return desiredOutcome;
	}
	
	
	private boolean contains(char[] charArray, int indexOfStringBuilder, int charArrayIndex) {
		if(charArrayIndex == charArray.length) {
			return false;
		}
		if(this.charAt(indexOfStringBuilder) == charArray[charArrayIndex]) {
			return true;
		} else {
			return this.contains(charArray, indexOfStringBuilder, (charArrayIndex+1));
		}
	}
	
	private boolean contains(MyStringBuilder2 one, int index, int index2) {
		if(index == length) {
			return false;
		}
		if(this.charAt(index) == one.charAt(index2)) {
			return true;
		} else {
			return this.contains(one, (index+1), index2);
		}
	}
	
	private MyStringBuilder2[] answersFix(MyStringBuilder2 [] answers, int indexCount) {
		if((indexCount + 1) < (answers.length) && answers[indexCount] != null) {
			if(answers[indexCount].contains(answers[indexCount+1], 0, 0) && answers[indexCount].length > 1) {
				//System.out.println("HIIIIIIII");
				//answers[indexCount].lastIndexOf(answers[indexCount+1].toString());
				//answers[indexCount].delete(answers[indexCount].lastIndexOf(answers[indexCount+1].toString()), 200);
				if((answers[indexCount].charAt(answers[indexCount].length - 2)) != answers[indexCount + 1].charAt(0) && (answers[indexCount].charAt(answers[indexCount].length - 1)) == answers[indexCount + 1].charAt(0) && answers[indexCount].length != 1) {
					answers[indexCount + 1].deleteCharAt(0);
					return answers;
				}
				answers[indexCount].deleteCharAt(answers[indexCount].length - 1);
				
				//System.out.println(answers[0].lastIndexOf(answers[1].toString()));
				//answers[indexCount].delete((answers[indexCount].length - 1), 500);
				if(answers[indexCount].contains(answers[indexCount+1], 0, 0)) {
					
					//answers[indexCount+1].delete(0, 0);
					return answersFix(answers, indexCount);
				} else {
					//System.out.println(answers[0]);
					return answersFix(answers, (indexCount+1));
				}
			} else {
				return answersFix(answers, (indexCount+1));
			}
		} else {
			//answersFix(answers, (indexCount+1));
			return answers;
		}
	}
	
	private void recursiveAppendString(String s, int count) //recursive method that appends string to stringbuilder object
	{
			if(count == s.length()) {
			//do nothing
			} else {
			CNode newNode = new CNode(s.charAt(count));
			lastC.next = newNode;
			lastC = newNode;
			length++;
			recursiveAppendString(s, count + 1); //update
			}
		
	}
	
	private void recursiveAppendChar(char[] c, int count) //recursive method that appends characters to string builder object
	{
			if(count == c.length) {
				//do nothing
			} else {
			CNode newNode = new CNode(c[count]);
			lastC.next = newNode;
			lastC = newNode;
			length++;
			recursiveAppendChar(c, count + 1);
			}
		
	}
	
	private void recursiveToString(int count, char[] c, CNode currNode) {
		if(currNode != null) {
			c[count] = currNode.data;
			recursiveToString(count + 1, c, currNode.next);
		}
	}
	
	private void recursiveDelete(int number, int start, int count) {
		if(number != 0) {
			CNode nodeBefore = getCNodeAt(start - 1, firstC);
			CNode nodeToRemove = nodeBefore.next;
			CNode nodeAfter = nodeToRemove.next;
			nodeBefore.next = nodeAfter;
			nodeToRemove = null;
			length--;
			recursiveDelete(number - 1, start, count);
		}
	}
	
	private CNode getCNodeAt(int index, CNode first) {
		
		CNode currNode = first;
		if(index == 0) {
			return currNode;
		} else {
			return getCNodeAt(index - 1, currNode.next);
		}
	}
	
	private int recursiveIndexOf(CNode curr, int length, int counter, int indexOf, String str) 
	{
		if (counter == (length - 1)) {
			return indexOf;
		} else {
			if (curr.data == str.charAt(0)) {
				indexOf = counter;
				CNode temp = curr; 
				boolean isString = compare(curr.next, str, 1); 
				if (isString) {					
					return indexOf;
				}
				else {
					return recursiveIndexOf(temp.next, length, counter + 1, -1, str);
				}
			} else {
				return recursiveIndexOf(curr.next, length, counter + 1, -1, str);
			}
		}
	}
	
	private boolean compare(CNode curr, String str, int counter)
	{
		if (counter == str.length() - 1 && curr != null) {
			if (curr.data == str.charAt(counter)) {
				return true;
			} else {
				return false;
			}
		} else if (curr == null || curr.data != str.charAt(counter)) {
			return false;
		} else {
			return compare(curr.next, str, counter + 1);
		}
	}
	
	private int recursiveLastIndexOf(CNode curr, int length, int counter, int indexOf, String str) //recursive helper method that loops through
	{
		if (counter == (length - 1)) {
			return indexOf;
		} else {
			if (curr.data == str.charAt(0)) {
				indexOf = counter;
				CNode temp = curr; 
				boolean isString = compare(curr.next, str, 1); 
				if (isString) {					
					int lastOne = indexOf;
					if(recursiveLastIndexOf(temp.next, length, counter + 1, lastOne, str) != -1) {
						lastOne = recursiveLastIndexOf(temp.next, length, counter + 1, lastOne, str);
					}
					return lastOne;
				} else {
					return recursiveLastIndexOf(temp.next, length, counter + 1, -1, str);
				}
				
			} else {
				return recursiveLastIndexOf(curr.next, length, counter + 1, -1, str);
			}
		}
	}
	
	private void recursiveInsert(int offset, int i, String str) {
		if(i < str.length()) {
		CNode inserted = new CNode(str.charAt(i));
		CNode nodeBefore = getCNodeAt(offset + i - 2, firstC);
		CNode nodeAfter = nodeBefore.next;
		inserted.next = nodeAfter;
		nodeBefore.next = inserted;
		length++;
		recursiveInsert(offset, i+1, str);
		}
	}
	
	private void recursiveInsertMiddle(int i, String str, CNode nodeBefore, CNode nodeAfter) {
		if(i < str.length()) {
			CNode newNode = new CNode(str.charAt(i));
			nodeBefore.next = newNode;
			nodeBefore = newNode;
			length++;
			recursiveInsertMiddle(i+1, str, nodeBefore, nodeAfter);
		} else {
			nodeBefore.next = nodeAfter;
		}
	}
	
	private void recursiveReplace(int index, CNode indexNode, String str, int counter)
	{
		if (index == 1) {
			indexNode.data = str.charAt(counter);
		} else {
			indexNode.data = str.charAt(counter);
			recursiveReplace(index - 1, indexNode.next, str, counter + 1);
		}
	}
	
	
	
	// You must use this inner class exactly as specified below.  Note that
	// since it is an inner class, the MyStringBuilder2 class MAY access the
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