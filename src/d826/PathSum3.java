package d826;

import java.util.ArrayList;
import java.util.List;

import util.TreeNode;

public class PathSum3 {
	int count = 0;

	public int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		search(root, new ArrayList<Integer>(), sum);
		return count;
	}

	public void search(TreeNode root, List<Integer> list, int sum) {
		if (root == null) {
			return;
		}
		List<Integer> temp = new ArrayList<Integer>();
		if(root.val == sum){
			count++;
		}
		for (int n : list) {
			n += root.val;
			if (n == sum) {
				count++;
				System.out.println(root.val);
			}
			temp.add(n);
		}
		temp.add(root.val);
		search(root.left, temp, sum);
		search(root.right, temp, sum);
	}

	public static void main(String[] args) {
		PathSum3 ps3 = new PathSum3();
		TreeNode n1 = new TreeNode(10);
		TreeNode n2 = new TreeNode(5);
		TreeNode n3 = new TreeNode(-3);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(2);
		TreeNode n6 = new TreeNode(11);
		TreeNode n7 = new TreeNode(3);
		TreeNode n8 = new TreeNode(-2);
		TreeNode n9 = new TreeNode(1);
		n1.left = n2;
		n1.right = n3;
		n2.left = n4;
		n2.right = n5;
		n3.right = n6;
		n4.left = n7;
		n4.right = n8;
		n5.right = n9;
		System.out.println(ps3.pathSum(n1, 8));
	}
}
