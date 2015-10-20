
public class Problem27_Remove_Element {
	public int removeElements(int[] nums, int val){
		int tailId = nums.length-1;
		for(int i = 0; i <= tailId; i++){
			if(nums[i] == val){
				while(i < tailId && nums[tailId] == val){
					tailId--;
				}
				if(i == tailId){
					tailId--;
					break;
				}
				int tmp = nums[i];
				nums[i] = nums[tailId];
				nums[tailId] = tmp;
				tailId--;
			}
		}
		return tailId + 1;
	}
	//hard to handled cases
	//[0,0] 0;
	//[1], 1;
	//[2], 3;
}
