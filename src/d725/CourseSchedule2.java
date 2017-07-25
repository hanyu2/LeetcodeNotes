package d725;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule2 {
	public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<Integer>());
        }
        int[] indegree = new int[numCourses];
        for(int[] pre : prerequisites){
            indegree[pre[0]]++;
            map.get(pre[1]).add(pre[0]);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int len = 0;
        while(!q.isEmpty()){
            int n = q.poll();
            res[len++] = n;
            List<Integer> list = map.get(n);
            for(int k : list){
                indegree[k]--;
                if(indegree[k] == 0){
                    q.offer(k);
                }
            }
        }
        if(len != numCourses){
            return new int[0];
        }
        return res;
    }
}
