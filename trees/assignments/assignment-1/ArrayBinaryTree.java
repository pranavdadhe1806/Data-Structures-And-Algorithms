import java.util.*; // Importing Java utility package for Scanner and other utilities


/*
   Custom Stack ADT implementation
   This stack will store indexes of nodes in the binary tree array.
   We are not using Java's built-in Stack class to demonstrate
   manual stack implementation (which is often expected in DS assignments).
*/
class StackADT {

    int arr[] = new int[100]; // Array used to store stack elements
    int top = -1;             // Top pointer of stack (-1 means stack is empty)

    // Push operation: insert element on top of stack
    void push(int x) {
        top++;           // Move top pointer up
        arr[top] = x;    // Store value at new top position
    }

    // Pop operation: remove and return top element
    int pop() {
        return arr[top--];  // Return current top value and decrease top
    }

    // Peek operation: return top element without removing it
    int peek() {
        return arr[top];
    }

    // Check if stack is empty
    boolean isEmpty() {
        return top == -1;
    }
}



/*
   Binary Tree implementation using Array Representation

   Important formulas used:

   If node index = i

   Left Child  = 2 * i
   Right Child = 2 * i + 1
   Parent      = i / 2

   Tree indexing starts from 1 to simplify child calculations.
*/
public class ArrayBinaryTree {

    static int tree[] = new int[100]; // Array used to store tree nodes
    static int n; // Stores number of nodes currently in the tree


    /*
      Create Binary Tree by taking user input

      User enters number of nodes and node values.
      Nodes are stored sequentially in the array.

      Example input:
      1 2 3 4 5 6 7

      Tree representation:

            1
          /   \
         2     3
        / \   / \
       4   5 6   7
    */
    static void createTree(Scanner sc) {

        System.out.print("Enter number of nodes: ");
        n = sc.nextInt(); // Read number of nodes

        System.out.println("Enter elements (-1 for empty node):");

        // Store node values from index 1 to n
        for (int i = 1; i <= n; i++) {
            tree[i] = sc.nextInt();
        }
    }



    /* =====================================================
       RECURSIVE TRAVERSALS
       ===================================================== */


    /*
      Recursive Inorder Traversal

      Order:
      Left -> Root -> Right

      Example output for tree:
      4 2 5 1 6 3 7
    */
    static void inorderRec(int i) {

        // Stop condition
        // if index exceeds number of nodes OR node is empty
        if (i <= n && tree[i] != -1) {

            inorderRec(2 * i); // Visit left child

            System.out.print(tree[i] + " "); // Visit root node

            inorderRec(2 * i + 1); // Visit right child
        }
    }



    /*
      Recursive Preorder Traversal

      Order:
      Root -> Left -> Right
    */
    static void preorderRec(int i) {

        if (i <= n && tree[i] != -1) {

            System.out.print(tree[i] + " "); // Visit root first

            preorderRec(2 * i); // Traverse left subtree

            preorderRec(2 * i + 1); // Traverse right subtree
        }
    }



    /*
      Recursive Postorder Traversal

      Order:
      Left -> Right -> Root
    */
    static void postorderRec(int i) {

        if (i <= n && tree[i] != -1) {

            postorderRec(2 * i); // Traverse left subtree

            postorderRec(2 * i + 1); // Traverse right subtree

            System.out.print(tree[i] + " "); // Visit root last
        }
    }



    /* =====================================================
       NON-RECURSIVE TRAVERSALS USING STACK
       ===================================================== */


    /*
      Non-Recursive Inorder Traversal
      Algorithm:
      1 Start from root
      2 Push nodes while moving left
      3 When leftmost node reached
      4 Pop node from stack
      5 Print node
      6 Move to right child
      7 Repeat until stack empty and no nodes left
    */
    static void inorderNonRec() {

        StackADT stack = new StackADT(); // Create stack to store node indexes
        int ptr = 1; // Pointer starts at root node (index 1)

        /*
           Outer loop runs while:
           - there are nodes left to visit
           OR
           - stack still contains nodes
        */
        while (ptr <= n || !stack.isEmpty()) {

            /*
               Inner loop moves down the left subtree
               pushing nodes into stack.
            */
            while (ptr <= n && tree[ptr] != -1) {

                stack.push(ptr); // Store current node index
                ptr = 2 * ptr;   // Move to left child
            }

            // Leftmost node reached -> pop from stack
            ptr = stack.pop();

            // Visit the node
            System.out.print(tree[ptr] + " ");

            // Move to right child
            ptr = 2 * ptr + 1;
        }
    }



    /*
      Non-Recursive Preorder Traversal

      Preorder order:
      Root -> Left -> Right

      Algorithm used here:

      1 Visit node
      2 Push node to stack
      3 Move left
      4 When no left child -> pop and move right
    */
    static void preorderNonRec() {

        StackADT stack = new StackADT();
        int ptr = 1; // Start from root

        while (ptr <= n || !stack.isEmpty()) {

            // Traverse left subtree
            while (ptr <= n && tree[ptr] != -1) {

                System.out.print(tree[ptr] + " "); // Visit node

                stack.push(ptr); // Store node to return later

                ptr = 2 * ptr; // Move to left child
            }

            // Backtrack
            ptr = stack.pop();

            // Move to right child
            ptr = 2 * ptr + 1;
        }
    }



    /*
      Non-Recursive Postorder Traversal

      Uses two stacks.

      stack1 -> traversal
      stack2 -> reverse order storage

      Steps:

      1 Push root to stack1
      2 Pop from stack1 and push into stack2
      3 Push children into stack1
      4 Repeat
      5 Finally pop stack2 to get correct order
    */
    static void postorderNonRec() {

        StackADT s1 = new StackADT(); // First stack
        StackADT s2 = new StackADT(); // Second stack

        s1.push(1); // Push root node index

        while (!s1.isEmpty()) {

            int ptr = s1.pop(); // Remove node from stack1

            s2.push(ptr); // Push node into stack2

            // Push left child to stack1
            if (2 * ptr <= n && tree[2 * ptr] != -1)
                s1.push(2 * ptr);

            // Push right child to stack1
            if (2 * ptr + 1 <= n && tree[2 * ptr + 1] != -1)
                s1.push(2 * ptr + 1);
        }

        // stack2 now contains nodes in reverse postorder
        while (!s2.isEmpty()) {

            System.out.print(tree[s2.pop()] + " ");
        }
    }



    /* =====================================================
       COUNT TERMINAL (LEAF) NODES
       ===================================================== */


    /*
      Leaf Node = node with no children

      Condition:
      left child absent AND right child absent
    */
    static void countLeaves() {

        int count = 0;

        for (int i = 1; i <= n; i++) {

            // Check if node exists
            if (tree[i] != -1) {

                int left = 2 * i;      // index of left child
                int right = 2 * i + 1; // index of right child

                /*
                   If both children do not exist
                   then this node is a leaf node
                */
                if ((left > n || tree[left] == -1) &&
                    (right > n || tree[right] == -1)) {

                    count++;
                }
            }
        }

        System.out.println("Number of terminal nodes: " + count);
    }



    /* =====================================================
       MAIN METHOD (MENU DRIVEN PROGRAM)
       ===================================================== */


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Scanner for user input
        int choice; // Menu choice variable

        do {

            // Display menu options
            System.out.println("\n---- Binary Tree Menu ----");
            System.out.println("1. Create Tree");
            System.out.println("2. Inorder Traversal");
            System.out.println("3. Preorder Traversal");
            System.out.println("4. Postorder Traversal");
            System.out.println("5. Count Terminal Nodes");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    createTree(sc); // Create binary tree
                    break;

                case 2:
                    System.out.print("Recursive Inorder: ");
                    inorderRec(1); // Start traversal from root
                    System.out.println();

                    System.out.print("Non Recursive Inorder: ");
                    inorderNonRec();
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Recursive Preorder: ");
                    preorderRec(1);
                    System.out.println();

                    System.out.print("Non Recursive Preorder: ");
                    preorderNonRec();
                    System.out.println();
                    break;

                case 4:
                    System.out.print("Recursive Postorder: ");
                    postorderRec(1);
                    System.out.println();

                    System.out.print("Non Recursive Postorder: ");
                    postorderNonRec();
                    System.out.println();
                    break;

                case 5:
                    countLeaves(); // Display number of leaf nodes
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }

        } while (choice != 6); // Repeat until user chooses exit

        sc.close(); // Close scanner
    }
}