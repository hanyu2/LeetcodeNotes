package d710;

public class BestTimeToBuyAndSellStock {
	public int maxProfit(int[] prices) {
		if (prices.length == 0) {
			return 0;
		}
		//开始把min初始为0， failed on[7, 1, 5, 3, 6, 4]
		int min = prices[0];
		int max = 0;
		for (int price : prices) {
			min = Math.min(price, min);
			max = Math.max(price - min, max);
		}
		return max;
	}
}
