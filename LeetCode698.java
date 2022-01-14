import java.util.*;

public class LeetCode698 {
    // LeetCode 698 Partition to K Equal Sum Subsets (Same as LeetCode 473 and
    // related to LeetCode 416 Partition Equal Subset Sum)
    // Understand why using bucket(tempSum)
    // here，当我在做这个题目的时候没有用tempSum这个变量，导致了很多错误，后来明白为什么要用tempSum这个变量了
    // 因为在 if (target == bucket) return dfs(nums, 0, k - 1, target, visited, 0);
    // （line 28) 这一行，之前用的是target - nums[i] && target == 0,
    // 这样传进去的target就会变成0，而非原来的target，这样就再也拿不到之前的target值了。所以tempSum在这个题目中必不可少。返回dfs()之后用于更新为0。
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        Arrays.sort(nums); // can sort or not, sort will accelerate the speed
        if (sum % k != 0)
            return false;
        boolean[] visited = new boolean[n];
        return dfs(nums, 0, k - 1, sum / k, visited, 0);
    }

    private boolean dfs(int[] nums, int bucket, int k, int target, boolean[] visited, int index) {
        if (k == 0)
            return true;
        if (target == bucket)
            return dfs(nums, 0, k - 1, target, visited, 0);
        if (bucket > target)
            return false;

        for (int i = index; i < nums.length; i++) {
            if (visited[i])
                continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])
                continue;
            // you can add this line with sorted above or you don't need this line to 减支
            visited[i] = true;
            if (dfs(nums, bucket + nums[i], k, target, visited, i + 1))
                return true;
            visited[i] = false;
        }
        return false;
    }
}
