import java.util.*;

public class LeetCode93 {
    // Restore IP Address

    List<String> res = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        StringBuilder sb = new StringBuilder();
        helper(s, sb, 0, 3);
        return res;
    }

    // remainingDot means the max number of dot left to append to the string to make
    // the valid ip address, starting of the remaining dot is 3
    private void helper(String s, StringBuilder sb, int index, int remainingDot) {
        if (remainingDot == 0) {
            if (valid(s.substring(index))) {
                sb.append("." + s.substring(index));
                res.add(sb.toString());
            }
            return;
        }

        for (int right = index; right < s.length(); right++) {
            if (valid(s.substring(index, right + 1))) {
                int length = sb.length();
                if (remainingDot == 3) {
                    // no more dots present in the string currently
                    sb.append(s.substring(index, right + 1));
                    helper(s, sb, right + 1, remainingDot - 1);
                    sb.setLength(length);
                } else {
                    sb.append("." + s.substring(index, right + 1));
                    helper(s, sb, right + 1, remainingDot - 1);
                    sb.setLength(length);
                }
            }
        }
    }

    private boolean valid(String s) {
        if (s.length() > 3)
            return false;
        if (s.length() == 0)
            return false;
        if (Integer.valueOf(s) > 255)
            return false;
        if (s.charAt(0) == '0' && s.length() > 1)
            return false;
        return true;
    }
}
