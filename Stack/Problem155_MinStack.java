import java.util.Stack;

/*Design a stack that supports push, pop, top, 
 * and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.*/

/*my analysis
 * method 1
 * use two stacks, one is used to store values,
 * the other is used to keep track of the min values
 * note: both push and pop and push can change min value
 * especially pop, we need to change the min value back to current stack_min.peek()
 * when stack is not empty after pop, if stack_min is empty, min = Integer.MAX_VALUE
 * 
 * method 2: remeber the code!!!!!!!!!!!
 * use only one stack, knid of tricky
 * for each push, if x <= min value 
 * first we need to push this min to the stack
 * then update min value
 * then push what we normally push
 * 
 * for each pop, 
 * if stack.peek() == min
 * first, we need to pop it :stack.pop();
 * and update min = stack.peek();
 * then stack.pop()
 * if not just stack.pop();
 * after pop, if stack is empty
 * change min back to Integer.MAX_VALUE
 * 
 * */
public class Problem155_MinStack {
	
//	//METHOD 1
//	int min = Integer.MAX_VALUE;
//    Stack<Integer> sv = new Stack<>();
//    Stack<Integer> sm = new Stack<>();
//    public void push(int x) {
//        sv.push(x);
//        min = Math.min(min, x);
//        sm.push(min);
//    }
//
//    public void pop() {
//        if(!sv.isEmpty()){
//            sv.pop();
//            sm.pop();
//            if(!sm.isEmpty())
//                min = sm.peek();
//            else
//                min = Integer.MAX_VALUE;
//        }
//    }
//
//    public int top() {
//        return sv.peek();
//    }
//
//    public int getMin() {
//        return sm.peek();
//    }
	
	
	//method 2
	
	public class MinStack{
		int min = Integer.MAX_VALUE;
	
		Stack<Integer> stack;
		public MinStack(){
			stack = new Stack<>();
		}
		
		public void push(int x){
			if(x <= min){
				push(min);
				min = x;
			}
			push(x);
		}
		
		public void pop(){
			if(!stack.isEmpty()){
				if(stack.peek() == min){
					stack.pop();
					min = stack.peek();
					stack.pop();
				}
				else stack.pop();
			}
			if(stack.isEmpty())
				min = Integer.MAX_VALUE;
		}
		
		public int top(){
			return stack.peek();
		}
		
		public int getMin(){
			return min;
		}
	}
	
	
}
