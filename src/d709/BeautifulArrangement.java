package d709;

import java.util.HashSet;
import java.util.Set;

public class BeautifulArrangement {
	//开始想用set判断数字是否使用
	//有ConcurrentModificationException
	//改为用数组判断
	int count = 0;

	public int countArrangement(int N) {
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= N; i++) {
			set.add(i);
		}
		search(1, set);
		return count;
	}

	public void search(int index, Set<Integer> set) {
		if (set.size() == 0) {
			count++;
			return;
		}
		for (int n : set) {
			if (n % index == 0 || index % n == 0) {
				set.remove(n);
				search(index + 1, set);
				set.add(n);
			}
		}
	}
	
	 public int countArrangement2(int N) {
	        search2(1, N, new boolean[N + 1]);
	        return count;
	    }
	    
	    public void search2(int index, int N, boolean[] used){
	        if(index > N){
	            count++;
	            return;
	        }
	        for(int i = 1; i <= N; i++){
	            if(!used[i] && (i % index == 0 || index % i == 0)){
	                used[i] = true;
	                search2(index + 1, N, used);
	                used[i] = false;
	            }
	        }
	    }
}
