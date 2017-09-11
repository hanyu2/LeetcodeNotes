package d827;

import java.util.HashSet;
import java.util.Set;

public class MinimumGeneticMutation {
	int step = Integer.MAX_VALUE;
    public int minMutation(String start, String end, String[] bank) {
        Set<String> set = new HashSet<String>();
        for(String s : bank){
            set.add(s);
        }
        if(bank.length == 0 || !set.contains(end)){
            return -1;
        }
        char[] ch = start.toCharArray();
        char[] alt = new char[]{'A','C','G','T'};
        Set<String> visited = new HashSet<String>();
        visited.add(start);
        search(ch, set, alt, start, 0, end, visited);
        return step == Integer.MAX_VALUE ? -1 : step;
    }
    public void search(char[] ch, Set<String> set, char[] alt, String s, int st, String end, Set<String> visited){
        for(int i = 0; i < 8; i++){
            char old = ch[i];
            for(char c : alt){
                ch[i] = c;
                String newStr = String.valueOf(ch);
                if(newStr.equals(end)){
                    st++;
                    step = Math.min(step, st);
                    return;
                }
                if(visited.contains(newStr)){
                    continue;
                }else{
                    if(set.contains(newStr)){
                        visited.add(newStr);
                        search(ch, set, alt, newStr, st + 1, end, visited);
                        visited.remove(newStr);
                    }
                }
            }
            ch[i] = old;
        }
    }
    public static void main(String[] args) {
		String[] bank = new String[]{"AAAAAAAA","AAAAAAAC","AAAAAACC","AAAAACCC","AAAACCCC","AACACCCC","ACCACCCC","ACCCCCCC","CCCCCCCA","CCCCCCCC"};
		MinimumGeneticMutation mgm  = new MinimumGeneticMutation();
		System.out.println(mgm.minMutation("AAAAAAAA", "CCCCCCCC", bank));
	}
}
