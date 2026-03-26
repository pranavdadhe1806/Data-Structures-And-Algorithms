import java.util.*;

class AVLNode {
    String key;
    int height;
    AVLNode left, right, parent;

    AVLNode(String key) {
        this.key = key;
        height = 1;
        left = right = parent = null;
    }
}

class AVLTree {

    AVLNode root;

    int height(AVLNode n) {
        if (n == null)
            return 0;
        return n.height;
    }

    int getBalance(AVLNode n) {
        if (n == null)
            return 0;
        return height(n.left) - height(n.right);
    }

    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    AVLNode rightRotate(AVLNode y) {

        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        if (T2 != null)
            T2.parent = y;

        x.parent = y.parent;
        y.parent = x;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {

        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        if (T2 != null)
            T2.parent = x;

        y.parent = x.parent;
        x.parent = y;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, String key) {

        if (node == null)
            return new AVLNode(key);

        if (key.compareToIgnoreCase(node.key) < 0) {
            node.left = insert(node.left, key);
            node.left.parent = node;
        }

        else if (key.compareToIgnoreCase(node.key) > 0) {
            node.right = insert(node.right, key);
            node.right.parent = node;
        }

        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareToIgnoreCase(node.left.key) < 0)
            return rightRotate(node);

        if (balance < -1 && key.compareToIgnoreCase(node.right.key) > 0)
            return leftRotate(node);

        if (balance > 1 && key.compareToIgnoreCase(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareToIgnoreCase(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    AVLNode minValueNode(AVLNode node) {
        AVLNode current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    AVLNode deleteNode(AVLNode root, String key) {

        if (root == null)
            return root;

        if (key.compareToIgnoreCase(root.key) < 0)
            root.left = deleteNode(root.left, key);

        else if (key.compareToIgnoreCase(root.key) > 0)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                AVLNode temp = null;

                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    root = null;
                } else
                    root = temp;
            }

            else {

                AVLNode temp = minValueNode(root.right);

                root.key = temp.key;

                root.right = deleteNode(root.right, temp.key);
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.key + " ");
            inorder(node.right);
        }
    }

    void preorder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    void postorder(AVLNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.key + " ");
        }
    }
}

public class AVL {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AVLTree tree = new AVLTree();

        while (true) {

            System.out.println("\n--- AVL Tree Menu ---");
            System.out.println("1.Insert");
            System.out.println("2.Delete");
            System.out.println("3.Inorder Traversal");
            System.out.println("4.Preorder Traversal");
            System.out.println("5.Postorder Traversal");
            System.out.println("6.Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {

                case 1:
                    System.out.print("Enter Month: ");
                    String key = sc.nextLine();
                    tree.root = tree.insert(tree.root, key);
                    break;

                case 2:
                    System.out.print("Enter Month to Delete: ");
                    String del = sc.nextLine();
                    tree.root = tree.deleteNode(tree.root, del);
                    break;

                case 3:
                    System.out.println("Inorder:");
                    tree.inorder(tree.root);
                    break;

                case 4:
                    System.out.println("Preorder:");
                    tree.preorder(tree.root);
                    break;

                case 5:
                    System.out.println("Postorder:");
                    tree.postorder(tree.root);
                    break;

                case 6:
                    System.exit(0);
            }
        }
    }
}
