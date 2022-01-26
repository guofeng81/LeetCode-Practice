public class LeetCode416 {
    // Solution 2: Time O(N^2) Space: O(N^2)
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum += num;
        }

        if (sum % 2 != 0)
            return false;

        sum = sum / 2;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int i = 1; i <= n; i++) { // 第i个物品，其实就是nums[i-1]
            for (int j = 1; j <= sum; j++) { // dp[i][j] 表示的是到第i个物品时，拿或者不拿nums[i-1] 第i个物品，能不能凑成 target.
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][sum];
    }

    // 所以这个题目用的是dp的0-1背包来解决

    // Solution 1:
    // backtrakcing solution 1, same as 473. Matchsticks to Square, but exceed time
    // limited since the
    /*
     * Constraints:
     * 1 <= nums.length <= 200
     * 1 <= nums[i] <= 100 Time O(2^N)
     * /*public boolean canPartition(int[] nums) {
     * int sum = 0;
     * Arrays.sort(nums);
     * int n = nums.length;
     * for (int num : nums) {
     * sum += num;
     * }
     * 
     * if (sum % 2 != 0)
     * return false;
     * boolean[] visited = new boolean[n];
     * if (helper(nums, 0, 0, sum / 2, 1, visited))
     * return true;
     * 
     * return false;
     * }
     * 
     * private boolean helper(int[] nums, int index, int tempSum, int target, int
     * groupId, boolean[] visited) {
     * 
     * if (groupId == 2)
     * return true;
     * if (tempSum == target)
     * return helper(nums, index + 1, 0, target, groupId + 1, visited);
     * if (tempSum > target)
     * return false;
     * 
     * for (int i = index; i < nums.length; i++) {
     * if (visited[i])
     * continue;
     * if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
     * continue;
     * visited[i] = true;
     * if (helper(nums, i + 1, tempSum + nums[i], target, groupId, visited))
     * return true;
     * visited[i] = false;
     * }
     * 
     * return false;
     * }
     */
}