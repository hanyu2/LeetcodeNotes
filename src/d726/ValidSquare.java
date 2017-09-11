package d726;

public class ValidSquare {
	public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        int len1 = (p1[0]-p2[0])*(p1[0]-p2[0])+(p1[1]-p2[1])*(p1[1]-p2[1]);
        int len2 = (p1[0]-p3[0])*(p1[0]-p3[0])+(p1[1]-p3[1])*(p1[1]-p3[1]);
        int len3 = (p1[0]-p4[0])*(p1[0]-p4[0])+(p1[1]-p4[1])*(p1[1]-p4[1]);
        int max = Math.max(len1, Math.max(len2, len3));
        if(max == 0){
            return false;
        }
        if(max == len1){
            if(len2 == len3 && len1 == len2 + len3){
                return true;
            }
        }
        if(max == len2){
            if(len1 == len3 && len2 == len1 + len3){
                return true;
            }
        }
        if(max == len3){
            if(len1 == len2 && len3 == len1 + len2){
                return true;
            }
        }
        return false;
    }
	public static void main(String[] args) {
		int[] p1 = {0,1};
		int[] p2 ={1,2};
		int[] p3 ={0,2};
		int[] p4 ={0,0};
		validSquare(p1, p2, p3, p4);
	}
}
