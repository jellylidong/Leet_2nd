/*6. ZigZag Conversion My Submissions Question
Total Accepted: 69096 Total Submissions: 305838 Difficulty: Easy
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:

string convert(string text, int nRows);
convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
Show Tags
*/

/*my analysis
 * vertical down
 * then obliquely up*/
public class Problem6_ZigZagConversion {
	public String convert(String s, int numRows) {
		char[] ss = s.toCharArray();
		StringBuilder[] sbs = new StringBuilder[numRows];
		for(int i = 0; i < numRows; i++)
			sbs[i] = new StringBuilder();
		int i = 0;
		while(i < s.length()){
			//vertical down
			for(int j = 0; j < numRows && i < s.length(); j++, i++)
				sbs[j].append(ss[i]);
			//obliquely up
			for(int j = numRows-2; j >= 1 && i < s.length(); j--, i++)
				sbs[j].append(ss[i]);
		}
		for(int ii = 1; ii <numRows; ii++)
			sbs[0].append(sbs[i]);
		return sbs.toString();
	}
	
}
