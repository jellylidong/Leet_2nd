/*Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, 
 * there may exist one celebrity. 
 * The definition of a celebrity is that all the other n - 1 people 
 * know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that 
there is not one. The only thing you are allowed to do is to ask questions like: 
"Hi, A. Do you know B?" to get information of whether A knows B. 
You need to find out the celebrity 
(or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. 
Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.

Note: There will be exactly one celebrity if he/she is in the party. 
Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.*/
public class Problem277_Find_the_Celebrity {

	//method 1 is straightforward
	// it uses O(n^2) time complexity
	//not a good solution
	public int findCelebrity(int n) {
        int res = -1;
        for(int i = 0; i < n; i++){
            int know = 0;
            for(int j = 0; j < n; j++){
                if(i != j){
                    if(knows(j, i) && !knows(i, j))
                        know++;
                    else
                        break;
                }
            }
            if(know == n-1){
                res = i;
                break;
            }
        }
        return res;
    }
	
	/*my analysis*/
	public int findCelebrity2(int n){
		int candidate = 0;
		/*if candidates exists, we can always find it
		 * because when going from 0 to n-1,
		 * assume, candidate is x,
		 * so must knows(candidate, x) must be true
		 * and once we find the candidate, since it knows nobody
		 * so the candidate's value won't change*/
		for(int i = 0; i < n; i++){
			if(knows(candiate, i))
				candidate = i;
		}
		
		/*it is also possible that the candidate is not a real candidate
		 * for example 1 knows 2, 2 knows 3, .... n-2 knows n-1 and n-1 knows someone else
		 * so we need to check if the candidate knows someone or someone doesn't know it
		 * either condition is true will infer that it's not a real candidate*/
		for(int i = 0; i < n; i++){
			if(i != candidate){
				if(knows(candidate, i) || ! knows(i, candidate))
					return -1;
			}
		}
		
		return candidate;
	}
}
