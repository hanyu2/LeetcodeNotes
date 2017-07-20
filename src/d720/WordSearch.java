package d720;

public class WordSearch {
	//set完*忘记还原
	//忘记了返回true的判断
	static int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public static boolean exist(char[][] board, String word) {
        if(word.length() == 0 || board.length == 0){
            return false;
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == word.charAt(0)){
                    if(search(word, 0, i, j, board)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static boolean search(String word, int index, int i, int j, char[][] board){
        if(index == word.length()){
            return true;
        }
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '*' || board[i][j] != word.charAt(index)){
            return false;
        }
        char c = board[i][j];
        board[i][j] = '*';
        for(int[] dir : directions){
            int x = i + dir[0];
            int y = j + dir[1];
            if(search(word, index + 1, x, y, board)){
                return true;
            }
        }
        board[i][j] = c;
        return false;
    }
    public static void main(String[] args) {
		char board[][] = { { 'A', 'B', 'C', 'E' }, 
				           { 'S', 'F', 'C', 'S' }, 
				           { 'A', 'D', 'E', 'E' } };
		System.out.println(exist(board, "ABCCED"));
	}
}
