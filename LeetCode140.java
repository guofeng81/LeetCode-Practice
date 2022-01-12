import java.util.*;

public class LeetCode140 {
    // Word Break II
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        helper(0, s, sb, set, result);
        return result;
    }

    private void helper(int index, String s, StringBuilder sb, Set<String> wordDict, List<String> result) {
        if (index == s.length()) {
            result.add(sb.toString());
            return;
        }

        for (int hi = index; hi < s.length(); hi++) {
            String temp = s.substring(index, hi + 1);
            if (wordDict.contains(temp)) {
                int beforeAdding = sb.length();

                if (beforeAdding == 0) {
                    sb.append(temp);
                } else {
                    sb.append(" " + temp);
                }

                helper(hi + 1, s, sb, wordDict, result);
                sb.delete(beforeAdding, sb.length()); // dive into this sb.delete method to see
            }
        }
    }
}