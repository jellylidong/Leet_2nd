/*Given a string, determine if it is a palindrome, 
 * considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? 
This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.

Show Company Tags
Show Tags
Show Similar Problems
*/

/*my analysis
 * straightforward
 * just compare s and reverse(s)
 * need to do some filter and up2low things before compare*/
public class Problem125_ValidPalindrome {
	public boolean isPalindrome(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(isAN(c))
                sb.append(toLower(c));
        }
        String s1 = sb.toString();
        String s2 = sb.reverse().toString();
        return s1.equals(s2);
    }
    
    public boolean isAN(char c){
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
    
    public char toLower(char c){
        if(c >= 'A' && c <= 'Z')
            return (char)(c+'a'-'A');
        else return c;
    }
}
