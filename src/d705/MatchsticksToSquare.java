package d705;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MatchsticksToSquare {
	/*
	 * 一开始想把数组分成两份搜索，当两份都能分开，再把每份分开再搜索
	 * ac做法：每个长度都必须是属于四个边的任意一个，所以对于每个长度要尝试四个边的可能性
	 * 排序能让搜索更早退出
	 */	
	public boolean makesquare(int[] nums) {
        if(nums.length < 4){
            return false;
        }
        int sum = 0;
        for(int n : nums){
            sum += n;
        }
        if(sum % 4 != 0){
            return false;
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int n : nums){
            list.add(n);
        }
        Arrays.sort(nums);
        reverse(nums);
        return search(nums, 0, new int[4], sum / 4);
    }
    public boolean search(int[] nums, int index, int[] edge, int len){
        if(index == nums.length){
            if(edge[0] == len && edge[1] == len && edge[2] == len && edge[3] == len){
                return true;
            }else{
                return false;
            }
        }
        for(int i = 0; i < 4; i++){
            if(edge[i] + nums[index] > len){
                continue;
            }
            edge[i] += nums[index];
            if(search(nums, index + 1, edge, len)){
                return true;
            }else{
                edge[i] -= nums[index];
            }
        }
        return false;
    }
    private void reverse(int[] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++; j--;
        }
    }
}
