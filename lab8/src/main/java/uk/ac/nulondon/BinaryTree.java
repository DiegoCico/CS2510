package uk.ac.nulondon;

public class BinaryTree {

    private Node root;

    /**
     * Problem 1: Mystery Function
     * @return the return value
     */
    private int mysteryFunc(int d) {
        return mysteryFunc(root, d);
    }

    private int mysteryFunc(Node r, int d) {
        if (r == null) return 0;
        int k = r.value > d ? 1 : 0;
        return k + mysteryFunc(r.left, d) + mysteryFunc(r.right,d);
    }

    /**
     * Problem 2: Search for a value in a binary search tree (BST)
     * @param value the value we're looking for
     * @return true if the value is in the tree, false if not
     */
    private boolean searchBST(int value) {
        return searchBST(root, value);
    }

    private boolean searchBST(Node root, int value) {
        //TODO: Your code here.
        return false;
    }

    /**
     * Problem 3: Add a node to a binary search tree (BST)
     * @return the new node
     */
    private Node insertBST(Node root, int value) {
        //TODO: Your code here
        return root;
    }

    /**
     * Problem 4: Invert a binary tree
     * @param root the starting node
     * @return the root of the tree
     */
    private Node invertTree(Node root) {
        //TODO: Your code here
        return root;
    }

    /**
     * Problem 5: pathSum()
     * @param root the starting node/where all paths will start from
     * @param targetSum the sum we're looking for
     * @return true if a path exists that adds up to targetSum, false if not
     */
    public boolean hasPathSum(Node root, int targetSum) {
        //TODO: Your code here
        return false;
    }

    public String toString() {
        return traversePreOrder(this.root);
    }

    /**
     * Use preorder traversal (left subtree first, then right) to print the tree.
     * @param root the root node
     * @return tree values in a string
     */
    public String traversePreOrder(Node root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(root.value);

        String pointerRight = "└──";
        String pointerLeft = (root.right != null) ? "├──" : "└──";

        traverseNodes(sb, "", pointerLeft, root.left, root.right != null);
        traverseNodes(sb, "", pointerRight, root.right, false);

        return sb.toString();
    }

    private void traverseNodes(StringBuilder sb, String padding, String pointer, Node node, boolean hasRightSibling) {
        if (node != null) {
            sb.append("\n");
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);

            StringBuilder paddingBuilder = new StringBuilder(padding);
            if (hasRightSibling) {
                paddingBuilder.append("│  ");
            } else {
                paddingBuilder.append("   ");
            }

            String paddingForBoth = paddingBuilder.toString();
            String pointerRight = "└──";
            String pointerLeft = (node.right != null) ? "├──" : "└──";

            traverseNodes(sb, paddingForBoth, pointerLeft, node.left, node.right != null);
            traverseNodes(sb, paddingForBoth, pointerRight, node.right, false);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(0);

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        root.setLeft(node1);
        root.setRight(node2);

        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.setLeft(node3);
        node1.setRight(node4);

        node2.setLeft(new Node(5));
        node2.setRight(new Node(6));

        Node node7 = new Node(7);
        node3.setLeft(node7);
        node7.setLeft(new Node(8));
        node7.setRight(new Node(9));

        BinaryTree bt = new BinaryTree();
        System.out.println(bt.traversePreOrder(root));

        // 1 -> 3 -> 7 -> 9
        System.out.println(bt.hasPathSum(root, 20));

        bt.invertTree(root);
        System.out.println(bt.traversePreOrder(root));
    }
}



