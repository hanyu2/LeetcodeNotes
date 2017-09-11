package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PowerNumbers {
	long getPowerNumber(long index) {
        long min = Long.MAX_VALUE;
        List<Integer> exps = new ArrayList<Integer>(Arrays.asList(2));  //store exp of each number
        for(long i = 0; i <= index; i++){                               //iterate n times
            List<Integer> minIndices = new ArrayList<Integer>();
            if(exps.get(exps.size() - 1) > 2) exps.add(2);
            min = Long.MAX_VALUE;
            for(int j = 0; j < exps.size(); j++){                       //iterate all exps
                long res = (long)Math.pow(j + 2, exps.get(j));
                if(res < min){
                    min = res;
                    minIndices.clear();
                    minIndices.add(j);
                }else if(res <= min){
                    minIndices.add(j);
                }
            }
            for(int j = 0; j < minIndices.size(); j++){                       //check and update exps
                minIndices.set(minIndices.get(j), exps.get(minIndices.get(j)) + 1);
            }
        }
        return min;
    }

	public static void main(String[] args) {
		PowerNumbers pn = new PowerNumbers();
		System.out.println(pn.getPowerNumber(1));
	}
}
