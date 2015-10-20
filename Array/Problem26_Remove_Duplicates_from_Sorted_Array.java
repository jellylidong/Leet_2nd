
public class Problem26_Remove_Duplicates_from_Sorted_Array {
	public int removeDuplicate(int[] nums){
		int res = 1;
		int l = nums.length;
		if(l < 2)
			return l;
		for(int i = 1; i < l; i ++){
			if(nums[i] != nums[i-1])
				nums[res] = nums[i];
				res++;
		}
		return res;
	}
}
