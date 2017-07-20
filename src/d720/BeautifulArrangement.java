package d720;

public class BeautifulArrangement {
	int count = 0;
    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        search(used, N, 1);
        return count;
    }
    public void search(boolean[] used, int N, int len){
        if(len == N + 1){
            count++;
            return;
        }
        for(int i = 1; i <= N; i++){
            if(!used[i]){
                if(len % i == 0 || i % len == 0){
                    used[i] = true;
                    search(used, N, len + 1);
                    used[i] = false;
                }
            }
        }
    }
}
