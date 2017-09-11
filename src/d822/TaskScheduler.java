package d822;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskScheduler {
	//忘记temp最后clear
	public int leastInterval(char[] tasks, int n) {
		if (tasks.length == 0) {
			return 0;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		PriorityQueue<Node> pq = new PriorityQueue<Node>(10000, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				return n2.time - n1.time;
			}
		});
		for (char task : tasks) {
			if (map.containsKey(task)) {
				map.put(task, map.get(task) + 1);
			} else {
				map.put(task, 1);
			}
		}
		for (char task : map.keySet()) {
			Node node = new Node(task, map.get(task), 1);
			pq.offer(node);
		}
		List<Node> temp = new ArrayList<Node>();
		int time = 1;
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			if (node.next <= time) {
				node.time--;
				node.next += (n + 1);
				if (node.time != 0) {
					pq.offer(node);
				}
				time++;
				for (Node no : temp) {
					pq.offer(no);
				}
				temp.clear();
			} else {
				temp.add(node);
			}
			if (pq.isEmpty() && temp.size() > 0) {
				time++;
				for (Node no : temp) {
					pq.offer(no);
				}
				temp.clear();
			}
		}
		return time - 1;
	}
	public static void main(String[] args) {
		char[] tasks = {'A','A','A','A','A','A','B','C','D','E','F','G'};
		TaskScheduler ts = new TaskScheduler();
		System.out.println(ts.leastInterval(tasks, 2));
	}
}

class Node {
	char val;
	int time;
	int next;

	public Node(char v, int t, int next) {
		this.val = v;
		this.time = t;
		this.next = next;
	}
}
