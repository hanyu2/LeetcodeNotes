package d713;

import java.util.LinkedList;
import java.util.Queue;

import util.TreeNode;

public class SerializeAndDeserializeBinaryTree {
	//BFS, wasted lots of space
	public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null){
            return "";
        }
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while(!q.isEmpty()){
        	int size = q.size();
        	for(int i = 0; i < size; i++){
        		TreeNode n = q.poll();
        		if(n != null){
        			sb.append(n.val + " ");
        			q.offer(n.left);
            		q.offer(n.right);
        		}else{
        			sb.append("# ");
        		}
        	}
        }
        return sb.toString().trim();
    }
   

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0){
            return null;
        }
        String[] str = data.split("\\s+");
        TreeNode root = new TreeNode(Integer.parseInt(str[0]));
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        int index = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node == null){
                    continue;
                }
                if(!str[index].equals("#")){
                    TreeNode left = new TreeNode(Integer.parseInt(str[index]));
                    q.offer(left);
                    node.left = left;
                }else{
                    q.offer(null);
                }
                index++;
                if(!str[index].equals("#")){
                    TreeNode right = new TreeNode(Integer.parseInt(str[index]));
                    q.offer(right);
                    node.right = right;
                }else{
                    q.offer(null);
                }
                index++;
            }
        }
        return root;
    }
    
    //dfs
    public String serialize2(TreeNode root) {
        if(root == null){
        	return "";
        }
        StringBuilder sb = new StringBuilder();
        dfs(root, sb);
        return sb.toString().trim();
    }
    
    public void dfs(TreeNode root, StringBuilder sb){
    	sb.append(root.val + " ");
    	if(root.left != null){
    		dfs(root.left, sb);
    	}else{
    		sb.append("# ");
    	}
    	if(root.right != null){
    		dfs(root.right, sb);
    	}else{
    		sb.append("# ");
    	}
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize2(String data) {
        if(data.length() == 0){
        	return null;
        }
        String[] str = data.split("\\s+");
        int[] index= {0};
        return build(index, str);
    }
    public TreeNode build(int[] index, String[] str){
    	if(str[index[0]].equals("#")){
    		return null;
    	}else{
    		TreeNode node = new TreeNode(Integer.parseInt(str[index[0]]));
    		index[0]++;
    		node.left = build(index, str);
    		index[0]++;
    		node.right = build(index, str);
    		return node;
    	}
    }
    
    public static void main(String[] args) {
    	SerializeAndDeserializeBinaryTree sad = new SerializeAndDeserializeBinaryTree();
		TreeNode n1 = new TreeNode(1);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		n1.left = n2;
		n1.right = n3;
		String data = sad.serialize2(n1);
		TreeNode res = sad.deserialize2(data);
	}
}
