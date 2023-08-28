package algorithm;

import java.util.*;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/21 上午10:20
 * <p>
 * <p>
 * 1. 遍历过程
 * <p>
 * 递归：若二叉树为空，则空操作返回，否则先访问根结点，然后前序遍历左子树，再前序遍历右子树。
 * 非递归：可以使用一个栈来模拟这种操作。（利用回溯思想）
 * 若二叉树为空，则空操作返回，
 * 当根节点（root）与栈（stack）同时不为空，判断 root 是否为空，
 * 不为空：输出 root.val，将 root 压栈，并将 root 指向 root.left
 * 为空：让 root 指向 stack.pop() ，并将 root 指向 root.right
 */
public class Tree {


    public static void main(String[] args) {
        //            1
        //              \
        //               2
        //              /
        //             3
        //pre->123  in->132   post->321  level->123
        TreeNode<Integer> root = new TreeNode<>(1);
        root.right = new TreeNode<>(2);
        root.right.left = new TreeNode<>(3);

        List<Integer> list_preorderRecursively = preorderRecursively(root);
        System.out.print("前序遍历递归版-preorderRecursively: " + '\t');
        System.out.println(list_preorderRecursively.toString());

        List<Integer> list_inorderRecursively = inorderRecursively(root);
        System.out.print("中序遍历递归版-inorderRecursively: " + '\t');
        System.out.println(list_inorderRecursively.toString());

        List<Integer> list_postorderRecursively = postorderRecursively(root);
        System.out.print("后序遍历递归版-postorderRecursively: " + '\t');
        System.out.println(list_postorderRecursively.toString());
        System.out.println();


        List<Integer> list_preorderIteratively = preorderIteratively(root);
        System.out.print("前序遍历非递归版-preorderIteratively: " + '\t');
        System.out.println(list_preorderIteratively.toString());

        List<Integer> list_inorderIteratively = inorderIteratively(root);
        System.out.print("中序遍历非递归版-inorderIteratively: " + '\t');
        System.out.println(list_inorderIteratively.toString());

        List<Integer> list_postorderIteratively = postorderIteratively(root);
        System.out.print("后序遍历非递归版-postorderIteratively: " + '\t');
        System.out.println(list_postorderIteratively.toString());
        System.out.println();

        List<Integer> list_levelorder = levelorder(root);
        System.out.print("层序遍历-levelorder: " + '\t');
        System.out.println(list_levelorder.toString());

    }

    /**
     * 前序遍历递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> preorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        list.add(node.val);
        list.addAll(preorderRecursively(node.left));
        list.addAll(preorderRecursively(node.right));
        return list;
    }

    /**
     * 中序遍历递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> inorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        list.addAll(inorderRecursively(node.left));
        list.add(node.val);
        list.addAll(inorderRecursively(node.right));
        return list;
    }

    /**
     * 后序遍历递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> postorderRecursively(TreeNode<Integer> node) {
        List<Integer> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        list.addAll(postorderRecursively(node.left));
        list.addAll(postorderRecursively(node.right));
        list.add(node.val);
        return list;
    }

    /**
     * 前序遍历非递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> preorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        if (node == null) {
            return list;
        }
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                list.add(cur.val);
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop().right;
            }
        }
        return list;
    }

    /**
     * 中序遍历非递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> inorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                list.add(stack.peek().val);
                cur = stack.pop().right;
            }
        }
        return list;
    }

    /**
     * 后序遍历非递归版
     *
     * @param node
     * @return
     */
    public static List<Integer> postorderIteratively(TreeNode<Integer> node) {
        //stack栈顶元素永远为cur的父节点
        //prevVisted用于区分是从左子树还是右子树返回的
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> cur = node;
        TreeNode<Integer> prevVisted = null;
        List<Integer> list = new LinkedList<>();
        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.peek().right;
                if (cur != null && cur != prevVisted) {
                    stack.push(cur);
                    cur = cur.left;
                } else {
                    prevVisted = stack.pop();
                    list.add(prevVisted.val);
                    cur = null;
                }
            }
        }
        return list;
    }

    /**
     * 层序遍历
     *
     * @param node
     * @return
     */
    public static List<Integer> levelorder(TreeNode<Integer> node) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        List<Integer> list = new LinkedList<>();
        TreeNode<Integer> temp = null;
        if (node == null) {
            return list;
        }
        queue.add(node);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            list.add(temp.val);
            if (temp.left != null) {
                queue.offer(temp.left);
            }
            if (temp.right != null) {
                queue.offer(temp.right);
            }
        }
        return list;
    }
}

