import java.util.*;

public class LeetCode282 {
    List<String> res;
    char[] nums;
    int n;
    long target;
    char[] chs;

    public List<String> addOperators(String num, int target) {
        res = new ArrayList<>();
        nums = num.toCharArray();
        n = nums.length;
        this.target = target;
        chs = new char[n + n];
        long value = 0;
        int chsPtr = 0;
        for (int i = 0; i < n; i++) {
            value = value * 10 + nums[i] - '0';
            chs[chsPtr++] = nums[i];
            helper(i + 1, chsPtr, 0, value);

            if (value == 0)
                break;
        }

        return res;
    }

    private void helper(int numPtr, int chsPtr, long cur, long prev) {
        if (numPtr == n) {
            if (cur + prev == target) {
                res.add(new String(chs, 0, chsPtr));
            }
            return;
        }

        long value = 0;
        int op = chsPtr++;
        for (int i = numPtr; i < n; i++) {
            value = value * 10 + nums[i] - '0';
            chs[chsPtr++] = nums[i];

            chs[op] = '+';
            helper(i + 1, chsPtr, cur + prev, value);

            chs[op] = '-';
            helper(i + 1, chsPtr, cur + prev, -value);

            chs[op] = '*';
            helper(i + 1, chsPtr, cur, prev * value);

            if (value == 0)
                break;
        }
    }
}
