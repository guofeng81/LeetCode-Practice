public class quickSelect {
    // find kth largest element in an array, LeetCode 215
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        if (k > n)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int pivot = nums[right];
            int pivotIndex = right;
            int leftIndex = left;
            int rightIndex = right - 1;

            while (leftIndex <= rightIndex) {
                if (nums[leftIndex] > pivot) {
                    leftIndex++;
                } else if (nums[rightIndex] < pivot) {
                    rightIndex--;
                } else {
                    swap(nums, leftIndex++, rightIndex--);
                }
            }

            swap(nums, leftIndex, pivotIndex);

            if (leftIndex == k - 1) {
                return nums[leftIndex];
            } else if (leftIndex > k - 1) {
                right = leftIndex - 1;
            } else {
                left = leftIndex + 1;
            }
        }

        return -1;
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }
}
