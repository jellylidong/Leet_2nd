
public class TEST {
	public static boolean exist(char[][] board, String word) {
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(exist(board, i, j, word, 0));
                    return true;
            }
        }
        return false;
    }
    
    public static boolean exist(char[][] board, int x, int y, String word, int i){
        if(i == word.length())
            return true;
        if( x < 0 || y < 0 || x == board.length || y == board[0].length)
            return false;
        if(board[x][y] != word.charAt(i))
            return false;
        board[x][y] ^= 256;
        boolean res = exist(board, x, y+1, word, i+1) ||
                      exist(board, x, y-1, word, i+1) ||
                      exist(board, x-1, y, word, i+1) ||
                      exist(board, x+1, y, word, i+1);
        board[x][y] ^= 256;
        return res;
    }
    
    public static void main(String[] args){
    	Trie trie = new Trie();
    }
}
