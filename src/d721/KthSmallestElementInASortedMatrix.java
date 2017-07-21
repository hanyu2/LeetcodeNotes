package d721;

import java.util.ArrayList;
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
	//binary search O(m*n*logk)
	//1.开始的判断条件写的是如果，list长度小于k，直接add，会造成list顺序混乱
	//[1, 2][1, 3]造成[1, 2, 1]
	//2.注意判断条件list.size()-1，当list为空，越界
	//不能在前面加list为空的判断，会造成无法填满k个元素
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
	
	//类似373
	public int kthSmallest4(int[][] matrix, int k) {
        int n = matrix.length;
        PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>();
        for(int j = 0; j <= n-1; j++) pq.offer(new Tuple(0, j, matrix[0][j]));
        for(int i = 0; i < k-1; i++) {
            Tuple t = pq.poll();
            if(t.x == n-1) continue;
            pq.offer(new Tuple(t.x+1, t.y, matrix[t.x+1][t.y]));
        }
        return pq.poll().val;
    }
	public static void main(String[] args) {
		int[][] matrix = {{1,2},{1,3}};
		System.out.println(kthSmallest2(matrix, 3));
	}
}
class Tuple{
	int x;
    int y;
    int val;
    public Tuple(int x, int y, int val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
}
