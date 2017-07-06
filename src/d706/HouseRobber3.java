package d706;
import util.TreeNode;

public class HouseRobber3 {
	public int rob(TreeNode root) {
        int[] res = search(root);
        return Math.max(res[0], res[1]);
    }
    
    public int[] search(TreeNode root){
        if(root == null){
            return new int[]{0, 0};
        }
        int[] left = search(root.left);
        int[] right = search(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;//开始写成两个取一个最大再加
        return res;
    }
}
