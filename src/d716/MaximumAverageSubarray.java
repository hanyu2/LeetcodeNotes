package d716;

public class MaximumAverageSubarray {
	 public double findMaxAverage(int[] nums, int k) {
	        int min = 10000;
	        int max = -10000;
	        for(int num : nums){
	            min = Math.min(num, min);
	            max = Math.max(num, max);
	        }
	        double left = min;
	        double right = max;
	        while(right - left > 0.00001){
	            double mid = (left + right) / 2;
	            if(valid(mid, nums, k)){
	                right = mid;
	            }else{
	                left = mid;
	            }
	        }
	        return left;
	    }
	    public boolean valid(double target, int[] nums, int k){
	        double[] values = new double[nums.length];
	        for(int i = 0; i < nums.length; i++){
	            values[i] = (double)nums[i] - target;
	        }
	        double sum = 0;
	        double premin = 0;
	        double presum = 0;
	        for(int i = 0; i < values.length; i++){
	            sum += values[i];
	            if(i >= k - 1){
	                if(sum - premin > 0){
	                    return false;
	                }
	                presum += values[i - k + 1];
	                premin = Math.min(premin, presum);
	            }
	        }
	        return true;
	    }
}
