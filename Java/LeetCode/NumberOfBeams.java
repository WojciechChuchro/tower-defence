package Java.LeetCode;

/**
 * Given a 0-indexed binary string array bank representing the floor plan of a bank, <br>
 * where '0' denotes an empty cell and '1' denotes a security device, this program<br>
 * calculates the total number of independent laser beams between pairs of security devices<br>
 * based on specified conditions.<br><br>
 * <p>
 * Laser beams are established between security devices if:<br>
 * 1. They are on different rows (r1 and r2, where r1 < r2).<br>
 * 2. There are no security devices in the rows between r1 and r2.<br><br>
 * <p>
 * Example 1 <br>
 * Input: bank = ["011001","000000","010100","001000"]<br>
 * Output: 8 <br>
 * Explanation: Between each of the following device pairs, there is one beam. In total, there are 8 beams:<br>
 * * bank[0][1] -- bank[2][1]<br>
 * * bank[0][1] -- bank[2][3]<br>
 * * bank[0][2] -- bank[2][1]<br>
 * * bank[0][2] -- bank[2][3]<br>
 * * bank[0][5] -- bank[2][1]<br>
 * * bank[0][5] -- bank[2][3]<br>
 * * bank[2][1] -- bank[3][2]<br>
 * * bank[2][3] -- bank[3][2]<br>
 * Note that there is no beam between any device on the 0th row with any on the 3rd row.<br>
 * This is because the 2nd row contains security devices, which breaks the second condition.<br><br>
 * Example 2 <br>
 * Input: bank = ["000","111","000"]<br>
 * Output: 0<br>
 * Explanation: There does not exist two devices located on two different rows.<br>
 */

public class NumberOfBeams {
    public static int numberOfBeams(String[] bank) {
        int total = 0, prev = 0;

        for (String s : bank) {
            int level = 0;
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                if (c == '1') {
                    total += prev;
                    ++level;
                }
            }
            if (level > 0) {
                prev = level;
            }
        }
        return total;
    }
}
