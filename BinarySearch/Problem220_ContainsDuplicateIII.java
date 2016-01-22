import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by vcoder on 1/21/16.
 */

/*Given an array of integers, find out whether there are
two distinct indices i and j in the array such that
the difference between nums[i] and nums[j] is at most t
and the difference between i and j is at most k.*/

/*my analysis
*
* method 1 use treeset
* treeset is a kind of set
* it has two special method
* treeSet.floor(num) will return the max value that <= num from the whole set
* treeSet.ceiling(num) will return the min value that >= num from  the whole set
* so we can keep a set of size k and a
* floor(nums[i]+t) and a ceil(nums[i]-t)
* if floor >= nums[i] or ceil <= nums[i]
* then we get it
*
* method 2 use bucket sort
* we put each number into different bucket
* with a window size t+1 (in case t = 0, we use t+1)
* i.e.
* long newNum = nums[i]-Integer.MIN_VALUE;
* long bucket = newNUm/(t+1)
* then map.put(bucket, newNum)
* for each number, if it's already belong to one of the bucket
* of nums[i] + t >= map.get(bkt+1)
* or map.get(bkt-1) + t >= nums[i]
* we get it
* also keep the map size <= k*/

public class Problem220_ContainsDuplicateIII {
    public boolean containsNearbyAlmostDuplicate_treeSet(int[] nums, int k, int t){
        if(nums.length == 0 || k <= 0 || t < 0)
            return false;
        TreeSet<Integer> ts = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){
            Integer floor = ts.floor(nums[i]+t);
            Integer ceil = ts.ceiling(nums[i]-t);

            if((floor != null && floor >= nums[i]) || (ceil != null && ceil <= nums[i]))
                return true;
            if(i >= k)
                ts.remove(nums[i-k]);
            ts.add(nums[i]);
        }
        return false;
    }

    public boolean containsNearbyAlmostDuplicate_bkt(int[] nums, int k, int t) {
        if(nums.length == 0 || k <= 0 || t < 0)
            return false;
        HashMap<Long, Long> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            long newNum = (long)nums[i]-Integer.MIN_VALUE;
            long bucket = newNum/((long)t+1);

            if(map.containsKey(bucket) ||
                    (map.containsKey(bucket-1) && newNum - map.get(bucket-1) <= t) ||
                    (map.containsKey(bucket+1) && map.get(bucket+1) - newNum <= t))
                return true;
            //note here:
            //if we use i to judge the size, it does not matter
            //the order of add and remove
            //if we use map.size()
            //we must first remove, than add the new one
            //other wise the new one would never be used
            if(map.size() == k){
                long n = (long)nums[i-k] - Integer.MIN_VALUE;
                long b = n/((long)t+1);
                map.remove(b);
            }

            map.put(bucket, newNum);
        }
        return false;
    }
}
