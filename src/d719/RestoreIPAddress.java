package d719;

import java.util.ArrayList;
import java.util.List;

public class RestoreIPAddress {
	//1.i, j, k不要忘记检查<n
	//2.j, k判断条件写成了j < j + 4
	//3.检查validation，忘了判断长度，
	//4.validation忘了判断长度是否等于0就判断第一个字符是不是0，越界
	//5.判断长度应该在parseInteger之前，否则字符串会很大，无法parse
	public static List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s.length() < 4){
            return res;
        }
        int n = s.length();
        for(int i = 0; i < 3; i++){
            String s1 = s.substring(0, i + 1);
            for(int j = i + 1; j < i + 4 && j < n; j++){
                String s2 = s.substring(i + 1, j + 1);
                for(int k = j + 1; k < j + 4 && k < n; k++){
                    String s3 = s.substring(j + 1, k + 1);
                    String s4 = s.substring(k + 1);
                    if(valid(s1) && valid(s2) && valid(s3) &&valid(s4)){
                        res.add(s1 +"."+s2 +"."+s3 +"."+s4);
                    }
                }
            }
        }
        return res;
    }
    public static boolean valid(String s){
        if(s.length() == 0 || (s.charAt(0) == '0' && s.length() != 1) || s.length() > 3 || Integer.parseInt(s) > 255){
            return false;
        }
        return true;
    }
    public static void main(String[] args) {
    	//restoreIpAddresses("255255255255");
		//restoreIpAddresses("1111");
		restoreIpAddresses("0279245587303");
	}
}
