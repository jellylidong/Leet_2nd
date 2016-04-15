
/*Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.*/

/*my analysis
* the key idea is to
* split s1 into 2: s11, s12
* split s2 into 2: s22, s23
* then compare
* see more details in the code*/

public class Problem87_Scramble_String {
	public boolean isScramble(String s1, String s2) {
        if(s1.equals(s2))
            return true;
        int l1 = s1.length();
        int l2 = s2.length();
        if(l1 != l2)
            return false;
        int[] table = new int[26];
        for(int i = 0; i < l1; i++){
            table[s1.charAt(i) - 'a']++;
            table[s2.charAt(i) - 'a']--;
        }
        for(int i = 0; i < 26; i++){
            if(table[i] != 0)
                return false;
        }
        
        for(int i = 1; i < l1; i++){
            String s11 = s1.substring(0, i);
            String s12 = s1.substring(i);
            String s21 = s2.substring(0, i);
            String s22 = s2.substring(i);
            String s23 = s2.substring(0, l1-i);//very easy to ignore this split, l1-i  can == i or not
            String s24 = s2.substring(l1-i);
            boolean res = (isScramble(s11, s21) && isScramble(s12, s22)) ||
                          (isScramble(s11, s23) && isScramble(s12, s24)) ||
                          (isScramble(s11, s22) && isScramble(s12, s21)) ||
                          (isScramble(s11, s24) && isScramble(s12, s23));

            //note, once find, must return so that we don't have to search further,
            // otherwise we can get wrong result
            if(res)
                return true;
        }
        return false;
    }
}
