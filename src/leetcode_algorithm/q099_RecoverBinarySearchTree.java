package leetcode_algorithm;

import java.util.Stack;

/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * <p>
 * Recover the tree without changing its structure.
 * <p>
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
 */

public class q099_RecoverBinarySearchTree {


    public static void main(String[] args) {
        q099_RecoverBinarySearchTree solution = new q099_RecoverBinarySearchTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        solution.recoverTree(t1);
    }


    /**
     * �ⷨ1 ���õݹ�
     *
     * @param root
     */
    public void recoverTree(TreeNode root) {
        traverse(root);
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        //�ҵ���һ��Ҫ������Ԫ��
        if (firstElement == null && prevElement.val >= root.val) {
            firstElement = prevElement;
        }
        //�ҵ��ڶ���Ҫ������Ԫ��
        if (firstElement != null && prevElement.val >= root.val) {
            secondElement = root;
        }
        prevElement = root;
        traverse(root.right);
    }

    /**
     * �ⷨ2 �ǵݹ��㷨
     *
     * @param root
     */
    public void recoverTree2(TreeNode root) {
        TreeNode first = null;
        TreeNode second = null;
        TreeNode curr = root;
        TreeNode prev = null;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || curr != null) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (prev != null && curr.val <= prev.val) {
                    if (first == null) first = prev;
                    second = curr;
                }
                //�����ҽڵ�
                prev = curr;
                curr = curr.right;
            }
        }

        //recover swapped nodes
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private final static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}
