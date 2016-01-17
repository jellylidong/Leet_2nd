import java.util.Stack;

/*Given a 2D board containing 'X' and 'O', 
 * capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

For example,
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X*/

/*my analysis
 * note that only the node that are connected with boundary node with value O
 * won't be changed to X, so we only need to iterate from boundary and label all
 * nodes that has connection to boundary 0 nodes
 * 1.iterate both left most and right most col
 * 2.iterate both the up most and down most row
 * 
 * method 1 uses recursion, this method 
 * this method work well when the array is small, but when the array
 * get very large, it can lead to stack overflow exceptions
 * 
 * to solve this, note that most recursion can be transformed to
 * BFS or DFS
 * for example DFS:
 * first push the node is 0;
 * while the stack is not empty
 * pop one node
 * then check all it's neighbor nodes that meet the condition and push it to the stack
 * 
 * !!! this very general and useful method to transform between recursion and BFS/DFS*/
public class Problem130SurroundedRegions {
	public void solve_recursion(char[][] board) {
        if(board.length == 0)
            return;
        for(int i = 0; i < board[0].length; i++){
            mark_recursion(0, i, board);
            mark_recursion(board.length-1, i, board);
        }
        for(int i = 0; i < board.length; i++){
            mark_recursion(i, 0, board);
            mark_recursion(i, board[0].length-1, board);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == 'L')
                    board[i][j] = 'O';
            }
        }   
        
    }
    
    private void mark_recursion(int i, int j, char[][] board){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length)
            return;
        if(board[i][j] == 'O'){
            board[i][j] = 'L';
            mark_recursion(i-1, j, board);
            mark_recursion(i+1, j, board);
            mark_recursion(i, j-1, board);
            mark_recursion(i, j+1, board);
        }
    }
    
    public void solve(char[][] board) {
        if(board.length == 0)
            return;
        for(int i = 0; i < board[0].length; i++){
            mark_iter(0, i, board);
            mark_iter(board.length-1, i, board);
        }
        for(int i = 0; i < board.length; i++){
            mark_iter(i, 0, board);
            mark_iter(i, board[0].length-1, board);
        }
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == 'O')
                    board[i][j] = 'X';
                if(board[i][j] == 'L')
                    board[i][j] = 'O';
            }
        }   
        
    }
    
    private void mark_iter(int i, int j, char[][] board){
        // if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O')
        if(board[i][j] != 'O')
            return;
        // System.out.println("mark");
        Stack<pos> stk = new Stack<>();
        board[i][j] = 'L';
        stk.push(new pos(i, j));
        while(!stk.isEmpty()){
            pos curPos = stk.pop();
            int x = curPos.x;
            int y = curPos.y;
            pos[] ps = {new pos(y+1, x), new pos(y-1, x), new pos(y, x+1), new pos(y, x-1)};
            for(pos p: ps){
                int xx = p.x;
                int yy = p.y;
                if(xx >= 0 && xx < board[0].length && yy >= 0 && yy < board.length && board[yy][xx] == 'O'){
                    board[yy][xx] = 'L';
                    stk.push(p);
                }
            }
        }
    }
    
    private class pos{
        int y;
        int x;
        pos(int yy, int xx){
            y = yy;
            x = xx;
        }
        
    }
}
