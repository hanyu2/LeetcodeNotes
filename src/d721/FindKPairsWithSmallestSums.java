package d721;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
	//开始只初始化了[0, 0]
	//每次取出往里加x+1,or y+1，有重复情况
	//[1, 1]弹出来放[1, 2][2, 1]
	//[0, 2]弹出来放[1, 2][0, 3] 重复
	//while循环加上pq为空的判断，有可能k会超出所有可能性
	public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
		PriorityQueue<Tuple> pq = new PriorityQueue<Tuple>(1000, new Comparator<Tuple>() {
			public int compare(Tuple t1, Tuple t2){
				return t1.val - t2.val;
			}
		});
        List<int[]> res = new ArrayList<int[]>();
        if(nums1.length == 0 || nums2.length == 0){
            return res;
        }
		for(int i = 0; i < nums2.length; i++){
            pq.offer(new Tuple(0, i, nums1[0] + nums2[i]));
        }
		while(res.size() < k && !pq.isEmpty()){
			Tuple t = pq.poll();
			res.add(new int[]{nums1[t.x], nums2[t.y]});
			if(t.x < nums1.length - 1){
				pq.offer(new Tuple(t.x + 1, t.y, nums1[t.x+1] + nums2[t.y]));
			}
		}
		return res;
	}
}

