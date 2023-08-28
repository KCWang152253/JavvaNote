package algorithm;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/28 下午2:30
 */
public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

}
