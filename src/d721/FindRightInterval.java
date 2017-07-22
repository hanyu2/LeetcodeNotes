package d721;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FindRightInterval {
	// 开始想新建一个数据结构储存index，start， end
	// 然后按照start排序
	// 从前向后依次用二分找
	// 因为题目已经说没有相同的start，可以根据start存index
	// 犯了很多错,自己再实现一次
	public int[] findRightInterval(Interval[] intervals) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int[] res = new int[intervals.length];
		int[] starts = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			map.put(intervals[i].start, i);
			starts[i] = intervals[i].start;
		}
		Arrays.sort(starts);
		for (int i = 0; i < intervals.length; i++) {
			int end = intervals[i].end;
			int index = search(starts, end);
			if (index < end) {
				res[i] = -1;
			} else {
				res[i] = map.get(index);
			}
		}
		return res;
	}

	public int search(int[] starts, int target) {
		int left = 0;
		int right = starts.length - 1;
		while (left < right) {
			int mid = (left + right) / 2;
			if (starts[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return starts[left];
	}

	public int[] findRightInterval2(Interval[] intervals) {
		TreeMap<Integer, Integer> starts = new TreeMap<>();
		int res[] = new int[intervals.length];
		for (int i = 0; i < intervals.length; i++) {
			starts.put(intervals[i].start, i);
		}
		for (int i = 0; i < intervals.length; i++) {
			Map.Entry<Integer, Integer> pos = starts.ceilingEntry(intervals[i].end);
			res[i] = pos == null ? -1 : pos.getValue();
		}
		return res;
	}

}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}
}
