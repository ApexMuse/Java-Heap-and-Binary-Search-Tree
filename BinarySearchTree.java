

public class BinarySearchTree {
	
    public Node root;

    // Create an empty Binary Search Tree
    public BinarySearchTree() {
        this.root = null;
    }

    // Add a node to the tree
    void addNode(Node node, int value) {
    	// If the tree is empty, set the new node to root
        if (node == null) {
            node = new Node(value);
            root = node;
        }
        /* 
         *   If the new node's key value is less than the current node's
         *   key value and the left child is empty, set the new node to the
         *   left child
         */
        else if (value < node.key && node.leftChild == null) {
            node.leftChild = new Node(value);
            node.leftChild.parent = node;
        }
        /* 
         *   If the new node's key value is greater than or equal to 
         *   the current node's key value and the right child is empty, 
         *   set the new node to the right child
         */
        else if (value >= node.key && node.rightChild == null) {
            node.rightChild = new Node(value);
            node.rightChild.parent = node;
        }
        else {
            /* 
             *   If the new node's key value is less than 
             *   the current node's key value, use recursion to call this
             *   method on the left child.  Otherwise call it on the 
             *   right child.
             */ 
            if (value < node.key) {
                addNode(node.leftChild, value);
            }
            else {
                addNode(node.rightChild, value);
            }
        }
    } // End of addNode() method
    

    // Search for a specific node key recursively
    public boolean search(Node node, int value) {
    	// If the tree is empty, return false
        if (node == null) {
            return false;
        }
        // If the key is found, return true
        else if (node.key == value) {
            return true;
        }
        /*
         *   If the value is less than the current node's key,
         *   use recursion to call this method on the left child.
         *   Otherwise call it on the right child.
         */
        else {
            if (value < node.key) {
                return search(node.leftChild, value);
            }
            else {
                return search(node.rightChild, value);
            }
        }
    } // End of search() method
    
    
 // Use recursion to find the min node key
    public static int findMin(Node node) {
    	if(node.leftChild == null) {
    		return node.key;
    	}
    	else {
    		return findMin(node.leftChild);
    	}
    }
    
    
    // Use recursion to find the max node key
    public static int findMax(Node node) {
    	if(node.rightChild == null) {
    		return node.key;
    	}
    	else {
    		return findMax(node.rightChild);
    	}
    }
    
    
    public boolean deleteNode(int value) {
    	
    	// Start at root
    	Node current = root;
    	Node parent = root;
    	boolean isLeftChild = true;
    	
    	while(current.key != value) {
    		parent = current;
    		/*
    		 *   If the value is less the current Node's key,
    		 *   search to the left.  Otherwise search right.
    		 */
    		if(value < current.key) {
    			current = current.leftChild;
    			isLeftChild = true;
    		}
    		else {
    			current = current.rightChild;
    			isLeftChild = false;
    		}
    		// Key not found
    		if(current == null) {
        		return false;
        	}
    	} // End of while loop
    	
    	// If there are no children
    	if(current.leftChild == null && current.rightChild == null) {
    		// If the node is root, delete root
    		if(current == root){
    			root = null;
    		}
    		// If it is a left child
    		else if(isLeftChild) {
    			parent.leftChild = null;
    		}
    		else {
    			parent.rightChild = null;
    		}
    	}
    	// If there is no right child
    	else if(current.rightChild == null) {
    		// If it is the root node, move the left child up
    		if(current == root) {
    			root = current.leftChild;
    		}
    		// If it is a left child, move its left child up
    		else if(isLeftChild) {
    			parent.leftChild = current.leftChild;
    		}
    		else {
    			parent.rightChild = current.leftChild;
    		}
    	}
    	// If there is no left child
    	else if(current.leftChild == null) {
    		if(current == root) {
    			root = current.rightChild;
    		}
    		else if(isLeftChild) {
    			parent.leftChild = current.rightChild;
    		}
    		else {
    			parent.rightChild = current.rightChild;
    		}
    	}
    	// If there are two children
    	else {
    		Node replacementNode = getReplacement(current);
    		
    		/*  If the current node is root, replace root with
    		 *  the replacement node
    		 */
    		if(current == root) {
    			root = replacementNode;
    		}
    		/*  If the deleted node was a left child, make the 
    		 *  replacement node the left child.  Otherwise make
    		 *  it the right child.
    		 */
    		else if(isLeftChild) {
    			parent.leftChild = replacementNode;
    		}
    		else {
    			parent.rightChild = replacementNode;
    		}
    		
    		replacementNode.leftChild = current.leftChild;
    	}
    	
    	return true;
    	
    } // End of deleteNode() method
    
    public Node getReplacement(Node node) {
    	Node nodeParent = node;
    	Node replacement = node;
    	Node current = node.rightChild;
    	
    	while (current != null) {
    		nodeParent = replacement;
    		replacement = current;
    		current = current.leftChild;
    	}
    	/*  If the replacement is not the right child, move the
    	 *  replacement into the parent's left child position and 
    	 *  move the replaced node's right child into the replacement's
    	 *  right child
    	 */
    	if(replacement != node.rightChild) {
    		nodeParent.leftChild = replacement.rightChild;
    		replacement.rightChild = node.rightChild;
    	}
    	return replacement;
    }
    
    
    // Print using In-order traversal
    public void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.leftChild);
            System.out.print(node.key + " - ");
            printInOrder(node.rightChild);
        }
    }

    /*
    // Print using Post-order traversal
    public void printPostOrder(Node node)
    {
        if (node != null)
        {
            printPostOrder(node.leftChild);
            printPostOrder(node.rightChild);
            System.out.print(node.key + " - ");
        }
    }

    // Print using Pre-order traversal
    public void printPreOrder(Node node)
    {
        if (node != null)
        {
            System.out.print(node.key + " - ");
            printPreOrder(node.leftChild);
            printPreOrder(node.rightChild);
        }
    }
    */

    // Main method
    public static void main(String[] args) throws InterruptedException {
        BinarySearchTree myTree = new BinarySearchTree();
        
        /*
         *   Generate 30 random integers and assign them to an array.
         *   Print the array and add each value to the Binary Search Tree.
         */
        
        int[] keyArray = new int[30];
        System.out.println("\nAdd 30 random integers to the tree, and print");
        System.out.println("the In-order traversal after each node is added");
        System.out.println("------------------------------------------------");
        Thread.sleep(2000); // do nothing for 2000 milliseconds (2 seconds)
        int arrayCount = 1;
        for(int i=0; i < keyArray.length; i++) {
        	keyArray[i] = 1 + (int)(Math.random()*50);
        	myTree.addNode(myTree.root, keyArray[i]);
        	System.out.println("\n\nKEY ADDED: " + keyArray[i] + "\t# OF NODES: " + arrayCount + "\n");
        	myTree.printInOrder(myTree.root);
        	arrayCount++;
        	Thread.sleep(2000); // do nothing for 2000 milliseconds (2 seconds)
        }
        System.out.println("\n");
        
        // Print the smallest and largest keys
        System.out.println("\n\nThe smallest key is: " + findMin(myTree.root));
        System.out.println("The largest key is: " + findMax(myTree.root));
        System.out.println("");
        
        /* // Print the Binary Search Tree using Post-order traversal
         * myTree.printPostOrder(myTree.root);
         * System.out.println("");
         */
        
        /* // Print the Binary Search Tree using Pre-order traversal
         * myTree.printPreOrder(myTree.root);
         * System.out.println("");
         */

        // Search the tree for random key values
        System.out.println("\nRandom key search");
        System.out.println("-------------------");
        int[] randArray = new int[5];
        for(int i=0; i<randArray.length; i++) {
        	randArray[i] = 1 + (int)(Math.random()*50);
        	System.out.println(myTree.search(myTree.root, randArray[i]) ? "Key " + randArray[i] + " Found" : "Key " + randArray[i] + " Not Found");
        }
        
        System.out.println("\nDelete the nodes with the min and max keys");
        System.out.println("--------------------------------------------");
        int min = findMin(myTree.root);
        int max = findMax(myTree.root);
        System.out.println(myTree.deleteNode(min) ? "Key " + min + " Deleted" : "Key " + min + " Not Found");
        System.out.println(myTree.deleteNode(max) ? "Key " + max + " Deleted" : "Key " + max + " Not Found");
        
        // Delete random nodes
        int delKey;
        int[] delArray = new int[3];
        System.out.println("\nDelete random nodes");
        System.out.println("---------------------");
        for(int i=0; i<delArray.length; i++) {
        	delArray[i] = 1 + (int)(Math.random()*30);
        	delKey = keyArray[delArray[i]];
            System.out.println(myTree.deleteNode(delKey) ? "Key " + delKey + " Deleted" : "Key " + delKey + " Not Found");
        }
        
        // Print the Binary Search Tree using In-order traversal
        System.out.println("\nIn-order Traversal after deletions");
        System.out.println("--------------------");
        myTree.printInOrder(myTree.root);
        System.out.println("");
        
    } // End of main() method
} // End of class BinarySearchTree

class Node {
    int key;
    Node parent;
    Node leftChild;
    Node rightChild;
    
    public Node(){
    	this.key = 0;
    	this.parent = null;
    	this.leftChild = null;
        this.rightChild = null;
    }

    public Node(int key) {
        this.key = key;
        this.parent = null;
        this.leftChild = null;
        this.rightChild = null;
    }
} // End of Node class
