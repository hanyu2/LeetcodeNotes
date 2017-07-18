package d718;

public class MaximalRectangle {
	public static int maximalRectangle(char[][] matrix) {
        if(matrix.length == 0){
            return 0;
        }   
        int m = matrix.length;
        int n = matrix[0].length;
        int[] height = new int[n];
        int max = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == '0'){
                    height[j] = 0;
                }else{
                    height[j] += 1;
                }
            }
            int[] left = new int[n];
            int[] right = new int[n];
            left[0] = -1;
            right[n - 1] = n;
            for(int j = 1; j < n; j++){
                int k = j - 1;
                while(k >= 0 && height[k] >= height[j]){
                    k = left[k];
                }
                left[j] = k;
            }
            for(int j = n - 2; j >= 0; j--){
                int k = j + 1;
                while(k < n && height[k] >= height[j]){
                    k = right[k];
                }
                right[j] = k;
            }
            for(int j = 0; j < n; j++){
                max = Math.max(max, height[j] * (right[j] - left[j] - 1));
            }
        }
        return max;
    }
	public static void main(String[] args) {
		char[][] matrix = {{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
		System.out.println(maximalRectangle(matrix));
	}
}
