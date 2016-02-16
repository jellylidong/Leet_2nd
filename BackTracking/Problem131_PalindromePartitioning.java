import java.util.ArrayList;
import java.util.List;

/**
 * Created by vcoder on 2/11/16.
 */

/*iven a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

For example, given s = "aab",
Return

  [
    ["aa","b"],
    ["a","a","b"]
  ]*/
public class Problem131_PalindromePartitioning {
    List<List<String>> ans;
    public List<List<String>> partition(String s) {
        ans = new ArrayList<>();
        List<String> sol = new ArrayList<>();
        solve(s, sol);
        return ans;
    }

    public boolean isP(String s){
        for(int i = 0; i < s.length()/2; i++){
            if(s.charAt(i) != s.charAt(s.length()-1-i))
                return false;
        }
        return true;
    }

    public void solve(String s, List<String> sol){

        for(int i = 1; i <= s.length(); i++){
            if(isP(s.substring(0, i))){
                sol.add(s.substring(0, i));
                if(i == s.length())
                    ans.add(new ArrayList<>(sol));
                else
                    solve(s.substring(i), sol);
                sol.remove(sol.size() - 1);
            }
        }
    }
}
