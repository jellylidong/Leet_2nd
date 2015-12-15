/*Given a digit string, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.



Input:Digit string "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:
Although the above answer is in lexicographical order, your answer could be in any order you want.

Show Company Tags
Show Tags
Show Similar Problems
*/

/*method_1 straightforward*/

/*method_2 use FIFO
 * for each number, 
 * when the head.length() == i
 * remove the head of the queue and store it into h
 * update h and store the updated result into the tail of the queue*/

import java.util.*;
public class Problem17_LetterCombinations_of_a_Phone_Number {
	public List<String> letterCombinations_1(String digits) {
        String[] ss = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> list = new ArrayList<>();
        if(digits.length() == 0)
            return list;
        list.add("");//for first iteration
        for(int i = 0; i < digits.length(); i++){
            int num = digits.charAt(i)-'0';
            String s = ss[num];
            
            List<String> newList = new ArrayList<>();
            
            for(int j=0; j < s.length(); j++){
                List<String> tmp = new ArrayList<>(list);
                for(int k = 0; k <tmp.size(); k++)
                    tmp.set(k, tmp.get(k) + s.charAt(j));
                newList.addAll(tmp);
            }
            list = new ArrayList<>(newList);
            
        }
        
        return list;
    }
	
	public List<String> letterCombinations_2(String digits) {
        String[] ss = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        LinkedList<String> list = new LinkedList<>();
        if(digits.length() == 0)
            return list;
        list.add("");//for first iteration
        for(int i = 0; i < digits.length(); i++){
            int num = digits.charAt(i)-'0';
            String s = ss[num];
            while(list.peek().length() == i){
                String head = list.remove();
                for(int j = 0; j < s.length(); j++){
                    list.add(head + s.charAt(j));
                }
            }
            
        }
        
        return list;
    }
}
