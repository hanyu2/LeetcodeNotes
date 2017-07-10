package d709;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
	//一开始写的搜索空间时1-9， 有问题 
	//当k=3, n=7, 开始搜索到1， 2， 4
	//后来最外层为2，仍能搜索到1，2，4
	//应该从上一个数字之后开始搜索
	public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        search(k, n, 1, 0, new boolean[10], res);
        return res;
    }
    
    public static void search(int k, int n, int index, int sum, boolean[] used, List<List<Integer>> res){
        if(k == 0){
            if(sum == n){
                List<Integer> list = new ArrayList<Integer>();
                for(int i = 1; i <= 9; i++){
                    if(used[i]){
                        list.add(i);
                    }
                }
                res.add(list);
            }
            return;
        }
        for(int i = index; i <= 9; i++){
            if(!used[i]){
                if(i + sum <= n){
                    used[i] = true;
                    search(k - 1, n, i, sum + i, used, res);
                    used[i] = false;
                }
            }
        }
    }
    public static void main(String[] args) {
		combinationSum3(3, 7);
	}
}
