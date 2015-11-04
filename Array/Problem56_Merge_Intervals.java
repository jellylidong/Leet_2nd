import java.util.*;

/*Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].*/

/*method 1
 * first sort the intervals by interval.start
 * (must sort first, or can not get correct result for case like
 * [[2,3],[4,5],[6,7],[8,9],[1,10]])
 * if i1 crossed i2
 * 	cur = merge(i1, i2)
 * else
 * 	put cur to the results lists
 */

/*method 2
 * first sort the intervals by interval.start
 * set start and end equals first interval's start and end
 * 
 * traversal from intervals.get(1) to the end
 *  if intervals.get(i).start < end
 *   it means crossing happens, so update the end
 *  if not
 *   it means no crossing happens, 
 *   so add new Interval(start, end) to the results list
 *   also set start and end to current interval's start and end
 *   */

/*!!!!
 * study how to use self-defined comparator*/

public class Problem56_Merge_Intervals {
	public List<Interval> mergeIntervals1(List<Interval> intervals){
		Collections.sort(intervals, Comparators.intComp);
		List<Interval> res = new ArrayList<>();
		if(intervals.size() == 0)
			return res;
		Interval cur = intervals.get(0);
		for(int i = 1; i < intervals.size(); i++){
			Interval tmp = intervals.get(i);
			if(crossed(cur, tmp)){
				cur = merge(cur, tmp);
			}
			else{
				res.add(cur);
				cur = tmp;
			}
		}
		res.add(cur);
		return res;
	}
	
	public boolean crossed(Interval i1, Interval i2){
        return ((i1.start >= i2.start && i1.start <= i2.end) ||
               (i1.end >= i2.start && i1.end <= i2.end) ||
               (i2.start >= i1.start && i2.start <= i1.end) ||
               (i2.end >= i1.start && i2.end <= i1.end));
    }
    
    public Interval merge(Interval i1, Interval i2){
        int start = Math.min(i1.start, i2.start);
        int end = Math.max(i1.end, i2.end);
        return new Interval(start, end);
    }
    
    public static class Comparators{
    	public static Comparator<Interval> intComp = new Comparator<Interval>(){
    		@Override
    		public int compare(Interval i1, Interval i2){
    			Integer start1 = new Integer(i1.start);
    			Integer start2 = new Integer(i2.start);
    			return start1.compareTo(start2);
    		}
    	};
    }
    
    public List<Interval> mergeIntervals2(List<Interval> intervals){
    	Collections.sort(intervals, Comparators.intComp);
    	if(intervals.size() <= 1)
    		return intervals;
    	List<Interval> res = new ArrayList<>();
    	int start = intervals.get(0).start;
    	int end = intervals.get(0).end;
    	
    	for(int i = 1; i < intervals.size(); i++){
    		Interval tmp = intervals.get(i);
    		if(tmp.start <= end){
    			end = Math.max(end, tmp.end);
    		}
    		else{
    			res.add(new Interval(start, end));
    			start = tmp.start;
    			end = tmp.end;
    		}
    	}
    	res.add(new Interval(start, end));
    	return res;
    }
}

/*don't forget to add the last interval for both method
 * if cross happens, we merge the interval but not add to the result list in the loop
 * if cross not happens, we update the start and end, but still not add it to the result list
 * so we should always add the last interval to result list*/
