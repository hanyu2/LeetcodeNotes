package d721;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArrays2 {
	public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<Integer>();
        int i = 0;
        int j = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while(i < nums1.length && j < nums2.length){
            if(nums1[i] == nums2[j]){
                res.add(nums1[i]);
                i++;
                j++;
            }else if(nums1[i] < nums2[j]){
                i++;
            }else{
                j++;
            }
        }
        int[] arr = new int[res.size()];
        for(int k = 0; k < arr.length; k++){
            arr[k] = res.get(k);
        }
        return arr;
    }
}
