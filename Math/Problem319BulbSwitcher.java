/**
 * Created by vcoder on 4/13/16.
 */


/*There are n bulbs that are initially off.
You first turn on all the bulbs.
Then, you turn off every second bulb.
On the third round, you toggle every third bulb
(turning on if it's off or turning off if it's on).
For the ith round, you toggle every i bulb. For the nth round,
you only toggle the last bulb. Find how many bulbs are on after n rounds.

Example:

Given n = 3.

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off].

So you should return 1, because there is only one bulb is on.*/

/*my analysis
* note that only the bulbs that are toggle odd times are finally on
* for any numbers, it has n paris of divisors
* that is to say 2*n divisors
* for example 18 = 1*18 = 2*9 = 3*6 = 6*3 = 9*2 = 18*1
* each devisor means this bulb is toggles one time
* but for squre number like 36
* 36 = 1*36 = 2*18 = 3*12 = 4*9 = 6×6
* note the 6*6, this mean the 36th bulb is only toggle one time for the 6th round
* so it's toggleed odd number times
* so only square numbers are finally on
* so we count from 1 to i
* until i*i <= n
* so i is he nuimber bulbs that are finally on
* so we can return sqrt(n) directly
* */

public class Problem319BulbSwitcher {
    public int bulbSwitch(int n) {
        return (int)Math.sqrt(n);
    }
}
