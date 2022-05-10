package com.reservation;

public class BinarySearchTree<T> {

    public static class Node<T> {
        int key;
        T element;
        Node<T> left;
        Node<T> right;

        public Node(int key, T element) {
            this.key = key;
            this.element = element;
            this.left = null;
            this.right = null;
        }
    }

    public Node<T> root;

    public BinarySearchTree() {
        root = null;
    }

    public void insertNode(int key, T element) {
        Node<T> newNode = new Node<>(key, element);

        if (root == null) {
            root = newNode;
            return;
        }
        Node<T> current = root, parent;
        while (true) {
            parent = current;

            if (key < current.key) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return;
                }
            }
        }
    }

    public Node<T> searchNode(Node<T> node, int key) {
        if (node == null || node.key == key) {
            return node;
        }
        if (node.key < key)
            return searchNode(node.right, key);

        return searchNode(node.left, key);
    }

    public Node<T> deleteNode(Node<T> node, int key) {
        if(node == null){
            return null;
        } else {
            if(key < node.key) {
                node.left = deleteNode(node.left, key);
            } else if(key > node.key) {
                node.right = deleteNode(node.right, key);
            } else {
                if(node.left == null && node.right == null) {
                    node = null;
                } else if(node.left == null) {
                    node = node.right;
                } else if(node.right == null) {
                    node = node.left;
                } else {
                    Node<T> temp = minNode(node.right);
                    node.key = temp.key;
                    node.right = deleteNode(node.right, temp.key);
                }
            }
            return node;
        }
    }

    public void inOrderTraversal(Node<Ticket> node) {
        if(node.left != null)
            inOrderTraversal(node.left);
        ReservationUtil.displayTicketDetails(node.key, node.element);
        if(node.right != null)
            inOrderTraversal(node.right);
    }

    private Node<T> minNode(Node<T> root) {
        if (root.left != null)
            return minNode(root.left);
        else
            return root;
    }
}
