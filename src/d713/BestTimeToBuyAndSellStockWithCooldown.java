package d713;

public class BestTimeToBuyAndSellStockWithCooldown {
	 public int maxProfit(int[] prices) {
	        int n = prices.length;
	        if(n <= 1){
	            return 0;
	        }
	        int[] s1 = new int[n];
	        int[] s2 = new int[n];
	        int[] s3 = new int[n];
	        s2[0] = -prices[0];
	        for(int i = 1; i < prices.length; i++){
	            int price = prices[i];
	            s1[i] = Math.max(s1[i - 1], s3[i-1]);
	            s2[i] = Math.max(s2[i - 1], s1[i - 1] - price);
	            s3[i] = s2[i - 1] + price;
	        }
	        return Math.max(s1[n - 1], s3[n - 1]);
	    }
}
