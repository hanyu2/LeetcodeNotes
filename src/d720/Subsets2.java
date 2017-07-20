package d720;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {
	public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        search(nums, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void search(int[] nums, int index, List<Integer> list, List<List<Integer>> res){
        res.add(list);
        for(int i = index; i < nums.length; i++){
            if(i > index && nums[i] == nums[i-1]){
                continue;
            }
            List<Integer> temp = new ArrayList<Integer>(list);
            temp.add(nums[i]);
            search(nums, i + 1, temp, res);
        }
    }
}
