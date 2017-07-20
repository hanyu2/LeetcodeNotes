package d719;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {
	public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
        if(n == 0){
            return res;
        }
        search(n, n, "", res);
        return res;
    }
    public void search(int left, int right, String s, List<String> res){
        if(left == 0 && right == 0){
            res.add(s);
            return;
        }
        if(left > 0){
            search(left - 1, right, s + "(", res);
        }
        if(right > left){
            search(left, right - 1, s + ")", res);
        }
    }
}
