package d821;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElement {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Node> map = new HashMap<Integer, Node>();
		PriorityQueue<Node> q = new PriorityQueue<Node>(1000, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n2.freq - n1.freq;
			}
		});
		for (int n : nums) {
			if (map.containsKey(n)) {
				Node node = map.get(n);
				q.remove(node);
				node.freq++;
				map.put(n, node);
				q.offer(node);
			} else {
				Node node = new Node(n, 1);
				q.offer(node);
				map.put(n, node);
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < k; i++) {
			if (!q.isEmpty()) {
				Node n = q.poll();
				list.add(n.key);
			}
		}
		return list;
	}
	public static void main(String[] args) {
		TopKFrequentElement t = new TopKFrequentElement();
		int[]nums = {3,0,1,0};
		System.out.println(t.topKFrequent(nums, 1).toString());
	}
}

class Node {
	int key;
	int freq;

	public Node(int k, int freq) {
		this.key = k;
		this.freq = freq;
	}
}
