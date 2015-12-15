import java.util.HashMap;
import java.util.HashSet;

/*Given a string, find the length of the longest substring without repeating characters. 
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc", 
 * which the length is 3. 
 * For "bbbbb" the longest substring is "b", with the length of 1.*/

/*my analysis*/

/*method_1
 * use a hashset to record if ths character exist in current substring
 * left is the beginning of substring
 * right is endding is substring
 * iterate every char by right pointer,
 * every iteration, if s[right] not in hashset, put it in the hashset
 * and update max by max = Math.max(max, right-left)
 * otherwise, go from left, hash.remove(s[left]), left++
 * until the duplicate char is removed.*/

/*method_2
 * the only difference is use a hashmap instead that of method_1
 * we use hashmap to update the left pointer so that we don't need to
 * change it one by one.
 * method_1 uses O(2n) time but O(n) space
 * method_2 used O(n) time but O(2n) space*/

/*method_3
 * similar to method_2, use an array[256] instead*/
public class Problem3_Longest_SubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring_1(String s) {
		HashSet<Character> hash = new HashSet<>();
		int max = 0;
		int left = 0;
		int right = 0;
		while(right < s.length()){
			if(!hash.contains(s.charAt(right))){
				hash.add(s.charAt(right));
				right++;
				max =Math.max(max, right-left);
			}
			//if s.charAt(left) == s.charAt(right), not using this and using the else part can lead to dead loop
			//e.g. "abca"
			else if(s.charAt(left) == s.charAt(right)){
				hash.remove(s.charAt(left));
				left++;
			}
			else{
				while(s.charAt(left) != s.charAt(right)){
					hash.remove(s.charAt(left));
					left++;
				}
			}
		}
		return max;
	}
	
	public int lengthOfLongestSubstring_2(String s) {
        if(s.length() == 0)
            return 0;
        HashMap<Character, Integer> hash = new HashMap<>();
        int max = 0;
        for(int left = 0, right = 0; right < s.length(); right++){
            if(hash.containsKey(s.charAt(right))){
                left = Math.max(left, hash.get(s.charAt(right)) + 1);
            }
            hash.put(s.charAt(right), right);
            max = Math.max(max, right-left+1);
        }
        return max;
    }
	
	 public int lengthOfLongestSubstring_3(String s) {
	        if(s.length() == 0)
	            return 0;
	        int[] arr = new int[256];
	        int max = 0;
	        int left = 0, right = 0;
	        for(int i = 0; i < arr.length; i++)
	            arr[i] = -1;
	        while(right < s.length()){
	            char c = s.charAt(right);
	            if(arr[c] == -1){
	                arr[c] = right;
	                right++;
	            }
	            else if(s.charAt(left) == s.charAt(right)){
	                left++;
	                arr[c] = -1;
	            }
	            else{
	                while(s.charAt(left) != s.charAt(right)){
	                    arr[s.charAt(left)] = -1;
	                    left++;
	                }
	            }
	            // System.out.println(left+" "+ right);
	            max = Math.max(max, right-left);
	        }
	        return max;
	    }
	 
	 public int lengthOfLongestSubstring_3_optimized(String s) {
	        if(s.length() == 0)
	            return 0;
	        // HashMap<Character, Integer> hash = new HashMap<>();
	        
	        int max = 0;
	        
	        int[] arr = new int[256];
	        for(int i = 0; i < arr.length; i++)
	            arr[i] = -1;
	            
	        for(int left = 0, right = 0; right < s.length(); right++){
	            // if(hash.containsKey(s.charAt(right))){
	            if(arr[s.charAt(right)] != -1){
	                // left = Math.max(left, hash.get(s.charAt(right)) + 1);
	                left = Math.max(left, arr[s.charAt(right)] + 1);
	            }
	            // hash.put(s.charAt(right), right);
	            arr[s.charAt(right)] = right;
	            max = Math.max(max, right-left+1);
	        }
	        return max;
	    }
}
