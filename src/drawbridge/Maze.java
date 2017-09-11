package drawbridge;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Maze{
	
	public static void main(String[] args){

		int[][] arr = {{0,0,0,1,2},
					   {0,1,0,1,0},
					   {1,2,0,0,0},
					   {2,0,0,0,1},
					   {0,0,0,0,0}};

		int[][] sm = {{0,2,0},
					 {1,1,2},
					 {1,0,0}};			   	

					 int x=4;
					 int y=2;

			System.out.println(findMinimumPath(arr,x,y,0,0));
	}

	public static int findMinimumPath(int[][] arr,int x,int y,int startX,int startY){

		ArrayList<Point> arrList = new ArrayList<>();
			for(int i=0; i < arr.length;i++){
				for(int j=0; j < arr.length;j++){
					if(arr[i][j]==2){
						arrList.add(new Point(i,j));
					}	
				}
			}



			int minimumDistance=Integer.MAX_VALUE;
			
			int newStartX=0;
			int newStartY=0;

			int id=-1;

			int steps=0;
			int dist=0;
			while(arrList.size()!=0){
				minimumDistance=Integer.MAX_VALUE;
				for(int i=0; i < arrList.size();i++){
					Point p = arrList.get(i);

					dist = findMinimumDistance(new Point(startX,startY),new Point(p.x,p.y),arr);
					System.out.println("finding distance between:"+startX+":"+startY+"   and "+p.x+":"+p.y+"    dist:"+dist);
						if(dist ==-1){
							return -1;
						}

						if(dist < minimumDistance){
							newStartX=p.x;
							newStartY=p.y;
							id=i;
							minimumDistance=dist;
						}
				}
				
				steps=steps+minimumDistance;
				arrList.remove(id);
				startX=newStartX;
				startY=newStartY;
			}
	dist = findMinimumDistance(new Point(startX,startY),new Point(x,y),arr);
	System.out.println("finding distance between:"+startX+":"+startY+"   and "+x+":"+y+"    dist:"+dist);
			return dist==-1 ? -1 : steps + dist;
	}


	public static int findMinimumDistance(Point source,Point destination,int[][] arr){

		    int[] possibleRow = {0,-1,0,1};
		    int[] possibleColumn = {-1,0,1,0};


		    int count=0;
		    
		    Queue<QueueNode> queue = new LinkedList<>();
		    queue.add(new QueueNode(source,0));	

		    boolean[][] visited = new boolean[arr.length][arr.length];

		    visited[source.x][source.y]=true;

		    while(!queue.isEmpty()){
		    	QueueNode node = queue.poll();
		    	Point currentPoint = node.point;

		    	if(currentPoint.x==destination.x && currentPoint.y==destination.y){
		    		return node.distance;
		    	}else{

		    		for(int i=0; i < possibleRow.length;i++){
		    			int possibleX = currentPoint.x + possibleRow[i];
		    			int possibleY = currentPoint.y + possibleColumn[i];

		    			if(isValid(possibleX,possibleY,arr) && !visited[possibleX][possibleY] && arr[possibleX][possibleY]!=1){

		    				visited[possibleX][possibleY]=true;
		    				queue.add(new QueueNode(new Point(possibleX,possibleY),node.distance+1));
		    			}
		    		}
		    	}
		    }

		return -1;
	}

    public static boolean isValid(int possibleX,int possibleY,int[][] arr){
        return(possibleX < arr.length && possibleX >=0 && possibleY < arr[0].length && possibleY >=0 );
    }




}
class Point{

	int x;
	int y;
	public Point(int x,int y){
		this.x=x;
		this.y=y;
	}

}
class QueueNode{

	Point point;
	int distance;

	public QueueNode(Point point,int distance){
		this.point = point;
		this.distance=distance;
	}
}
