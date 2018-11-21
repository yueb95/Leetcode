// https://www.lintcode.com/problem/shortest-subarray-ii/description
public class Solution {
    
    private int[] prefixSum;
    
    public int smallestLengthII(int[] nums, int k) {
        int n = nums.length;
        prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = nums[i] + prefixSum[i];
        }
        
        int start = 1;
        int end = n;
        // binary search
        // why start + 1 < end can not pass oj
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (exist(nums, k, mid)) {
                end = mid;
            } else {
                start = mid + 1; // start = mid;
            }
        }
        
        if (exist(nums, k, start)) {
            return start;
        }
        if (exist(nums, k, end)) {
            return end;
        }
        return -1;
    }
    
    private boolean exist(int[] nums, int k, int len) {
        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        
        int idx = len;
        while (sum < k && idx < nums.length) {
            sum += nums[idx];
            sum -= nums[idx - len];
            idx++;
        }
        
        return sum >= k;
    }
}
