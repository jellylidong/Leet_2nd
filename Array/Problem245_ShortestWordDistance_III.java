/*This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “makes”, word2 = “coding”, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.*/
public class Problem245_ShortestWordDistance_III {
	/*my analysis
	 * if(word1 not equals word2)
	 * for(word in words)
	 * 	if(word == word1)	p1 = i
	 * 	if(word == word2)	p2 = i
	 * 	res = min(res, abs(p1-p1))
	 * if(word1 equals word2)
	 * for(word in words)
	 * 	if(word == word1)
	 * 		p2 = p1
	 * 		p1 = i
	 * 	res = min(res, abs(p1-p2))
	 * 
	 * 
	 * note: when word1 == word2
	 * we need to check if p2 == -1
	 * if p2 == -1, it means only on word equals word1 or word2 exists in the given array
	 * so we need to return 0	*/
	
	public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1;
        int p2 = -1;
        int min = Integer.MAX_VALUE;
        if(!word1.equals(word2)){
            for(int i = 0; i < words.length; i++){
                if(words[i].equals(word1))
                    p1 = i;
                if(words[i].equals(word2))
                    p2 = i;
                    
                if(p1 != -1 && p2 != -1)
                    min = Math.min(min, Math.abs(p1-p2));
            }
        }
        else{
            for(int i = 0; i < words.length; i++){
                if(words[i].equals(word1)){
                    p2 = p1;
                    p1 = i;
                }
                if(p1 != -1 && p2 != -1)
                    min = Math.min(min, Math.abs(p1 - p2));
            }
        }
        if(p2 == -1)
            return 0;
        return min;
    }
	
	/*anthoer solution
	 * shorter code but longer run time
	 * because we need to compare w1 and w2 every loop*/
	public int shortestWordDistance2(String[] words, String word1, String word2) {
		int p1 = -1;
		int p2 = -1;
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < words.length; i++){
			if(words[i].equals(word1))
				p1 = i;
			if(words[i].equals(word2)){
				if(word1.equals(word2))
					p1 = p2;
				p2 = i;
			}
			if(p1 != -1 && p2 != -1)
				min = Math.min(min, Math.abs(p1-p2));
		}
		if(p1 == -1)
			return 0;
		return min;
	}
}
