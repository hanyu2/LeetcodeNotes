package d712;

public class UglyNumber2 {
	public static int nthUglyNumber(int n) {
		int[] uglyNumbers = new int[n];
	    uglyNumbers[0] = 1;
	    int index2 = 0, index3 = 0, index5 = 0;
	    
	    for (int i = 1; i < n; i++) {
	        // generate ugly number by multiply all the factors
	        uglyNumbers[i] = Math.min(uglyNumbers[index2] * 2, Math.min(uglyNumbers[index3] * 3, uglyNumbers[index5] * 5));
	        
	        // bump up index for the current minimum ugly number 
	        if (uglyNumbers[i] == uglyNumbers[index2] * 2) index2++;
	        if (uglyNumbers[i] == uglyNumbers[index3] * 3) index3++;
	        if (uglyNumbers[i] == uglyNumbers[index5] * 5) index5++;
	    }
	    return uglyNumbers[n - 1];
    }
	public static void main(String[] args) {
		System.out.println(nthUglyNumber(11));
	}
}
