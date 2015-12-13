/*Given a non-negative number represented as an array of digits, 
 * plus one to the number.

The digits are stored such that the most significant digit is 
at the head of the list.*/

/*method 1
 * straight forward, but not optimized*/

/*method 2
 * notice that what makes a difference is if this digits is 9
 * we go through this number from the lowest digit to highest digit,
 * if this digit is 9, we need to add 1 to it and continue to the next digit
 * if not, just add 1 and return the whole array, because there's no carry, 
 * so we don't need to care about other digits
 * if after going through all digits, there's still no return value,
 * it means the highest digit has a carry, so we need to set a new array and
 * set the res[0] = 1*/
public class Problem66_Plus_One {
	
	public int[] plusOne_1(int[] digits) {
        int l = digits.length;
        if(l == 0)
            return digits;
        int tmp = digits[l-1] + 1;
        int carry = tmp/10;
        digits[l-1] = tmp%10;
        for(int i = l-2; i >= 0; i--){
            tmp = digits[i] + carry;
            carry = tmp/10;
            digits[i] = tmp%10;
        }
        if(carry == 0)
            return digits;
        else{
            int[] res = new int[l+1];
            for(int i = l-1; i >= 0; i--)
                digits[i] = res[i+1];
            res[0] = carry;
        return res;
        }
    }
	
	public int[] plusOne_2(int[] digits) {
        int l = digits.length;
        for(int i = l-1; i >= 0; i--){
            if(digits[i] < 9){
                digits[i]++;
                return digits;
            }
            digits[i] = 0;
        }
        int[] res = new int[l+1];
        res[0] = 1;
        return res;
    }

}
