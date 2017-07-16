package d716;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FrogJump {
	//三步乡下搜索， 当扫到某一层存有跟当前step+-1，返回true
	//当最后一个数很大，中间所有层没法add step，会超时
	public static boolean canCross(int[] stones) {
		if(stones.length <= 1){
			return true;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Map<Integer, List<Integer>> cache = new HashMap<Integer, List<Integer>>();
		for(int i = 0; i < stones.length; i++){
			map.put(stones[i], i);
			cache.put(i, new ArrayList<Integer>());
		}
		return search(0, 0, stones, map, cache);
	}
	
	public static boolean search(int index, int step, int[] stones, Map<Integer, Integer> map, Map<Integer, List<Integer>> cache){
		if(index == stones.length - 1){
			return true;
		}
		for(int i = step - 1; i <= step + 1; i++){
			if(cache.get(index).contains(i)){
				return true;
			}
		}
		for(int i = step + 1; i >=  step - 1; i--){
			if(i <= 0){
				continue;
			}
			if(map.containsKey(stones[index] + i)){
				if(search(map.get(stones[index] + i), i, stones, map, cache)){
					List<Integer> list = cache.get(index);
					list.add(i);
					return true;
				}
			}
		}
		return false;
	}
	//从前向后，注意毛存的不是index，是石头的位置
	public static boolean canCross2(int[] stones) {
        if(stones.length <= 1){
            return true;
        }
        HashMap<Integer, Set<Integer>> level = new HashMap<Integer, Set<Integer>>();
        for(int i = 0; i < stones.length; i++){
            level.put(stones[i], new HashSet<Integer>());
        }
        level.get(0).add(1);
        for(int i = 0; i < stones.length - 1; i++){
            int pos = stones[i];
            for(int step : level.get(pos)){
                int reach = step + pos;
                if(reach == stones[stones.length - 1]){
                    return true;
                }
                Set<Integer> set = level.get(reach);
                if(set != null){
                    set.add(step);
                    set.add(step + 1);
                    if(step - 1 > 0){
                        set.add(step - 1);
                    }
                }
            }
        }
        return false;
    }
	
	public static void main(String[] args) {
		//int[] stones = {0,1,3,5,6,8,12,17};
		int[] stones2 = {0,1,2,3,4,8,9,11};
		System.out.println(canCross2(stones2));
	}
}

