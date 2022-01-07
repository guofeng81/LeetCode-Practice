import java.util.List;

public class LeetCode315 {
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(i, nums[i]);
        }

        List<Integer> result = new ArrayList<>();

        int[] count = new int[n];
        mergeSort(items, 0, n - 1, count);

        for (int c : count) {
            result.add(c);
        }

        return result;
    }

    private void mergeSort(Item[] items, int lo, int hi, int[] count) {
        if (lo >= hi)
            return;

        int mid = lo + (hi - lo) / 2; // (lo + hi)/ 2

        mergeSort(items, lo, mid, count);
        mergeSort(items, mid + 1, hi, count);
        merge(items, lo, mid, mid + 1, hi, count);
    }

    private void merge(Item[] items, int lo, int loEnd, int hi, int hiEnd, int[] count) {
        int m = hiEnd - lo + 1;
        Item[] sorted = new Item[m];
        int index = 0;
        int rightCounter = 0;

        int loPtr = lo;
        int hiPtr = hi;
        while (loPtr <= loEnd && hiPtr <= hiEnd) {
            if (items[hiPtr].val < items[loPtr].val) {
                rightCounter++;
                sorted[index++] = items[hiPtr++];
            } else {
                count[items[loPtr].index] += rightCounter;
                sorted[index++] = items[loPtr++];
            }
        }

        while (loPtr <= loEnd) {
            count[items[loPtr].index] += rightCounter;
            sorted[index++] = items[loPtr++];
        }

        while (hiPtr <= hiEnd) {
            sorted[index++] = items[hiPtr++];
        }

        System.arraycopy(sorted, 0, items, lo, m);
    }

    class Item {
        int val;
        int index;

        public Item(int index, int val) {
            this.val = val;
            this.index = index;
        }
    }
}
