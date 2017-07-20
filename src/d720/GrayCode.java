package d720;

import java.util.ArrayList;
import java.util.List;

public class GrayCode {
	public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<Integer>();
        res.add(0);
        if(n == 0){
            return res;
        }
        res.add(1);
        for(int i = 2; i <= n; i++){
            int base = (int)Math.pow(2, i-1);
            for(int j = base - 1; j >= 0; j--){
                res.add(base + res.get(j));
            }
        }
        return res;
    }
}
