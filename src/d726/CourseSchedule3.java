package d726;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseSchedule3 {
	public int scheduleCourse(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>(){
            public int compare(int[] c1, int[] c2){
                return c1[1] - c2[1];
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(courses.length, Collections.reverseOrder());
        int time = 0;
        for(int[] course : courses){
            time += course[0];
            pq.offer(course[0]);
            if(time > course[1]){
                time -= pq.poll();
            }
        }
        return pq.size();
    }
}
