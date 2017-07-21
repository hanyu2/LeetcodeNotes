package d720;

public class ArrangingCoins {
	public static int arrangeCoins(int n) {
        if(n == 0){
            return 0;
        }
        int left = 1;
        int right = n;
        while(left <= right){
            int mid = (left + right)/2;
            int sum = (1 + mid) * mid / 2;
            if(sum + mid + 1 < n){
                left = mid + 1;
            }else if(sum + mid + 1 > n){
                right = mid - 1;
            }else{
                return mid + 1;
            }
        }
        return left;
    }
	public static void main(String[] args) {
		System.out.println(arrangeCoins(8));
	}
}
