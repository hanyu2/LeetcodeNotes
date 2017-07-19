package d718;

public class RemoveBoxes {
	//开始想的是搜索，每一层找到连续的没用过的，计算本层得分
	//把用完的标记为使用过，传到下一层，在标记未使用
	//计算连续相同字符数字个数比较麻烦，要记录他们的index，用完还要还原
	//计算最后一个字符还要注意在for外面再计算一次
	int max = 0;
    public int removeBoxes(int[] boxes) {
        boolean[] used = new boolean[boxes.length];
        search(boxes, used, 0);
        return max;
    }
    public void search(int[] boxes, boolean[] used, int score){
    	if(allUsed(used)){
    		max = Math.max(max, score);
    		return;
    	}
    	int last = -1;
    	int len = 0;
    	for(int i = 0; i < boxes.length; i++){
    		if(!used[i]){
    			if(last == -1){
    				last = boxes[i];
    				len = 1;
    			}else{
    				if(boxes[i] == last){
    					len++;
    				}else{
    					last = boxes[i];
    					len = 1;
    				}
    			}
    		}
    	}
    	
    }
    public boolean allUsed(boolean[] used){
    	for(boolean b : used){
    		if(!b){
    			return false;
    		}
    	}
    	return true;
    }
    //https://www.youtube.com/watch?v=U8Ru-ZpfHfA
    public int removeBoxes2(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n - 1, 0, dp);
    }
        
    private int removeBoxesSub(int[] boxes, int l, int r, int k, int[][][] dp) {
        if (l > r) return 0;
        if (dp[l][r][k] > 0) return dp[l][r][k];
          
        while(l < r && boxes[r] == boxes[r - 1]){
        	r--;
        	k++;
        }
        
        int res = (k + 1) * (k + 1) + removeBoxesSub(boxes, l, r - 1, 0, dp);
            
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, r - 1, 0, dp) + removeBoxesSub(boxes, l, i, k + 1, dp));
            }
        }
            
        dp[l][r][k] = res;
        return res;
    }
    
}
