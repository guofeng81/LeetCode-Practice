import java.util.*;

public class LeetCode40 {
    // Combination Sum II
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        Arrays.sort(candidates);
        int n = candidates.length;
        boolean[] visited = new boolean[n];
        helper(0, candidates, 0, target, tempResult, result, visited);
        return result;
    }

    private void helper(int index, int[] nums, int tempSum, int target, List<Integer> tempResult,
            List<List<Integer>> result, boolean[] visited) {
        if (tempSum > target)
            return;

        if (tempSum == target) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) // why do we need this line in this question? this
                                                                    // line skip the duplicate elements
                continue;
            tempSum += nums[i];
            tempResult.add(nums[i]);
            visited[i] = true;
            helper(i + 1, nums, tempSum, target, tempResult, result, visited);
            visited[i] = false;
            tempSum -= nums[i];
            tempResult.remove(tempResult.size() - 1);

        }
    }
}
