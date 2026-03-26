import java.util.Scanner;

// Heap class implements Min Heap or Max Heap using an array
class Heap {

    int[] heap;        // Array to store heap elements
    int size;          // Current number of elements in heap
    boolean isMinHeap; // Determines if heap is MinHeap or MaxHeap

    // Constructor initializes heap with given capacity
    Heap(int capacity, boolean isMinHeap) {
        heap = new int[capacity];  // Create array
        size = 0;                  // Initially heap is empty
        this.isMinHeap = isMinHeap;
    }

    // Compare function decides ordering for MinHeap or MaxHeap
    boolean compare(int child, int parent) {

        // For MinHeap: child must be smaller
        if (isMinHeap)
            return child < parent;

        // For MaxHeap: child must be larger
        else
            return child > parent;
    }

    // Swap two elements in the heap array
    void swap(int i, int j) {

        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Insert new element into heap
    void insert(int value) {

        heap[size] = value;   // Insert element at end
        heapifyUp(size);      // Fix heap property by moving upward
        size++;               // Increase heap size
    }

    // Heapify Up (Used after insertion)
    // Moves element upward until heap property is satisfied
    void heapifyUp(int index) {

        while (index > 0) {

            int parent = (index - 1) / 2; // Find parent index

            // If heap property violated, swap child with parent
            if (compare(heap[index], heap[parent])) {

                swap(index, parent);
                index = parent; // Continue checking upward
            }

            else
                break; // Heap property satisfied
        }
    }

    // Heapify Down (Used after deletion or heap creation)
    // Moves element downward until heap property is restored
    void heapifyDown(int index) {

        int target = index;

        while (true) {

            int left = 2 * index + 1;   // Left child index
            int right = 2 * index + 2;  // Right child index

            target = index;

            // Compare with left child
            if (left < size && compare(heap[left], heap[target]))
                target = left;

            // Compare with right child
            if (right < size && compare(heap[right], heap[target]))
                target = right;

            // If a swap is needed
            if (target != index) {

                swap(index, target);
                index = target; // Continue heapifying downward
            }

            else
                break; // Heap property satisfied
        }
    }

    // Delete root element (Min or Max depending on heap type)
    int deleteRoot() {

        // If heap is empty
        if (size == 0)
            return -1;

        int root = heap[0]; // Store root element

        // Move last element to root
        heap[0] = heap[size - 1];

        size--; // Reduce heap size

        // Restore heap property
        heapifyDown(0);

        return root;
    }

    // Create heap using Heapify method
    void createHeap(int[] arr) {

        // Copy elements from input array to heap
        for (int val : arr) {
            heap[size++] = val;
        }

        // Heapify all non-leaf nodes
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapifyDown(i);
        }
    }

    // Perform Heap Sort
    // Repeatedly delete root to produce sorted order
    void heapSort() {

        int originalSize = size;

        System.out.print("Sorted Output: ");

        // Remove root repeatedly
        for (int i = 0; i < originalSize; i++) {
            System.out.print(deleteRoot() + " ");
        }

        System.out.println();
    }

    // Display heap elements
    void display() {

        if (size == 0) {
            System.out.println("Heap is empty");
            return;
        }

        System.out.print("Heap elements: ");

        // Print all elements in heap array
        for (int i = 0; i < size; i++)
            System.out.print(heap[i] + " ");

        System.out.println();
    }
}


// Main class containing menu-driven program
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Heap heap = null; // Heap object initially null

        // Infinite loop for menu
        while (true) {

            System.out.println("\n---- Heap Menu ----");
            System.out.println("1 Create Heap");
            System.out.println("2 Insert Element");
            System.out.println("3 Heap Sort");
            System.out.println("4 Display Heap");
            System.out.println("5 Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                // Create Heap
                case 1:

                    System.out.print("Enter number of elements: ");
                    int n = sc.nextInt();

                    int[] arr = new int[n];

                    System.out.println("Enter elements:");

                    // Input elements
                    for (int i = 0; i < n; i++)
                        arr[i] = sc.nextInt();

                    // Choose heap type
                    System.out.println("1 Min Heap");
                    System.out.println("2 Max Heap");

                    int type = sc.nextInt();

                    // Create heap object
                    heap = new Heap(100, type == 1);

                    // Build heap
                    heap.createHeap(arr);

                    System.out.println("Heap created successfully");

                    break;


                // Insert element
                case 2:

                    if (heap == null) {
                        System.out.println("Create heap first!");
                        break;
                    }

                    System.out.print("Enter element: ");
                    heap.insert(sc.nextInt());

                    break;


                // Heap Sort
                case 3:

                    if (heap == null) {
                        System.out.println("Create heap first!");
                        break;
                    }

                    heap.heapSort();

                    break;


                // Display heap
                case 4:

                    if (heap == null) {
                        System.out.println("Create heap first!");
                        break;
                    }

                    heap.display();

                    break;


                // Exit program
                case 5:

                    System.exit(0);
            }
        }
    }
}