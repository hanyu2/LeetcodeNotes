package d721;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
	public int kthSmallest(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k, Collections.reverseOrder());
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(pq.size() < k){
                    pq.offer(matrix[i][j]);
                }else{
                    if(matrix[i][j] <= pq.peek()){
                        pq.poll();
                        pq.offer(matrix[i][j]);
                    }
                }
            }
        }
        return pq.poll();
    }
	//binary search
	public static int kthSmallest2(int[][] matrix, int k) {
		List<Integer> list = new ArrayList<Integer>();
		int m = matrix.length;
		int n = matrix.length;
		for(int i = 0; i < m; i++){
			for(int j = 0 ; j < n; j++){
				if(list.size() < k || matrix[i][j] < list.get(list.size() - 1)){
					int index = Collections.binarySearch(list, matrix[i][j]);
					if(index < 0){
						index = -(index + 1);
					}
					list.add(index, matrix[i][j]);
					if(list.size() > k){
						list.remove(list.size() - 1);
					}
				}
			}
		}
		return list.get(list.size() - 1);
	}
	public static void main(String[] args) {
		int[][] matrix = {{1,2},{1,3}};
		System.out.println(kthSmallest2(matrix, 3));
	}
}
