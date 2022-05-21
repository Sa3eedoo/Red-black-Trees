package cce;

public class RedBlackTree {
    private Node root;
    private final Node NIL;
    private int numberOfNodes;

    public RedBlackTree() {
        NIL = new Node();
        NIL.color = 0;
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    public void printTreeSize() {
        System.out.println("Tree size: " + getNumberOfNodes());
    }

    public int getHeight() {
        int height;
        height = findHeight(root);
        return height;
    }

    private int findHeight(Node temp) {
        //Check whether tree is empty
        if (root == null) {
            System.out.println("Tree is empty");
            return 0;
        } else {
            int leftHeight = -2, rightHeight = -2;

            //Calculate the height of left subtree
            if (temp.left != null)
                leftHeight = findHeight(temp.left);

            //Calculate the height of right subtree
            if (temp.right != null)
                rightHeight = findHeight(temp.right);

            //Compare height of left subtree and right subtree
            //and store maximum of two in variable max
            int max = Math.max(leftHeight, rightHeight);

            //Calculate the total height of tree by adding height of root
            return (max + 1);
        }
    }

    public void printTreeHeight() {
        System.out.println("Tree Height: " + getHeight());
    }

    private Node searchTree(Node node, String key) {
        if (node == NIL || key.equals(node.data)) {
            return node;
        }

        if (key.compareToIgnoreCase(node.data) < 0) {
            return searchTree(node.left, key);
        }
        return searchTree(node.right, key);
    }

    public Node searchTree(String k) {
        return searchTree(this.root, k);
    }

    public void insert(String key) {
        numberOfNodes++;

        Node node = new Node();
        node.parent = null;
        node.data = key;
        node.left = NIL;
        node.right = NIL;
        node.color = 1; // new node must be red

        Node y = null;
        Node x = this.root;

        while (x != NIL) {
            y = x;
            if (node.data.compareToIgnoreCase(x.data) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data.compareToIgnoreCase(y.data) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }

    private void fixInsert(Node k) {
        Node u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    // case 3.1
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    private void rightRotate(Node x) {
        Node y = x.left;
        x.left = y.right;
        if (y.right != NIL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }
}