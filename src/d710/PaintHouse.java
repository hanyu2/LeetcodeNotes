package d710;

public class PaintHouse {
	//类似paint fense
	//paint fense需要记录两个值， 这个只需要记录上一个房子是什么颜色
	//然后下一个房子避免同色
	public int minCost(int[][] costs) {
        if(costs.length == 0){
            return 0;
        }
        int [][] res = new int[costs.length][3];
        res[0][0] = costs[0][0];
        res[0][1] = costs[0][1];
        res[0][2] = costs[0][2];
        for(int i = 1; i < costs.length; i++){
            costs[i][0] = Math.min(costs[i - 1][1], costs[i - 1][2]) + costs[i][0];
            costs[i][1] = Math.min(costs[i - 1][0], costs[i - 1][2]) + costs[i][1];
            costs[i][2] = Math.min(costs[i - 1][0], costs[i - 1][1]) + costs[i][2];
        }
        int len = costs.length;
        return Math.min(costs[len - 1][0], Math.min(costs[len - 1][1], costs[len - 1][2]));
    }
}
