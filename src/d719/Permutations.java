package d719;

import java.util.ArrayList;
import java.util.List;

public class Permutations {
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 0){
            return res;
        }
        boolean[] used = new boolean[nums.length];
        search(nums, used, new ArrayList<Integer>(), res);
        return res;
    }
    public void search(int[] nums, boolean[] used, List<Integer> list, List<List<Integer>> res){
        if(list.size() == nums.length){
            res.add(list);
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(!used[i]){
                used[i] = true;
                List<Integer> temp = new ArrayList<Integer>(list);
                temp.add(nums[i]);
                search(nums, used, temp, res);
                used[i] = false;
            }
        }
    }
}
