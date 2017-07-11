package d710;

public class ArithmeticSlices {
	public int numberOfArithmeticSlices(int[] A) {
        if(A.length < 3){
            return 0;
        }
        int[] dp = new int[A.length];
        if(A[2] - A[1] == A[1] - A[0]){
            dp[2] = 1;
        }
        int result = dp[2];
        for(int i = 3; i < A.length; i++){
            if(A[i] - A[i - 1] == A[i - 1] - A[i - 2]){
                dp[i] = dp[i - 1] + 1;
            }
            result += dp[i];
        }
        return result;
    }
}
