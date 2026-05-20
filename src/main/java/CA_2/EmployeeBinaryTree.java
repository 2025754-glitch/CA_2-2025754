/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

import java.util.LinkedList;
import java.util.Queue;

// =============================================================================
// Binary Tree implementation for the Employee Hierarchy.
// Contains two classes: TreeNode (individual node) and EmployeeBinaryTree (the tree).
// =============================================================================

/**
 * Represents a single node in the Employee Hierarchy Binary Tree.
 * Each node stores one Employee object and has references to a left and right child.
 */
class TreeNode {

    // The employee stored at this node
    Employee employee;

    // References to the left and right child nodes (null if no child exists)
    TreeNode left;
    TreeNode right;

    /**
     * Creates a new tree node holding the given employee.
     * Left and right children start as null (no children yet).
     *
     * @param employee the employee to store at this node
     */
    public TreeNode(Employee employee) {
        this.employee = employee;
        this.left  = null;
        this.right = null;
    }
}

// -----------------------------------------------------------------------------


public class EmployeeBinaryTree {

    // The root node of the tree (null if the tree is empty)
    private TreeNode root;

    /**
     * Constructs an empty binary tree with no nodes.
     */
    public EmployeeBinaryTree() {
        this.root = null;
    }

    // =========================================================================
    // INSERT — Level-order (breadth-first) insertion
    // =========================================================================

    
    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        // If the tree is empty, the new employee becomes the root
        if (root == null) {
            root = newNode;
            return;
        }

        // Use a queue to perform a level-order scan of the tree
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // Take the next node from the front

            // Try to place the new node as the left child
            if (current.left == null) {
                current.left = newNode;
                return; // Insertion complete
            } else {
                queue.add(current.left); // Left is taken — add to queue to check later
            }

            // Try to place the new node as the right child
            if (current.right == null) {
                current.right = newNode;
                return; // Insertion complete
            } else {
                queue.add(current.right); // Right is taken — add to queue to check later
            }
        }
    }

    // =========================================================================
    // DISPLAY — Level-order (BFS) traversal
    // =========================================================================

    
    public void displayLevelOrder() {
        // Handle the case where the tree has no nodes
        if (root == null) {
            System.out.println("  [Tree is empty — no records to display]");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int levelNumber = 1;

        while (!queue.isEmpty()) {
            // Count how many nodes are on this level
            int nodesOnThisLevel = queue.size();

            System.out.println("\n  --- Level " + levelNumber + " ---");

            // Process all nodes on the current level
            for (int i = 0; i < nodesOnThisLevel; i++) {
                TreeNode current = queue.poll();
                System.out.println("  " + current.employee.toString());

                // Add children to the queue so they are processed in the next level
                if (current.left  != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            levelNumber++;
        }
    }

    // =========================================================================
    // TREE HEIGHT — Recursive calculation
    // =========================================================================

    /**
     * Returns the height of the tree, defined as the number of levels.
     * An empty tree has height 0. A tree with only a root has height 1.
     *
     * @return the height (number of levels) of the tree
     */
    public int getHeight() {
        return calculateHeight(root);
    }

    /**
     * Recursive helper to calculate the height of a subtree.
     * At each node, the height is 1 plus the maximum height of its two subtrees.
     *
     * @param node the root of the subtree being measured
     * @return the height of this subtree
     */
    private int calculateHeight(TreeNode node) {
        // Base case: a null node contributes 0 to the height
        if (node == null) {
            return 0;
        }

        int leftHeight  = calculateHeight(node.left);
        int rightHeight = calculateHeight(node.right);

        // Height is 1 (this node) + the taller of the two subtrees
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // =========================================================================
    // NODE COUNT — Recursive calculation
    // =========================================================================

    /**
     * Returns the total number of nodes (employees) in the tree.
     *
     * @return the total node count
     */
    public int getNodeCount() {
        return countNodes(root);
    }

    /**
     * Recursive helper to count all nodes in a subtree.
     *
     * @param node the root of the subtree being counted
     * @return the number of nodes in this subtree
     */
    private int countNodes(TreeNode node) {
        // Base case: a null node has no count
        if (node == null) {
            return 0;
        }

        // Count this node (1) plus all nodes in left and right subtrees
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    /**
     * Returns true if the tree contains no nodes.
     *
     * @return true if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }
}
