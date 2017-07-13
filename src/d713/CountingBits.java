package d713;

public class CountingBits {
	public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        if(num == 0){
            return res;
        }
        res[1] = 1;
        for(int i = 2; i <= num; i++){
            int sum = 0;
            int n = i;
            while(n > 0){
                sum += res[n & 1];
                n >>>= 1;
            }
            res[i] = sum;
        }
        return res;
    }
	public static void main(String[] args) {
		countBits(7);
	}
}
