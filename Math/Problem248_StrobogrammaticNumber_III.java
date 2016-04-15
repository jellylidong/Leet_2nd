/**
 * Created by vcoder on 4/15/16.
 */

/*A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,
Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:
Because the range might be a large number, the low and high numbers are represented as string.*/


/*my analysis
*try to generate strobogrammatic string from low.length to high.length (both invlusive)
 * for each number in the range, count++
  *
  * so how to try
  * for each try, update two layer by two layers
  * for exmaple to generate s strobogrammatic string with leng i,
  * we store it into char array cs[i]
  * update cs[0] and cs[i-1]
  * update cs[1] and cs[i-2]
  * ...
  * dfs until satrt_index > end_index
  * it means we finish generating the string, then we can see if it's in the range
  *
  * note there are also some conditions we need to judge so that we can decide to continue dfs or not
  * see the code comment
  * */

public class Problem248_StrobogrammaticNumber_III {
    char[][] pairs = {{'0', '0'}, {'1', '1'}, {'6', '9'}, {'8', '8'}, {'9', '6'}};
    int count = 0;

    public int strobogrammaticInRange(String low, String high) {
        int loLen = low.length();
        int hiLen = high.length();

        for(int i = loLen; i <= hiLen; i++){
            dfs(low, high, new char[i], 0, i-1);
        }

        return count;
    }

    public void dfs(String low, String high, char[] cs, int s, int e){
        if(s > e){
            String ss =new String(cs);
            if((ss.length() == low.length() && ss.compareTo(low) < 0) ||
                    (ss.length() == high.length() && ss.compareTo(high) > 0)){
                return;
            }
            // System.out.println(ss);
            count++;
            return;
        }

        for(char[] p: pairs){
            cs[s] = p[0];
            cs[e] = p[1];
            //number start with 0 but not 0 is invalid
            if(cs.length != 1 && cs[0] == '0')
                continue;
            //if s < e, we can continue dfs
            //if s == e, this number shuold be 6 or 9 because it's not strobogrammatic
            if(s < e || (s == e && p[0] == p[1]))
                dfs(low, high, cs, s+1, e-1);
        }
    }
}
