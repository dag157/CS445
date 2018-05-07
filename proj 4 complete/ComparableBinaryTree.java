package MyTreePackage;

public class ComparableBinaryTree<T extends Comparable<? super T>>
extends BinaryTree<T>
implements ComparableTreeInterface<T> {
	
	public ComparableBinaryTree() {
		super();
	}

	@Override
	public T getMax() {
		
		if(isEmpty() == true) {
			//doesn't have any if its empty
			return null;
		} else {
			//travesre if true
			return getMaxTraverse(null, getRootNode());
		}
		
	}
	
	@Override
	public T getMin() {
		
		//same idea
		if(getRootData() == null) {
			return null;
		}
		if(isEmpty() == true) {
			//if empty reutrn null
            return null;
        } else {
        	//else keep going until you dind it
            return getMinTraverse(null, getRootNode());
        }
		
	}

	@Override
	public boolean isBST() {
		
		boolean tree = isBSTTraverse(getRootNode());
		
		return tree;
	}

	@Override
	public int rank(T data) {
		
		if(data == getMin()) {
			//found it return the rank
			return 0;
		} else if (data == getMax()){
			//retirm the max if max
			return (getNumberOfNodes() - 1);
		} else {
			//else traverse it
			return getRankTraverse(data, getRootNode(), 0);
		}
		
		//return 0;
	}
	
	private int getRankTraverse(T greatestValue, BinaryNode<T> node, int counter)
    {
        if(isEmpty() == false) {
        
            if(node.hasLeftChild()) {
            	//return counter
            		counter = getRankTraverse(greatestValue, node.getLeftChild(), counter);
            }
            
            if(node.hasRightChild()) {
            	//return counter
            		counter = getRankTraverse(greatestValue, node.getRightChild(), counter);
            }
            if (greatestValue == null) {
            	//do nothing
            } else if (greatestValue.compareTo(node.getData()) > 0) {
            	//add to counter
            		counter++;
            }
            
        }

        return counter;
    }
	
	public boolean checkEquals(T data) {
		//checsk if values are qeual
		if(getRootNode().getData() == data) {
			return true;
		}
		
		return false;
	}

	@Override
	public T get(int i) {
		T temp = null;
		
		if(i == -1) {
			throw new IndexOutOfBoundsException("out of bounds");
		} else if(i == 0) {
			//get min
			return getMin();
		} else {
			//go through every value
			for(int h = 0; h < i; h++) {
				
				if(h == 0) {
					temp = getTraverse(getMin(), getRootNode(), i, getMax(), getMax());
				} else {
					temp = getTraverse(temp, getRootNode(), i, getMax(), getMax());
				}
			}
			
			
		}
		return temp;
	}
	
	private T getTraverse(T smallestValue, BinaryNode<T> node, int iteratorVal, T carriedVal, T otherValue) {
		
			//checks evrychild
		//othervalue is the previous vlaue we compare to
		//algorithm works by grabinng the smallest and alrgest values
		//it iterates until it finds the second smallest value
		//grabs that value and then we iterate to the next smallest value through a for loop above
		
			if(getRootData() != null) {
				
	            if(node.hasLeftChild()) {
	                carriedVal = getTraverse(smallestValue, node.getLeftChild(), iteratorVal, carriedVal, otherValue);
	            }
	            
	            if(node.hasRightChild()) {
	            		carriedVal = getTraverse(smallestValue, node.getRightChild(), iteratorVal, carriedVal, otherValue);
	            }

	            if (carriedVal == null) {
	            		carriedVal = node.getData();
	            		otherValue = node.getData();
	            } else if (node.getData().compareTo(smallestValue) == 1 && node.getData().compareTo(carriedVal) == -1) {
	            		otherValue = node.getData();
	            		carriedVal = node.getData();
	            		iteratorVal++;
	            }
	            
	        }

	        return carriedVal;
	    
		
		
		
		//return null;
	}

	private T getMaxTraverse(T greatestValue, BinaryNode<T> leaf)
    {
		
		int counter = 0;
		
        if(isEmpty() == false) {
        
        		//checks for elft and right leafs
            if(leaf.hasLeftChild()) {
            		greatestValue = getMaxTraverse(greatestValue, leaf.getLeftChild());
            		counter++;
            }
            
            if(leaf.hasRightChild()) {
            		greatestValue = getMaxTraverse(greatestValue, leaf.getRightChild());
            		counter++;
            }

            //if null, copy value 
            if (greatestValue == null) {
            		System.out.print(""); // works
            		greatestValue = leaf.getData();
            		//else check if the value is less than leafe, if so copy that leaf
            } else if (greatestValue.compareTo(leaf.getData()) == -1) {
            		System.out.print(""); // works
            		greatestValue = leaf.getData();
            }
            
        }

        return greatestValue;
    }
	
	private T getMinTraverse(T smallestValue, BinaryNode<T> leaf)
    {
		
		int counter = 0;
		
		if(getRootData() != null) {
			
			//if has a left go there
            if(leaf.hasLeftChild()) {
            		counter++;
                smallestValue = getMinTraverse(smallestValue, leaf.getLeftChild());
            }
            
            //if right, got here as well
            if(leaf.hasRightChild()) {
            		smallestValue = getMinTraverse(smallestValue, leaf.getRightChild());
            		counter++;
            }

            //if the node is null, grab data
            if (smallestValue == null) {
            		System.out.print(""); // works
            		smallestValue = leaf.getData();
            		//else grab data to see if the current value is greter than the leave
            		//copy if smaller
            } else if (smallestValue.compareTo(leaf.getData()) == 1) {
            		System.out.print(""); // works
            		smallestValue = leaf.getData();
            }
            
        }

        return smallestValue;
    }
	
	private boolean isBSTTraverse(BinaryNode<T> leaf)
    {
		
		System.out.print(""); //cehck my bode data
		
		//checks the case if the final leaf is null, which is good
        if(leaf.getData() == null) {
            return true;
        }

        //checks to see if values are in correct positon with left nodes
        if(leaf.getLeftChild() != null && leaf.getData().compareTo(leaf.getLeftChild().getData()) <= 0) {
            return false;
        }

        //checks if values on right are correct with right nodes.
        if(leaf.getRightChild() != null && leaf.getData().compareTo(leaf.getRightChild().getData()) >= 0) {
            return false;
        }

        //keep iterating
        return ((leaf.getRightChild() == null || isBSTTraverse(leaf.getRightChild()) && leaf.getLeftChild() == null || isBSTTraverse(leaf.getLeftChild())));
    }
}
