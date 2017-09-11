package d821;

import java.util.Arrays;

public class NextGreaterElement3 {
	public int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        if(s.length() <= 1){
            return -1;
        }
        char[] ch = s.toCharArray();
        int i = ch.length - 1;
        for(; i > 0; i--){
            if(ch[i] > ch[i - 1]){
                break;
            }
        }
        if(i == 0){
            return -1;
        }
        int j = i + 1;
        int minIndex = i;
        for(;j < ch.length; j++){
            if(ch[j] > ch[i - 1] && ch[j] < ch[minIndex]){
                minIndex = j;
            }
        }
        char temp = ch[i - 1];
        ch[i - 1] = ch[minIndex];
        ch[minIndex] = temp;
        Arrays.sort(ch, i, ch.length);
        String st = String.valueOf(ch);
        long l = Long.parseLong(st);
        if(l > Integer.MAX_VALUE){
            return -1;
        }
        return (int)l;
    }
	public static void main(String[] args) {
		NextGreaterElement3 nge = new NextGreaterElement3();
		System.out.println(nge.nextGreaterElement(12));
	}
}
