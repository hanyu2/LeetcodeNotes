package d726;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRange {
	//index-1忘记检查边界
	//failed on[1,2,3][1,2,3],只能保证range长度最小，无法保证range最小
	public int[] smallestRange(List<List<Integer>> nums) {
        int[] res = new int[2];
        PriorityQueue<Node> pq = new PriorityQueue<>(3500, new Comparator<Node>(){
        	public int compare(Node n1, Node n2){
        		return n2.val - n1.val;
        	}
        });
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < nums.size(); i++){
        	List<Integer> list = nums.get(i);
        	int last = list.get(list.size() - 1);
        	Node node = new Node(list.size() - 1, last, i);
        	pq.offer(node);
        	min = Math.min(min, last);
        }
        res[0] = min;
        while(true){
        	Node n = pq.poll();
        	int nextVal = n.index - 1>= 0 ? nums.get(n.from).get(n.index - 1) : Integer.MAX_VALUE;
        	if(nextVal > min){
        		pq.add(new Node(n.index - 1, nextVal, n.from));
        	}else{
        		max = n.val;
        		break;
        	}
        }
        res[1] = max;
        return res;
    }
	//从前向后扫，如何保证当前区间包含每一个list的值
	//用pq的size和list size 控制
	public int[] smallestRange2(List<List<Integer>> nums) {
        PriorityQueue<Node> pq = new PriorityQueue<>(3500, new Comparator<Node>(){
        	public int compare(Node n1, Node n2){
        		return n1.val - n2.val;
        	}
        });
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < nums.size(); i++){
            Node node = new Node(0, nums.get(i).get(0), i);
            pq.offer(node);
            max = Math.max(max, nums.get(i).get(0));
        }
        int range = Integer.MAX_VALUE;
        int start = -1;
        int end = -1;
        while(pq.size() ==  nums.size()){
            Node node = pq.poll();
            if(max - node.val < range){
                range = max - node.val;
                start = node.val;
                end = max;
            }
            if(node.index + 1 < nums.get(node.from).size()){
                Node temp = new Node(node.index + 1, nums.get(node.from).get(node.index + 1), node.from);
                pq.offer(temp);
                if(temp.val > max){
                    max = temp.val;
                }
            }
        }
        return new int[]{start, end};
    }
}

class Node{
	int index;
    int val;
    int from;
    public Node(int index, int val, int from) {
		this.index = index;
		this.val = val;
		this.from = from;
	}
}