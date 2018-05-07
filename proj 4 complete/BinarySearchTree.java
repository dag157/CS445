//package MyTreePackage;
//CS 0445 Spring 2018
//Modified BinarySearchTree class.  This class now extends
//ComparableBinaryTree rather than BinaryTree.
//Add your methods to the class so that it works as specified
//in the assignment.  Note: This class will work without any modifications,
//since it is not defining any new methods -- rather it is overriding already
//existing methods to be more efficient.  However, you will lose credit if
//you do not override the methods to take advantage of the fact that this is
//a BST, not a general ComparableBinaryTree.

package MyTreePackage;
import java.util.ArrayList;
import java.util.Iterator;
/**
A class that implements the ADT binary search tree by extending BinaryTree.
Recursive version.

@author Frank M. Carrano
@author Timothy M. Henry
@version 4.0
*/
public class BinarySearchTree<T extends Comparable<? super T>>
          extends ComparableBinaryTree<T> 
          implements SearchTreeInterface<T>
{
public BinarySearchTree()
{
   super();
} // end default constructor

public BinarySearchTree(T rootEntry)
{
   super();
   setRootNode(new BinaryNode<>(rootEntry));
} // end constructor

public void setTree(T rootData) // Disable setTree (see Segment 25.6)
{
   throw new UnsupportedOperationException();
} // end setTree

public void setTree(T rootData, BinaryTreeInterface<T> leftTree, 
                                BinaryTreeInterface<T> rightTree)
{
   throw new UnsupportedOperationException();
} // end setTree

	public T getEntry(T entry)
	{
	   return findEntry(getRootNode(), entry);
	} // end getEntry

// Recursively finds the given entry in the binary tree rooted at the given node.
	private T findEntry(BinaryNode<T> rootNode, T entry)
	{	
   T result = null;

   if (rootNode != null)
   {
      T rootEntry = rootNode.getData();

      if (entry.equals(rootEntry))
         result = rootEntry;
      else if (entry.compareTo(rootEntry) < 0)
         result = findEntry(rootNode.getLeftChild(), entry);
      else
         result = findEntry(rootNode.getRightChild(), entry);
   } // end if

   return result;
	} // end findEntry
	
	public boolean contains(T entry)
	{
		return getEntry(entry) != null;
	} // end contains
	
public T add(T newEntry)
{
   T result = null;

   if (isEmpty())
      setRootNode(new BinaryNode<>(newEntry));
   else
      result = addEntry(getRootNode(), newEntry);
    
   return result;
} // end add

// Adds newEntry to the nonempty subtree rooted at rootNode.
private T addEntry(BinaryNode<T> rootNode, T newEntry)
{
   assert rootNode != null;
   T result = null;
   int comparison = newEntry.compareTo(rootNode.getData());

   if (comparison == 0)
   {
      result = rootNode.getData();
      rootNode.setData(newEntry);
   }
   else if (comparison < 0)
   {
      if (rootNode.hasLeftChild())
         result = addEntry(rootNode.getLeftChild(), newEntry);
      else
         rootNode.setLeftChild(new BinaryNode<>(newEntry));
   }
   else
   {
      assert comparison > 0;

      if (rootNode.hasRightChild())
         result = addEntry(rootNode.getRightChild(), newEntry);
      else
         rootNode.setRightChild(new BinaryNode<>(newEntry));
   } // end if

   return result;
} // end addEntry

	public T remove(T entry)
{
   ReturnObject oldEntry = new ReturnObject(null);
   BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
   setRootNode(newRoot);

   return oldEntry.get();
} // end remove

	// Removes an entry from the tree rooted at a given node.
// Parameters:
//    rootNode  A reference to the root of a tree.
//    entry  The object to be removed.
//    oldEntry  An object whose data field is null.
// Returns:  The root node of the resulting tree; if entry matches
//           an entry in the tree, oldEntry's data field is the entry
//           that was removed from the tree; otherwise it is null.
private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry,
                                  ReturnObject oldEntry)
{
   if (rootNode != null)
   {
      T rootData = rootNode.getData();
      int comparison = entry.compareTo(rootData);

      if (comparison == 0)       // entry == root entry
      {
         oldEntry.set(rootData);
         rootNode = removeFromRoot(rootNode);
      }
      else if (comparison < 0)   // entry < root entry
      {
         BinaryNode<T> leftChild = rootNode.getLeftChild();
         BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
         rootNode.setLeftChild(subtreeRoot);
      }
      else                       // entry > root entry
      {
         BinaryNode<T> rightChild = rootNode.getRightChild();
         rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
      } // end if
   } // end if

   return rootNode;
} // end removeEntry

	// Removes the entry in a given root node of a subtree.
// Parameter:
//    rootNode  A reference to the root of the subtree.
// Returns:  The root node of the revised subtree.
private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode)
{
   // Case 1: rootNode has two children 
   if (rootNode.hasLeftChild() && rootNode.hasRightChild())
   {
      // Find node with largest entry in left subtree
      BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
      BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);

      // Replace entry in root
      rootNode.setData(largestNode.getData());

      // Remove node with largest entry in left subtree
      rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
   } // end if 

   // Case 2: rootNode has at most one child
   else if (rootNode.hasRightChild())
      rootNode = rootNode.getRightChild();
   else
      rootNode = rootNode.getLeftChild();
    
   // Assertion: If rootNode was a leaf, it is now null

   return rootNode; 
} // end removeEntry

// Finds the node containing the largest entry in a given tree.
// Parameter:
//    rootNode  A reference to the root node of the tree.
// Returns:  The node containing the largest entry in the tree.
private BinaryNode<T> findLargest(BinaryNode<T> rootNode)
{
   if (rootNode.hasRightChild())
      rootNode = findLargest(rootNode.getRightChild());
    
   return rootNode;
} // end findLargest

	// Removes the node containing the largest entry in a given tree.
// Parameter:
//    rootNode  A reference to the root node of the tree.
// Returns:  The root node of the revised tree.
private BinaryNode<T> removeLargest(BinaryNode<T> rootNode)
{
   if (rootNode.hasRightChild())
   {
      BinaryNode<T> rightChild = rootNode.getRightChild();
      rightChild = removeLargest(rightChild);
      rootNode.setRightChild(rightChild);
   }
   else 
      rootNode = rootNode.getLeftChild();
    
   return rootNode;
} // end removeLargest

	private class ReturnObject
	{
		private T item;
			
		private ReturnObject(T entry)
		{
			item = entry;
		} // end constructor
		
		public T get()
		{
			return item;
		} // end get

		public void set(T entry)
		{
			item = entry;
		} // end set
	} // end ReturnObject

	@Override
	public T getMax() {
		return getMax(getRootNode());
	}

	@Override
	public T getMin() {
		return getMin(getRootNode());
	}

	@Override
	public boolean isBST() {
		// always a BST 
		return true;
	}

	@Override
	public int rank(T data) {
		//check for ranks
		int i = 0;
		
		while(i< getNumberOfNodes()) {
			//iterate through tree
			if(get(i).compareTo(data) == 0) {
				return i;
			} else if (data.compareTo(get(i)) > 0) {
				//checks to see if less than
				if(i < (getNumberOfNodes() - 1)) {
					//compare to next node
					if(data.compareTo(get(i + 1)) < 0) {
						return (i+1);
					}
				} else {
					return (i+1);
				}
				
			}
			i++;
		}
		return 0;
	}

	@Override
	public T get(int i) {
		
		//arraylist to add nodes after traversed
		ArrayList<T> A = new ArrayList<>();
		//grab min base case
		T found = getMin();
		//exception
		if(i <= -1) {
			throw new IndexOutOfBoundsException("your message goes here");
		} 
		//else check
		if(i > 0) {
			grabInorder(getRootNode(), A);
			found = (T) A.get(i);
		}
		//return
	    return found;
		
	}
	
	private void grabInorder(BinaryNode<T> binaryNode, ArrayList<T> B)
    {
		if (binaryNode == null) {
			return;
		} else {
            
			//go to the left node
        grabInorder(binaryNode.getLeftChild(), B);
 
        //go to add
       // System.out.print(binaryNode.getData() + "");
        B.add(binaryNode.getData());
        
 
        //go to right ndoe
        grabInorder(binaryNode.getRightChild(), B);
		}
    }

	public boolean isBalanced(int k) {
		
		//checks for baalnced, see binary node for specific info
		
		if(getRootData() == null) {
  			return true;
  		}
  		
  		if(getRootNode().getLeftChild() == null || getRootNode().getRightChild() == null) {
  			return true;
  		}
  		
  		if((Math.abs(getRootNode().getLeftChild().getHeight() - getRootNode().getRightChild().getHeight()) <= k)) {
  			if(getRootNode().getLeftChild().isBalanced(k) && getRootNode().getRightChild().isBalanced(k)) {
  				return true;
  			}
  		}
		return false;	// in height between the left and right subtrees is at most k,
		// and 2) the left and right subtrees are both recursively
		// k-balanced; return false otherwise
	}
	
	// **************************************************
	// Override the methods specified in Assignment 4 below
	// **************************************************
	
	private T getMax(BinaryNode<T> node)
    {
		//checks for farthest right child
        if (node == null){
            return null;
        }
        if (node.getRightChild() == null) {
            return node.getData();
        } else {
            return getMax(node.getRightChild());
        }
    }
	
	private T getMin(BinaryNode<T> node)
    {
		//checks for farthest left child
        if (node == null){
            return null;
        }
        if (node.getLeftChild() == null){
            return node.getData();
        } else {
            return getMin(node.getLeftChild());
        }
    }
} // end BinarySearchTree
