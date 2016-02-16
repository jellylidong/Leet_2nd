///*Given a string S,
// *  you are allowed to convert it to a palindrome by adding characters in front of it.
// *  Find and return the shortest palindrome you can find by performing this transformation.
//
//For example:
//
//Given "aacecaaa", return "aaacecaaa".
//
//Given "abcd", return "dcbabcd".*/
//
///*my analysis
// *
// * method_1 O(n2)
// * the basic idea is
// * first find the longest palindrome begins from i=0,
// * then add the left part's reversion to the beginning of original string
// * then we can get the result
// * for example, "abacd", the longest palindrom is "aba"
// * the left part is "cd", its reversion is "dc"
// * so results is "dc" + "abacd
// * for the code, we only need to go from 0(inclusive) to len/2(inclusive),
// * for(int i = 0; i <= len/2;)
// * this range is where the middle of the longest palindrom could be
// * so we don't need to check the other part
// * in the loop
// * we first need to check if next char == current char
// * lo = hi = i;
// * while(hi < len-1 && s.charAt(hi) == s.charAt(hi+1))
// * 		hi++;
// * this can avoid check palindroms that has even length
// * fro example "abba"
// * if we do the loop above, we can start from tow a, otherwise we can't find this palindrome
// * besides
// * i = hi+1
// * then while s.charAt(lo-1) == s.charAt(hi+1)
// * lo--; hi++
// * after while done, update longest = hi-lo+1
// * after all loop done
// * return reverse(s.substring(longest, len)) + s
// *
// * method_2 KMP O(n)
// * still don't understand*/
//public class problem214_ShortestPalindrome {
//	public String shortestPalindrome_1(String s) {
//		int l = s.length();
//		if(l <= 1)
//			return s;
//		int lo = 0;
//		int hi = 0;
//		int longest = 0;
//		for(int i = 1; i <= l/2;){
//			lo = hi = i;
//			while(hi < l-1 && s.charAt(hi) == s.charAt(hi+1))
//				hi++;
//			i = hi+1;
//			while(lo > 0 && hi < l-1 && s.charAt(hi+1) == s.charAt(lo-1)){
//				hi++;
//				lo--;
//			}
//			if(lo == 0 && longest < hi-lo+1)
//				longest = hi-lo+1;
//		}
//		String s_rev = new StringBuilder(s.substring(longest, l)).reverse().toString();
//		return s_rev + s;
//	}
//
//	public String shortestPalindrome_2(String s) {
//}
