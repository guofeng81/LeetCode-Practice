import java.util.*;

public class LeetCode39 {
    // Combination Sum
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempResult = new ArrayList<>();
        helper(0, candidates, 0, target, tempResult, result);
        return result;
    }

    private void helper(int index, int[] nums, int tempSum, int target, List<Integer> tempResult,
            List<List<Integer>> result) {

        if (index == nums.length) {
            return;
        } // Combination Sum II don't need this statement.

        if (tempSum > target) {
            return;
        }

        if (tempSum == target) {
            result.add(new ArrayList<>(tempResult));
            return;
        }

        for (int i = index; i < nums.length; i++) {
            tempResult.add(nums[i]);
            helper(i, nums, tempSum + nums[i], target, tempResult, result);
            tempResult.remove(tempResult.size() - 1);
        }
    }
}
