package d713;

public class WiggleSubsequence {
	//一开始想用一个数组存每个元素到这的为增的长度和减的长度
	//因为增减只依赖于前一个数字，不需要记录之前所有的数字
	public int wiggleMaxLength(int[] a) {
        if(a.length<2) return a.length;
      
      int start=1;
      while( (start<a.length) && (a[start] == a[start-1]) )
          start++;
      if(start==a.length)
           return 1;
       
      boolean increasing = a[start]>a[0];   // denoting if we are expecting increased relative to prev
      int prev = a[0], maxLen=1;
      for(int i=start; i<a.length; i++){
          if ( (increasing && (a[i] >prev)) || (!increasing && (a[i] < prev) ) ) {
                  increasing = !increasing;
                  maxLen++;
          }
          prev = a[i];
      }
      return maxLen;
   }
}
