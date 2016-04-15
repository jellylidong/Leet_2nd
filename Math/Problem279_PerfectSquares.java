import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by vcoder on 4/14/16.
 */

/*Given a positive integer n,
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...)
 which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4;
given n = 13, return 2 because 13 = 4 + 9.

Credits:
Special thanks to @jianchao.li.fighter for adding this problem and creating all test cases.*/



/*my analysis
*
* method 1
* BFS
*
*
* method 2
* DB
* count[i] = Math.min(count[i], count[i-j*j]+1);
* ie i's perfect sqrt number is min value of (i-j*j's ps number) + (j*j's ps number = 1)
* */

public class Problem279_PerfectSquares {
    public int numSquares_method1(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        //count[i] is the Perfect Squares is nuimber i
        int[] count = new int[n+1];


        //first find the Perfect Squares number of i*i, obviously count[i] = 1
        //at the same time we need to save these i*i into arrayList and queue
        //we need to use them in the furtue
        for(int i = 1; i*i <= n; i++){
            q.offer(i*i);
            list.add(i*i);
            count[i*i] = 1;
        }

        //if n is i*i number, we can return 1 directly
        if(count[n] != 0)
            return 1;

        //level order BFS
        while(!q.isEmpty()){
            int size = q.size();
            for(int i = 0; i < size; i++){
                int cur = q.poll();
                for(int k: list){
                    if(cur + k == n)
                        return count[cur] + count[k];
                    else if(cur + k < n){
                        if(count[cur + k] == 0){
                            count[cur + k] =  count[cur] + count[k];
                            q.offer(cur + k);
                        }
                        // we don't need to update count[cur + k] if it's not 0
                        //becuase for i*i = j*j + k*K
                        //for a specific i satisfies the above condition,
                        // there's only one pair(j, k) satisfies it
                        // think about Pythagorean theorem (gougu dingli in Chinese)

                        // else{
                        //     count[cur + k] = Math.min(count[cur + k], count[cur] + count[k]);
                        // }

                    }
                    //cur + k > n, no need to go further of the list
                    else if(cur + k > n){
                        break;
                    }
                }

            }
        }
        return count[n];
    }


    public int numSquares_method2(int n) {
        int[] count = new int[n+1];
        Arrays.fill(count, Integer.MAX_VALUE);
        count[0] = 0;//must initial 0, otherwise for n = 1 would be wrong
        for(int i = 1; i <= n; i++){
            for(int j = 1; j*j <= i; j++){
                count[i] = Math.min(count[i], count[i-j*j]+1);
            }
        }
        return count[n];
    }
}
