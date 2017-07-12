package d712;

public class MaximalSquare {
	//cache
	//dp记录纵向连续1的个数
	//count记录本行连续1的个数
	//b记录上一行方形的长度
	public static int maximalSquare(char[][] matrix) {
        int max = 0;
        int m = matrix.length;
        if(m == 0){
            return 0;
        }
        int n = matrix[0].length;
        int [] dp = new int[n + 1];
        int [] b = new int[n + 1];
        for(int i = 0; i < m; i++){
            int count = 0;
            int[] temp = new int[n + 1];
            int[] btemp = new int[n + 1];
            for(int j = 1; j <= n; j++){
            	temp[j] = dp[j];
            }
            for(int j = 1; j <= n; j++){
                if(matrix[i][j-1] == '0'){
                    count = 0;
                    temp[j] = 0;
                    btemp[j] = 0;
                }else{
                	max = Math.max(1, max);
                    if(b[j - 1] != 0){
                        btemp[j] = Math.min(count, Math.min(temp[j], b[j - 1]));
                    }
                    count++;
                    temp[j]++;
                    btemp[j]++;
                    max = Math.max(max, btemp[j]);
                }
            }
            dp = temp;
            b = btemp;
        }
        return max * max;
    }
	
	public int maximalSquare2(char[][] a) {
	    if(a.length == 0) return 0;
	    int m = a.length, n = a[0].length, result = 0;
	    int[][] b = new int[m+1][n+1];
	    for (int i = 1 ; i <= m; i++) {
	        for (int j = 1; j <= n; j++) {
	            if(a[i-1][j-1] == '1') {
	                b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
	                result = Math.max(b[i][j], result); // update result
	            }
	        }
	    }
	    return result*result;
	}
	
	public static void main(String[] args) {
		String[] temp = {"10100","10111","11111","10010"};
		//String[] temp = {"1010","1011","1011","1111"};
		char[][] matrix = new char[temp.length][temp[0].length()];
		for(int i = 0; i < temp.length; i++){
			for(int j = 0; j < temp[0].length(); j++){
				matrix[i][j] = temp[i].charAt(j);
			}
		}
		//char[][] matrix = {{'1'}};
		System.out.println(maximalSquare(matrix));
	}
}
