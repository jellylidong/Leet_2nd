import java.util.HashMap;

/*Given an unsorted array of integers, 
 * find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. 
Return its length: 4.

Your algorithm should run in O(n) complexity.*/

/*my analysis
 * method 1 use hashmap, hashmap contiains the key 
 * and the lengthe of the sequence starts with the key
 * for each n in nums, if n is not in the hashmap
 * first check if its right node n+1 is in the hashmap
 * if yes, hash.put(n, hash.get(n+1)+1)
 * else hash.put(n, 1);
 * 
 * then check and update all left node that is in the hashmap 
 * by adding their values by node n's value
 * 
 * this method uses O(n^2)*/

/*method 2 use hashmap that node contains
 * the key and the length of the sequence contains this node
 * for each node that is not in the hashmap
 * 1.first check its left length left
 * 2.than its right length right
 * 3.sum = left + 1 + right
 * 4.update max value by max(max, sum);
 * 5.update boundary of this sequence:
 * hash.put(n-left, sum), hash.put(n+1eft, sum)
 * than repeat from step 1
 * this works because for any single node that has no left or right,
 * its value will be 1
 * for other cases, both left boundary and right boundary will be updated
 * notice before update boundary we already get the current value
 * let's see this case
 * 2,2,_,1, when the number of third position comes, it will change to
 * 4,2,4,4
 * since we will ignore the keys that are already in hashmap,
 * so this won't influence us to get the correct max 
 * */
public class Problem128LongestConsecutiveSequence {
	public int longestConsecutive_1(int[] nums) {
        HashMap<Integer, Integer> hash = new HashMap<>();
        int max = 0;
        for(int i = 0; i < nums.length; i++){
            int n = nums[i];
            if(hash.containsKey(n))
                continue;
            if(hash.containsKey(n+1)){
                hash.put(n, hash.get(n+1) + 1);
            }
            else
                hash.put(n, 1);
                
            if(hash.containsKey(n-1)){
                int offset = hash.get(n);
                while(hash.containsKey(n-1)){
                    hash.put(n-1, hash.get(n-1) + offset);
                    n--;
                }
            }
            
        }
        
        for(int n: hash.keySet())
            max = Math.max(max, hash.get(n));
            
        return max;
        
    }
	
	public int longestConsecutive_2(int[] nums) {
		int max = 0;
		HashMap<Integer, Integer> hash = new HashMap<>();
		for(int n: nums){
			if(hash.containsKey(n))
				continue;
			int left = hash.containsKey(n-1)? hash.get(n-1):0;
			int right = hash.containsKey(n+1)? hash.get(n+1):0;
			
			int sum = left + 1 + right;
			max = Math.max(max,  sum);
			
			hash.put(n, sum);
			hash.put(n-left, sum);
			hash.put(n+right, sum);
		}
		return max;
	}
}
