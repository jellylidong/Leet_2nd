import java.util.ArrayList;
import java.util.List;

/**
 * Created by vcoder on 2/10/16.
 */
public class Problem51N_Queens {
    static List<List<String>> ans;
    public static List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        boolean[] colSet = new boolean[n];
        boolean[] rowSet = new boolean[n];
        boolean[] digSet = new boolean[2*n-1]; //j-i+n-1


        ArrayList<String> sol = new ArrayList<>();
        solve(n, sol, colSet, rowSet, digSet);

        return ans;
    }

    public static void solve(int n, ArrayList<String> sol, boolean[] colSet, boolean[] rowSet, boolean[] digSet){
        if(sol.size() == n){
            ans.add(new ArrayList<>(sol));
            return;
        }

        int i = sol.size();
        if(rowSet[i])
            return;
        for(int j = 0; j < n; j++){


            if(!colSet[j] && !rowSet[i] && !digSet[j-i+n-1]){
                String str = "";
                for(int k = 0; k < n; k++){
                    if(k == j){
                        str += "Q";
                        colSet[j] = true;
                        rowSet[i] = true;
                        digSet[j-i+n-1] = true;
                    }
                    else
                        str += ".";
                }
                sol.add(str);
                solve(n, sol, colSet, rowSet, digSet);
                sol.remove(sol.size() - 1);
                colSet[j] = false;
                rowSet[i] = false;
                digSet[j-i+n-1] = false;
            }
        }
    }

    public static void main(String[] args){
        List<List<String>> ans = solveNQueens(9);
        tools.print2dList(ans);
    }
}
