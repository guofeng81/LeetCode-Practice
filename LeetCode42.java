public class LeetCode42 {
    public int trap(int[] height) {

        int n = height.length;
        int[] left_max = new int[n];
        int[] right_max = new int[n];

        int leftHeight = height[0];

        for (int i = 1; i < n; i++) {
            if (leftHeight < height[i]) {
                leftHeight = height[i];
            }
            left_max[i] = leftHeight;
        }

        int rightHeight = height[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            if (rightHeight < height[i]) {
                rightHeight = height[i];
            }
            right_max[i] = rightHeight;
        }

        int sum = 0;

        for (int i = 0; i < n; i++) {
            int minHeight = Math.min(left_max[i], right_max[i]);
            if (minHeight > height[i]) {
                sum += minHeight - height[i];
            }
        }

        return sum;
    }

    // ********************************************************************************************************
    // */ Time: O(N) Space: O(1) (Best Solution)
    // Solution 2: used two pointers to solve this problem
    /*
     * public int trap(int[] height) {
     * int left = 0, right = height.length - 1;
     * int sum = 0;
     * 
     * int left_max = 0, right_max = 0;
     * 
     * while (left < right) {
     * if (height[left] < height[right]) {
     * if (height[left] >= left_max) {
     * left_max = height[left];
     * } else {
     * sum += left_max - height[left];
     * }
     * left++;
     * } else {
     * if (height[right] >= right_max) {
     * right_max = height[right];
     * } else {
     * sum += right_max - height[right];
     * }
     * right--;
     * }
     * }
     * 
     * return sum;
     * }
     */
    // ********************************************************************************************************
    // */
}