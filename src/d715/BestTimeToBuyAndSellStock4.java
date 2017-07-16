package d715;

public class BestTimeToBuyAndSellStock4 {
	public int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if(n <= 1){
			return 0;
		}
        int[] dp = new int[n];
        for(int i = 0; i < k; i++){
        	int[] temp = new int[n];
        	int min = prices[0];
        	temp[0] = 0;
        	for(int j = 1; j < n; j++){
        		temp[j] = prices[j] > min ? prices[j] - min : 0;
        		min = Math.min(prices[j], min);
        	}
        }
    }
}
