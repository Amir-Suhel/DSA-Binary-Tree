package com.binary.tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeImplementation {

	static class Node {
		int data;
		Node left;
		Node right;

		public Node(int data) {
			this.data = data;
			this.left = null;
			this.right = null;
		}

	}

	static class BinaryTree {
		static int idx = -1;

		/**
		 * Build a binary tree
		 */
		static Node buildTree(int nodes[]) {
			idx++;
			if (nodes[idx] == -1) {
				return null;
			}
			Node node = new Node(nodes[idx]);
			node.left = buildTree(nodes);
			node.right = buildTree(nodes);

			return node;
		}

		/**
		 * Preorder Traversal
		 */
		public static void preorder(Node root) {
			if (root == null) {
				return;
			}
			System.out.print(root.data + " ");
			preorder(root.left);
			preorder(root.right);
		}

		/**
		 * Inorder Traversal
		 */
		public static void inorder(Node root) {
			if (root == null) {
				return;
			}
			inorder(root.left);
			System.out.print(root.data + " ");
			inorder(root.right);
		}

		/**
		 * Postorder Traversal
		 */
		public static void postorder(Node root) {
			if (root == null) {
				return;
			}
			postorder(root.left);
			postorder(root.right);
			System.out.print(root.data + " ");
		}

		/**
		 * Level Order Traversal
		 */
		public static void levelOrder(Node root) {
			if (root == null) {
				System.out.println("Tree is empty!!");
				return;
			}
			Queue<Node> queue = new LinkedList<>();
			queue.add(root);
			queue.add(null);

			while (!queue.isEmpty()) {

				Node node = queue.remove();
				if (node == null) {
					System.out.println();
					if (queue.isEmpty()) {
						break;
					} else {
						queue.add(null);
					}
				} else {
					System.out.print(node.data + " ");
					if (node.left != null) {
						queue.add(node.left);
					}
					if (node.right != null) {
						queue.add(node.right);
					}
				}
			}
		}

		/**
		 * Count of nodes
		 */
		public static int count(Node root) {
			if (root == null) {
				return 0;
			}

			int leftNodes = count(root.left);
			int rightNodes = count(root.right);

			return 1 + leftNodes + rightNodes;

			// return 1 + count(root.left) + count(root.right);
		}

		/**
		 * Sum of nodes
		 */
		public static int sumNodes(Node root) {
			if (root == null) {
				return 0;
			}

			int leftSum = sumNodes(root.left);
			int rightSum = sumNodes(root.right);

			return root.data + leftSum + rightSum;
		}

		/**
		 * Height of Binary Tree
		 */
		public static int height(Node root) {
			if (root == null) {
				return 0;
			}

			int leftHeight = height(root.left);
			int rightHeight = height(root.right);

			return 1 + Math.max(leftHeight, rightHeight);
		}

		/**
		 * Diameter of Binary Tree - T(n) = O(n^2)
		 */
		public static int diameter(Node root) {
			if (root == null) {
				return 0;
			}

			int leftDiameter = diameter(root.left);
			int rightDiameter = diameter(root.right);
			int height = height(root.left) + height(root.right) + 1;

			return Math.max(height, Math.max(leftDiameter, rightDiameter));
		}

		/**
		 * Diameter of Binary Tree - T(n) = O(n)
		 */
		public static TreeInfo diameter2(Node root) {
			if (root == null) {
				return new TreeInfo(0, 0);
			}

			TreeInfo left = diameter2(root.left);
			TreeInfo right = diameter2(root.right);

			int height = Math.max(left.ht, right.ht) + 1;

			int diam1 = left.diam;
			int diam2 = right.diam;
			int diam3 = left.ht + right.ht + 1;

			int diameter = Math.max(Math.max(diam1, diam2), diam3);

			return new TreeInfo(height, diameter);

		}

	}

	/**
	 * This class is using to add height and diameter at the same time so that time
	 * complexity could be T(n) = O(n)
	 */
	static class TreeInfo {
		int ht;
		int diam;

		TreeInfo(int ht, int diam) {
			this.ht = ht;
			this.diam = diam;
		}
	}

	public static void main(String[] args) {
		int[] nodes = { 1, 2, 4, -1, -1, 5, -1, -1, 3, -1, 6, -1, -1 };

		BinaryTree tree = new BinaryTree();
		Node root = BinaryTree.buildTree(nodes);

		System.out.println("Preorder Traversal: ");
		BinaryTree.preorder(root);

		System.out.println();
		System.out.println("Inorder Traversal: ");
		tree.inorder(root);

		System.out.println();
		System.out.println("Postorder Traversal: ");
		tree.postorder(root);

		System.out.println();
		System.out.println("Level Order Traversal: ");
		tree.levelOrder(root);

		System.out.println();
		System.out.println("Number of nodes: " + tree.count(root));

		System.out.println();
		System.out.println("Sum of nodes: " + tree.sumNodes(root));

		System.out.println();
		System.out.println("Height of binary tree: " + tree.height(root));

		System.out.println();
		System.out.println("Diameter of binary tree with T(n) = O(n^2) : " + tree.diameter(root));

		System.out.println();
		System.out.println("Diameter of binary tree with T(n) = O(n) : " + tree.diameter2(root).diam);

	}

}
