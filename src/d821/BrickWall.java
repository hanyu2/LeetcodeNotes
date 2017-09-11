package d821;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {
	public int leastBricks(List<List<Integer>> wall) {
        if(wall.size() == 1){
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(List<Integer> w : wall){
            int sum = 0;
            for(int i = 0; i < w.size() - 1; i++){
                sum += w.get(i);
                if(map.containsKey(sum)){
                    map.put(sum, map.get(sum) + 1);
                }else{
                    map.put(sum, 1);
                }
            }
        }
        int max = 0;
        System.out.println(map.size());
        for(int k : map.keySet()){
            if(map.get(k) > max){
                max = map.get(k);
            }
        }
        return wall.size() - max;
    }
	public static void main(String[] args) {
		List<List<Integer>> wall = new ArrayList<List<Integer>>();
		wall.add(new ArrayList<Integer>(Arrays.asList(1)));
		BrickWall bw = new BrickWall();
		System.out.println(bw.leastBricks(wall));
	}
}
