import java.util.Scanner;

/*
   Node class representing each element of BST
   Each node contains:
   - data
   - reference to left child
   - reference to right child
*/

class Node {

    int data;
    Node left;
    Node right;

    // Constructor
    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}


/*
   BST class containing all operations
*/

class BST {

    Node root;

    // Constructor
    BST() {
        root = null;
    }


    /* ==========================
       INSERT ELEMENT (Non Recursive)
       ========================== */

    void insert(int value) {

        Node newNode = new Node(value);

        // If tree is empty
        if (root == null) {
            root = newNode;
            return;
        }

        Node current = root;
        Node parent = null;

        // Traverse tree to find insertion position
        while (current != null) {

            parent = current;

            if (value < current.data)
                current = current.left;
            else
                current = current.right;
        }

        // Insert node at correct position
        if (value < parent.data)
            parent.left = newNode;
        else
            parent.right = newNode;
    }



    /* ==========================
       SEARCH ELEMENT
       ========================== */

    void search(int key) {

        Node current = root;

        while (current != null) {

            if (key == current.data) {
                System.out.println("Element Found");
                return;
            }

            else if (key < current.data)
                current = current.left;

            else
                current = current.right;
        }

        System.out.println("Element Not Found");
    }



    /* ==========================
       FIND MINIMUM ELEMENT
       ========================== */

    void findMin() {

        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        Node current = root;

        // Minimum is leftmost node
        while (current.left != null)
            current = current.left;

        System.out.println("Minimum element: " + current.data);
    }



    /* ==========================
       FIND MAXIMUM ELEMENT
       ========================== */

    void findMax() {

        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        Node current = root;

        // Maximum is rightmost node
        while (current.right != null)
            current = current.right;

        System.out.println("Maximum element: " + current.data);
    }



    /* ==========================
       DISPLAY BST (Inorder)
       ========================== */

    void display(Node root) {

        if (root != null) {

            display(root.left);
            System.out.print(root.data + " ");
            display(root.right);
        }
    }



    /* ==========================
       DELETE ELEMENT
       ========================== */

    Node delete(Node root, int key) {

        Node parent = null;
        Node current = root;

        // Search node to delete
        while (current != null && current.data != key) {

            parent = current;

            if (key < current.data)
                current = current.left;
            else
                current = current.right;
        }

        if (current == null) {
            System.out.println("Element not found");
            return root;
        }

        // Case 1: Node with two children
        if (current.left != null && current.right != null) {

            Node successor = current.right;
            Node successorParent = current;

            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            current.data = successor.data;
            current = successor;
            parent = successorParent;
        }

        // Case 2 & 3: Node with one child or no child
        Node child;

        if (current.left != null)
            child = current.left;
        else
            child = current.right;

        if (parent == null)
            root = child;

        else if (parent.left == current)
            parent.left = child;

        else
            parent.right = child;

        return root;
    }
}



/*
   Menu Driven Main Program
*/

public class BST_ADT {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BST tree = new BST();

        int ch, value;

        do {

            System.out.println("\n1.Insert");
            System.out.println("2.Delete");
            System.out.println("3.Search");
            System.out.println("4.Find Minimum");
            System.out.println("5.Find Maximum");
            System.out.println("6.Display BST");
            System.out.println("7.Exit");

            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Enter element: ");
                    value = sc.nextInt();
                    tree.insert(value);
                    break;

                case 2:
                    System.out.print("Enter element to delete: ");
                    value = sc.nextInt();
                    tree.root = tree.delete(tree.root, value);
                    break;

                case 3:
                    System.out.print("Enter element to search: ");
                    value = sc.nextInt();
                    tree.search(value);
                    break;

                case 4:
                    tree.findMin();
                    break;

                case 5:
                    tree.findMax();
                    break;

                case 6:
                    System.out.println("BST elements (Inorder): ");
                    tree.display(tree.root);
                    System.out.println();
                    break;

            }

        } while (ch != 7);
    }
}