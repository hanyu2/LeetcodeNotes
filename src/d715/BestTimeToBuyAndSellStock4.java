package d715;

public class BestTimeToBuyAndSellStock4 {
	//http://lib.csdn.net/article/datastructure/21116
	//http://blog.csdn.net/feliciafay/article/details/45128771
	public static int maxProfit(int k, int[] prices) {
		int n = prices.length;
		if(n <= 1){
			return 0;
		}
        if(k >= prices.length / 2){
        	int sum = 0;
        	for(int i = 1; i < prices.length; i++){
        		sum += Math.max(0, prices[i] - prices[i - 1]);
        	}
        	return sum;
        }
        //first is 
        int[][] local = new int[k + 1][n];//must sell on this day
        int[][] global = new int[k + 1][n];
        for(int i = 1; i <= k; i++){
        	for(int j = 1; j < n; j++){
        		local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(0, prices[j] - prices[j - 1]), local[i][j - 1] + prices[j] - prices[j - 1]);
        		global[i][j] = Math.max(global[i][j-1], local[i][j]);
        	}
        }
        return global[k][n - 1];
    }
	public static void main(String[] args) {
		int[] prices = {3,2,6,5,0,3};
		System.out.println(maxProfit(2, prices));
	}
}
