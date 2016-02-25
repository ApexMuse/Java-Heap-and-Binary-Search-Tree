import java.util.Arrays;
import java.util.NoSuchElementException;

public class Heap {
	
	private int heapSize;
	private int[] heapArray;
	
	// Constructor
	public Heap(int maxSize) {
		heapSize = 0;
		heapArray = new int[maxSize + 1];
		Arrays.fill(heapArray, -1);
	}
	
	// Method to determine if heap is empty
	public boolean isEmpty() {
		return heapSize == 0;
	}
	
	// Method to determine if heap is full
	public boolean isFull() {
		return heapSize == heapArray.length;
	}
	
	// Method to find the smallest element
	public int findMin() {
		if(isEmpty()) {
			throw new NoSuchElementException("The heap is empty");
		}
		return heapArray[0];
	}
	
	// Method to delete the smallest element
	public int removeMin() {
		int keyItem = heapArray[0];
		delete(0);
		return keyItem;
	}
	
	// Method to delete an element at a given index
	public int delete(int index) {
		if(isEmpty()) {
			throw new NoSuchElementException("The heap is empty");
		}
		int keyItem = heapArray[index];
		heapArray[index] = heapArray[heapSize - 1];
		heapSize--;
		traverseDown(index);
		return keyItem;
	}
	
	// Method to get the index parent of a node
	private int parent(int i) {
		return (i - 1)/2;
	}
	
	// Method to get the index of the kth child of i
	private int kthChild(int i, int k) {
		return 2 * i + k;
	}
	
	// Method to add an element
	public void addElement(int x) {
		if(isFull()) {
			throw new NoSuchElementException("The Heap is Full");
		}
		// Percolate up the heap
		heapArray[heapSize++] = x;
		traverseUp(heapSize - 1);
	}
	
	// Method to traverse up the heap
    private void traverseUp(int childIndex) {
        int tmp = heapArray[childIndex];    
        while (childIndex > 0 && tmp < heapArray[parent(childIndex)])
        {
            heapArray[childIndex] = heapArray[ parent(childIndex) ];
            childIndex = parent(childIndex);
        }                   
        heapArray[childIndex] = tmp;
    }
    
    // Method to traverse down the heap
    private void traverseDown(int index) {
        int child;
        int temp = heapArray[index];
        while (kthChild(index, 1) < heapSize) {
            child = minChild(index);
            if (heapArray[child] < temp) {
                heapArray[index] = heapArray[child];
            }
            else {
                break;
            }
            index = child;
        }
        heapArray[index] = temp;
    }
    
    // Method to get the smallest child of an element
    private int minChild(int index) 
    {
        int bestChild = kthChild(index, 1);
        int k = 2;
        int pos = kthChild(index, k);
        while ((k <= 2) && (pos < heapSize)) 
        {
            if (heapArray[pos] < heapArray[bestChild]) 
                bestChild = pos;
            pos = kthChild(index, k++);
        }    
        return bestChild;
    }
    
    // Method to print the heap
    public void printHeap() {
    	System.out.print("\nHeap = ");
    	for(int i=0; i<heapSize; i++) {
    		System.out.print(heapArray[i] + " - ");
    	}
    	System.out.println();
    }
    
    
 // Main method
    public static void main(String[] args) throws InterruptedException {
        
    	Heap myHeap = new Heap(30);
        
        /*
         *   Generate 30 random integers and add them to the heap.
         *   Print the heap each time an element is added.
         */
        
        int[] keyArray = new int[30];
        System.out.println("\nAdd 30 random integers to the heap, and");
        System.out.println("print the heap after each element is added");
        System.out.println("------------------------------------------");
        Thread.sleep(3000); // do nothing for 3000 milliseconds (3 seconds)
        int arrayCount = 1;
        for(int i=0; i < keyArray.length; i++) {
        	keyArray[i] = 1 + (int)(Math.random()*50);
        	myHeap.addElement(keyArray[i]);
        	System.out.println("\n\nKey Added: " + keyArray[i] + "\tTotal Keys Added: " + arrayCount + "\n");
        	myHeap.printHeap();
        	arrayCount++;
        	Thread.sleep(2000); // do nothing for 2000 milliseconds (2 seconds)
        }
        System.out.println("\n");
        
        // Find the smallest key
        System.out.println("\nFind the smallest element");
        System.out.println("-----------------------------");
        System.out.println("\nThe smallest element is: " + myHeap.findMin());
        System.out.println("");
        
        // Remove the smallest key
        System.out.println("\nRemove the smallest element");
        System.out.println("-----------------------------");
        System.out.println("Key " + myHeap.removeMin() + " removed\n");
        myHeap.printHeap();
        
    } // End of main() method
	
    
} // End of class Heap