package d714;

import java.util.Arrays;
import java.util.TreeSet;

public class MaxSumOfRectangleNoLargerThanK {
	//遇到二维，先降维
	//首先是一维数组求subarray最大和小于k
	private int maxSumSubArray(int[] a, int k) {
		int max = Integer.MIN_VALUE;
		int sum = 0;
		TreeSet<Integer> set = new TreeSet<Integer>();
		set.add(0);
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
			Integer gap = set.ceiling(sum - k);
			if (gap != null)
				max = Math.max(max, sum - gap);
			set.add(sum);
		} 
		return max;
	}
	
	//
	public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
        	int[] sum = new int[m];
        	for(int j = i; j < n; j++){
        		for(int r = 0; r < m; r++){
        			 sum[r] += matrix[j][r];
        		}
        		TreeSet<Integer> set = new TreeSet<Integer>();
        		set.add(0);
        		int total = 0;
        		for(int s : sum){
        			total += s;
        			Integer gap = set.ceiling(total - k);
        			if(gap != null){
        				max = Math.max(max, total - gap);
        			}
        			set.add(total);
        		}
        	}
        }
        return max;
    }
	
	public static void main(String[] args) {
		TreeSet<Integer> set = new TreeSet<Integer>(Arrays.asList(1,3,5,6,7));
		System.out.println(set.ceiling(4));
		System.out.println(set.floor(4));
	}
}
