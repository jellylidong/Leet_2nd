import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vcoder on 4/14/16.
 */

/*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.*/

/*my analysis
* from two ends layer, two layer by two layer
* more detail see the code*/

public class Problem247_StrobogrammaticNumber_II {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }

    public ArrayList<String> helper(int cur, int len){
        if(cur == 0)
            return new ArrayList<String>(Arrays.asList(""));//must return "", otherwise when n=2, it would return []
        if(cur == 1)
            return new ArrayList<String>(Arrays.asList("0", "1", "8"));

        ArrayList<String> list = helper(cur-2, len);
        ArrayList<String> ans = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            String s = list.get(i);
            if(cur != len)
                ans.add("0" + s + "0");
            ans.add("1" + s + "1");
            ans.add("6" + s + "9");
            ans.add("8" + s + "8");
            ans.add("9" + s + "6");
        }

        return ans;
    }
}
