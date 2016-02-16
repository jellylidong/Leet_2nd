//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///*30. Substring with Concatenation of All Words My Submissions Question
//Total Accepted: 46364 Total Submissions: 229864 Difficulty: Hard
//You are given a string, s, and a list of words, words, that are all of the same length. Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once and without any intervening characters.
//
//For example, given:
//s: "barfoothefoobarman"
//words: ["foo", "bar"]
//
//You should return the indices: [0,9].
//(order does not matter).*/
//
///*my analysis
// *see the comment in code*/
//
//public class Problem30_Substring_with_Concatenation_of_All_Words {
//	public static List<Integer> findSubstring(String S, String[] L) {
//		List<Integer> list = new ArrayList<>();
//		if(S.length() == 0 || L.length == 0 || L[0].length() == 0)
//			return list;
//
//		//use a hashmap to store all strings and their count in L
//		HashMap<String, Integer> map = new HashMap<>();
//		for(String s: L){
//			map.put(s, map.getOrDefault(s, 0)+1);
//		}
//
//		int wordLen = L[0].length();
//		int StrLen = S.length();
//		/*i is the starting of the searching
//		 * i+j is the starting of current substring*/
//		for(int k = 0; k < StrLen; k++){
//			//make a copy of the hashmap
//			HashMap<String, Integer> wordMap = new HashMap<>(map);
//			for(int i = k, j = 0; i+j+wordLen < StrLen;){
//				String tmp = S.substring(i+j, i+j+wordLen);
//				/*if hash contains the string of L
//				 * 1.reduce the count of this string
//				 * 2.after reduce, if the count == 0, remove this string from hash
//				 * 3.after remove, if hash is empty, it means we find a starting position
//				 * that meet the requirement, list.add(i)*/
//				if(wordMap.containsKey(tmp)){
//					wordMap.put(tmp, wordMap.get(tmp)-1);
//					if(wordMap.get(tmp) == 0)
//						wordMap.remove(tmp);
//					if(wordMap.isEmpty())
//						list.add(i);
//					//go forward
//					j += wordLen;
//				}
//				/*if current substring is not in hashmap
//				 * two cases:
//				 * case 1:
//				 * j == 0, it means we have not found any string that contains in hashmap
//				 * so just update i so that we start searching from next position
//				 * case 2:
//				 * j != 0 means we have found strings that contains in hashmap
//				 * so we need to back up what we have done before
//				 * wo we need to update hashmap
//				 * we also need to update i to the next position
//				 * since i is changed, to make sure we continue the searching from current position
//				 * we need to let j -= wordLen*/
//				else{
//					if(j == 0)
//						i += wordLen;
//					else{
//						String tmp2 = S.substring(i, i+wordLen);
//						wordMap.put(tmp2, wordMap.getOrDefault(tmp2, 0)+1);
//						i += wordLen;
//						j -= wordLen;
//					}
//				}
//			}
//		}
//		return list;
//	}
//}
