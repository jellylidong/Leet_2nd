import java.util.ArrayList;
import java.util.List;

/**
 * Created by vcoder on 1/19/16.
 */

/*Given a string of numbers and operators,
return all possible results from computing all the different possible ways
to group numbers and operators. The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]*/

/*my analysis
* split the string with any operator and the evaluate the two sub-strings*/

public class Problem241_DifferentWays_to_AddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> list = new ArrayList<>();
        if(input.length() == 0)
            return list;
        for(int i = 0; i < input.length(); i++){
            char c = input.charAt(i);
            if(c == '+' || c =='-' || c == '*'){
                List<Integer> l1 = diffWaysToCompute(input.substring(0, i));
                List<Integer> l2 = diffWaysToCompute(input.substring(i+1));
                for(Integer n1: l1){
                    for(Integer n2: l2){
                        int res = 0;
                        switch(c){
                            case '+': res = n1+n2; break;
                            case '-': res = n1-n2; break;
                            case '*': res = n1*n2; break;
                        }
                        list.add(res);
                    }
                }
            }
        }
        //if after going through the whole string, the list is empty,
        //it means the whole string is a number
        if(list.size() == 0)
            list.add(Integer.parseInt(input));
        return list;
    }
}
