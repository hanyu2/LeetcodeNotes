package d831;

public class ReconstructOriginalDigitsFromEnglish {
	public String originalDigits(String s) {
        int[] res = new int[10];
        int[] aph = new int[26];
        for(char c : s.toCharArray()){
            aph[c - 'a']++;
        }
        res[0] = aph[25];
        delete(aph, "zero", res[0]);
        res[8] = aph['g' - 'a'];
        delete(aph, "eight", res[8]);
        res[2] = aph['w' - 'a'];
        delete(aph, "two", res[2]);
        res[3] = aph['t' - 'a'];
        delete(aph, "three", res[3]);
        res[6] = aph['x' - 'a'];
        delete(aph, "six", res[6]);
        res[7] = aph['s' - 'a'];
        delete(aph, "seven", res[7]);
        res[4] = aph['r' - 'a'];
        delete(aph, "four", res[4]);
        res[5] = aph['f' - 'a'];
        delete(aph, "five", res[5]);
        res[9] = aph['i' - 'a'];
        delete(aph, "nine", res[9]);
        res[1] = aph['e' - 'a'];
        delete(aph, "one", res[1]);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < res[i]; j++){
                sb.append(i+"");
            }
        }
        return sb.toString();
    }
    public void delete(int[] ch, String s, int n){
        if(n <= 0){
            return;
        }
        for(int i = 0; i < n; i++){
	        for(char c : s.toCharArray()){
	            ch[c - 'a']--;
	        }
        }
    }
    public static void main(String[] args) {;
		ReconstructOriginalDigitsFromEnglish rodfe = new ReconstructOriginalDigitsFromEnglish();
		System.out.println(rodfe.originalDigits("zerozero"));
	}
}
