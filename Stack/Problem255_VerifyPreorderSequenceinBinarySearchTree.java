import java.util.Stack;

/*Given an array of numbers, verify whether it is the correct 
 * preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?*/

/*my analysis
 * method 1 divide and conquer(this method is easy to understand but run time is poor)
 * for any tree or subtree's preorder traversal
 * it must be composed by three parts
 * root,[left subtree preorder], [right subtring preorder]
 * what we need to do is to find the index of start of right subtree
 * make sure all numbers in left subtree < node
 * all numbers in right subtree > node
 * then return verify(left subtree) && verify(right subtree)*/

/*method 2 use stack O(n) space
 * for any tree or subtree's preorder traversal
 * it must be composed by thre parts
 * root,[left subtree preorder], [right subtring preorder]
 * node > left subtree and node < right subtree
 * we use a stack to keep track of the smallest number of any subtree
 * if current value > stk.peek(), it means we are staring traverse a right sub tree
 * so keep pop stk until the peek value of stk > current value, 
 * which means the peek value is the root value of current subtree
 * meanwhile, we need to keep updating the minimum value = stk.pop()
 * the last poped value is the fist node of left substree,
 * which means later values should not < this value
 * otherwise it's not preorder traversal results
 * so just push it to the stack*/

/*method 3
 * notice that the method 2 uses O(n) space for the worst case
 * we can get the min value from preorder array instead of stack
 * we can use an integer i to keep track of the index of min value
 * which uses O(1) space*/
public class Problem255_VerifyPreorderSequenceinBinarySearchTree {
	
	public boolean verifyPreorder_1(int[] preorder) {
        return verify(preorder, 0, preorder.length-1);
    }
    
    private boolean verify(int[] preorder, int ps, int pe){
        if(ps >= pe)
            return true;
        
        int rightStart = ps+1;
        int leftStart = ps+1;
        while(rightStart <= pe && preorder[rightStart] < preorder[ps])
            rightStart++;
        if(rightStart <= pe){
            for(int i = rightStart; i <= pe; i++)
                if(preorder[i] <= preorder[ps])
                    return false;
        }
        boolean left = verify(preorder, leftStart, rightStart-1);
        boolean right = verify(preorder, rightStart, pe);
        return left && right;
    }
    
    public boolean verifyPreorder_2(int[] preorder) {
    	Stack<Integer> stk = new Stack<>();
    	int min = Integer.MIN_VALUE;
    	for(int i = 0; i < preorder.length; i++){
    		int num = preorder[i];
    		if(num < min)
    			return false;
    		while(!stk.isEmpty() && num > stk.peek())
    			min = stk.pop();
    		stk.push(num);
    	}
    	return true;
    }
    
    public boolean verifyPreorder_3(int[] preorder) {
    	int min = Integer.MIN_VALUE;
    	int i = -1;
    	for(int n: preorder){
    		if(n < min)
    			return false;
    		while(i >= 0 && n > preorder[i]){
    			min = preorder[i];
    			i--;
    		}
    		i++;
    		preorder[i] = n;
    	}
    	return true;
    }
}
