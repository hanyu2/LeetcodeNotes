package d719;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
	//一开始想不用加index，实际搜索会从0开始，有重复
	//[2, 2, 3] [2, 3, 2]
	//应该从index及以后
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates.length == 0){
            return res;
        }
        search(0, candidates, 0, new ArrayList<Integer>(), target, res);
        return res;
    }
    public void search(int index, int[] can, int sum, List<Integer> list, int target, List<List<Integer>> res){
        if(sum > target){
            return;
        }
        if(sum == target){
            List<Integer> temp = new ArrayList<Integer>(list);
            res.add(temp);
            return;
        }
        for(int i = index; i < can.length; i++){
            list.add(can[i]);
            search(i, can, sum + can[i], list, target, res);
            list.remove(list.size() - 1);
        }
    }
}
