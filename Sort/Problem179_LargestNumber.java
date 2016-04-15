import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by vcoder on 2/29/16.
 */

/*Given a list of non negative integers,
arrange them such that they form the largest number.

For example, given [3, 30, 34, 5, 9],
the largest formed number is 9534330.

Note: The result may be very large,
so you need to return a string instead of an integer.*/

/*my analysis
* what make this problem difficult is that
* we need to build a new comparator the sort the array
* so that we make compose the largest number
*
* to build the new compartor, we need to consider the following case
* 1: 3 and 34
* 2: 3 and 32
* 3: 34 and 32
* 4: 345 and 35
*
* for case 1 and case, which one is "bigger" depends on the 2nd bit
* more detail seed code, especially the comparator*/

public class Problem179_LargestNumber {
    public String largestNumber(int[] num) {
        //the compartor can only compare objects
        //se we need to tranform the orignal int array to integer array
        Integer[] newNum = new Integer[num.length];
        for(int i = 0; i < num.length; i++)
            newNum[i] = new Integer(num[i]);

        //sort and define the new comparator
        Arrays.sort(newNum, new Comparator<Integer>(){
            @Override
            public int compare(Integer n1, Integer n2){
                String s1 = n1.toString();
                String s2 = n2.toString();
                return comStr(s1, s2);
            }

        });

        StringBuilder sb = new StringBuilder();


        for(int i = newNum.length-1; i >= 0; i--)
            sb.append(newNum[i]);

        //for case like[0, 0], the finally result should be 0
        //so we need to remove the starting 0s
        String res = sb.toString();
        while(res.length() > 1 && res.charAt(0) == '0')
            res = res.substring(1);


        return res;
    }

    public int comStr(String s1, String s2){
        int i = 0;

        while(i < s1.length() && i < s2.length()){
            if(s1.charAt(i) == s2.charAt(i)){
                i++;
                continue;
            }
            return s1.charAt(i) - s2.charAt(i);
        }
        //two nums are equal, either is ok
        if(i == s1.length() && i == s2.length())
            return 1;
        //if s1 end first,  it means the prious i-1 chars are equal in each string
        //so we need to compare s1 and s2's left substring, ie s2.substring(i)
        //vise versa to the last else case
        //e.g. [3, 34], [3, 35]
        else if(i == s1.length())
            return comStr(s1, s2.substring(i));
        else
            return comStr(s1.substring(i), s2);
    }
}
