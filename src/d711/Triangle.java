package d711;

import java.util.ArrayList;
import java.util.List;

public class Triangle {
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle.size() == 0) {
			return 0;
		}
		List<Integer> cache = new ArrayList<Integer>();
		cache.add(triangle.get(0).get(0));
		for (int i = 1; i < triangle.size(); i++) {
			List<Integer> temp = new ArrayList<Integer>();
			List<Integer> last = triangle.get(i);
			temp.add(cache.get(0) + last.get(0));
			for (int j = 1; j < last.size() - 1; j++) {
				temp.add(Math.min(cache.get(j - 1), cache.get(j)) + last.get(j));
			}
			temp.add(cache.get(cache.size() - 1) + last.get(cache.size()));
			cache = temp;
		}
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < cache.size(); i++) {
			min = Math.min(cache.get(i), min);
		}
		return min;
	}
}
