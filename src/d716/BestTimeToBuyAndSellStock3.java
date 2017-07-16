package d716;

public class BestTimeToBuyAndSellStock3 {
	
	//开始想从前到后建数组保持到这个位置的最大的收益，发现当第二次交易不好计算
	//遇见两次的要想到从两头开始
	public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n < 2){
            return 0;
        }
        int[] maxBefore = new int[n];
        int min = prices[0];
        for(int i = 1; i < n; i++){
            maxBefore[i] = Math.max(prices[i] - min, maxBefore[i-1]);
            min = Math.min(min, prices[i]);
        }
        int max = prices[n - 1];
        int res = 0;
        for(int i = n - 2; i >= 0; i--){
            res = Math.max(res, max - prices[i] + maxBefore[i]);
            max = Math.max(prices[i], max);
        }
        return res;
    }
}
