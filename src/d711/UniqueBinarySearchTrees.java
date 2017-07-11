package d711;

public class UniqueBinarySearchTrees {
	public int numTrees(int n) {
        if(n <= 2){
            return n;
        }
        int[] res = new int[n + 1];
        res[0] = 1;
        res[1] = 1;
        res[2] = 2;
        for(int i = 3; i <= n; i++){
            for(int j = 0; j <= i - 1; j++){
                res[i] += res[j] * res[i - j - 1];
            }
        }
        return res[n];
    }
}
