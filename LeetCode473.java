import java.util.*;

public class LeetCode473 {
    // 473. Matchsticks to Square
    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        int n = matchsticks.length;
        for (int i = 0; i < n; i++) {
            sum += matchsticks[i];
        }

        Arrays.sort(matchsticks);
        if (sum % 4 != 0)
            return false;

        boolean[] visited = new boolean[n];
        return helper(0, matchsticks, 0, sum / 4, visited, 1); // groupId starts from 1
    }

    private boolean helper(int pos, int[] matchsticks, int tempSum, int target, boolean[] visited, int groupId) {
        if (groupId == 4)
            return true;
        if (tempSum == target)
            return helper(0, matchsticks, 0, target, visited, groupId + 1); // increase the group until it reaches 4，
                                                                            // why pos is 0??
        if (tempSum > target)
            return false;

        for (int i = pos; i < matchsticks.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && matchsticks[i] == matchsticks[i - 1] && !visited[i - 1])
                continue;
            // try the same number as the previous number, if previous num's path is found,
            // then found, if not, then we will never find it.
            visited[i] = true;
            if (helper(i + 1, matchsticks, tempSum + matchsticks[i], target, visited, groupId))
                return true;
            visited[i] = false;
        }

        return false;
    }
}
