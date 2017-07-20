package d719;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum2 {
	//增加排序，判断
	public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(candidates.length == 0){
            return res;
        }
        search(0, candidates, 0, new ArrayList<Integer>(), target, res);
        return res;
    }
    public static void search(int index, int[] can, int sum, List<Integer> list, int target, List<List<Integer>> res){
        if(sum > target){
            return;
        }
        if(sum == target){
            List<Integer> temp = new ArrayList<Integer>(list);
            res.add(temp);
            return;
        }
        for(int i = index; i < can.length; i++){
            if(i > index && can[i] == can[i-1]){
                continue;
            }
            list.add(can[i]);
            search(i + 1, can, sum + can[i], list, target, res);
            list.remove(list.size() - 1);
        }
    }
    public static void main(String[] args) {
		int[] candidates = {10,1,2,7,6,1,5};
		combinationSum2(candidates, 8);
	}
}
