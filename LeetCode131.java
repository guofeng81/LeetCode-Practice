import java.util.*;

public class LeetCode131 {
    // Palindrome Partitioning
    public List<List<String>> partition(String s) {
        List<String> curList = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        helper(0, s, curList, result);
        return result;
    }

    private void helper(int lo, String s, List<String> curList, List<List<String>> result) {
        if (lo == s.length()) {
            result.add(new ArrayList<>(curList));
            return;
        }

        for (int hi = lo; hi < s.length(); hi++) {
            if (isPalindrome(s, lo, hi)) {
                curList.add(s.substring(lo, hi + 1));
                helper(hi + 1, s, curList, result); // why is hi + 1, not index + 1
                curList.remove(curList.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
