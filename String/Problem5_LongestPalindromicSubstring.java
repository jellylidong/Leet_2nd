/*Given a string S, find the longest palindromic substring in S.
 *  You may assume that the maximum length of S is 1000, 
 *  and there exists one unique longest palindromic substring.
*/

/*my analysis
 * note that the length of substring can be even or odd
 * so we need to use an updataBound(s, left, right) function to update substring's bound 
 * for both even and odd substrings
 * in the updateBound function
 * we keep updating the bound while(in the range of s.length and s[left] == s[right)
 * after updating bound done, update the maxLength using the new left and right*/
public class Problem5_LongestPalindromicSubstring {
	int maxL = 0;
	int lo = 0;
	public String longestPalindrome(String s) {
		for(int i = 0; i < s.length(); i++){
			updateBound(s, i, i); // when substring length is odd
			updateBound(s, i, i+1); // when substring length is even
		}
		return s.substring(lo, lo+maxL);
	}
	
	public void updateBound(String s, int left, int right){
		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
			left--;
			right++;
		}
		if(maxL < (right-1) - (left+1) + 1){
			maxL = (right-1) - (left+1) + 1;
			lo = left+1;
		}
			
	}
}
