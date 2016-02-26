/*Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.*/


/*my analysis
* if we choose i as the root
* then the number of possible trees are
* numTrees(i-1) * numTrees(n-i)
*
* res[i] means the number of trees of i numbers
* we iterate from 0 to i, note that the process can be symmetry,
* so we can use *2 so that we only need to iterate from 0 to i/2
* but don't forget that the i can be an odd,
* so we also need to add the number of trees in this case
* because the *2 cases can't cover this case*/
public class Problem96_Unique_Binary_Search_Trees {
    public int numTrees(int n) {
        if(n <= 0)
            return n;
        int[] res = new int[n+1];
        res[0] = 1;
        res[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 1; j <= i/2; j++)
                res[i] += res[j-1] * res[i-j] * 2; //note: not n-j, it's i-j !!!!
            if((i&1) == 1)
                res[i] += res[i/2]*res[i/2];
        }
        return res[n];
    }
}
