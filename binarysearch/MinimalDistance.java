// https://www.lintcode.com/problem/minimal-distance-in-the-array/description
public class Solution {
    
    public int[] minimalDistance(int[] a, int[] b) {
        // binary search
        Arrays.sort(a);
        int n = b.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = binarysearch(a, b[i]);
        }
        return ans;
    }
    
    private int binarysearch(int[] a, int val) {
        // find the last element that is smaller or equal to val
        int start = 0;
        int end = a.length - 1;
        while (start + 1 < end) {
            int mid = (end - start) / 2 + start;
            if (a[mid] <= val) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (a[end] <= val) {
            int min = val - a[end];
            end++;
            while (end < a.length && a[end] - val < min) {
                min = a[end] - val;
                end++;
            }
            return a[end - 1];
        } else if (a[start] <= val) {
            int min = val - a[start];
            start++;
            while (start < a.length && a[start] - val < min) {
                min = a[start] - val;
                start++;
            }
            return a[start - 1];
        } else {
            return a[start];
        }
    }
}
