package d725;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseSchedule {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<int[]>> map = new HashMap<Integer, List<int[]>>();
        for(int i = 0; i < numCourses; i++){
            map.put(i, new ArrayList<int[]>());
        }
        for(int[] pre : prerequisites){
            indegree[pre[1]]++;
            map.get(pre[0]).add(pre);
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0){
                q.offer(i);
            }
        }
        int count = 0;
        while(!q.isEmpty()){
            int n = q.poll();
            count++;
            List<int[]> list = map.get(n);
            for(int[] arr : list){
                indegree[arr[1]]--;
                if(indegree[arr[1]] == 0){
                    q.offer(arr[1]);
                }
            }
        }
        return count == numCourses;
    }
}
