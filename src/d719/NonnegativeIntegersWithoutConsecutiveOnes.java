package d719;

public class NonnegativeIntegersWithoutConsecutiveOnes {
	public static int findIntegers(int num) {
        if(num <= 2){
            return num + 1;
        }
        int[] dp = new int[num + 1];
        for(int i = 0; i <= 2; i++){
        	dp[i] = i + 1;
        }
        dp[3] = 3;
        for(int i = 4; i <= num; i++){
        	StringBuilder sb = new StringBuilder(Integer.toBinaryString(i));
        	StringBuilder one = new StringBuilder("1");
        	for(int j = 0; j < sb.length() - 1; j++){
        		one.append("0");
        	}
        	if(one.toString().equals(sb.toString())){
        		dp[i] = dp[Integer.parseInt(one.toString(), 2) - 1] + 1;
        	}else{
        		dp[i] = dp[Integer.parseInt(one.toString(), 2)];
        	}
        	if(sb.charAt(1) == '0'){
        		dp[i] += dp[Integer.parseInt(sb.substring(2).toString(), 2)] - 1;
        	}else{
        		dp[i] = dp[i-1];
        	}
        }
        return dp[num];
    }
	//http://www.geeksforgeeks.org/count-number-binary-strings-without-consecutive-1s/
	public static int findIntegers2(int num) {
        int n = Integer.toBinaryString(num).length();
        int a[] = new int [n];
        int b[] = new int [n];
        a[0] = 1;
        for (int i = 1; i < n; i++){
            a[i] = a[i-1] + b[i-1];
            b[i] = a[i-1];
        }
        return a[n-1] + b[n-1];
    }
	
	public static void main(String[] args) {
		//learn useful methods
		System.out.println(Integer.numberOfTrailingZeros(8));
		System.out.println(findIntegers2(19));
	}
}
