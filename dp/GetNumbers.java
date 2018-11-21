// https://www.lintcode.com/problem/find-the-numbers/description

public class Solution {
    
    public int[] getNumbers(int n) {
        if (n < 0) {
            return new int[0];
        }
        
        if (n == 0) {
            return new int[]{1};
        }
        
        // 0, 1
        int[] f = new int[3];
        f[0] = 0;
        f[1] = 1;
        int min = 0;
        int max = Integer.MAX_VALUE;
        for (int i = 0; i <= n + 1; i++) {
            if (i >= 2) {
                f[i % 3] = f[(i - 1) % 3] + f[(i - 2) % 3];
            }
            if (f[i % 3] < n) {
                min = Math.max(min, f[i % 3]);
            }
            if (f[i % 3] > n) {
                if (max < f[i % 3]) {
                    break;
                }
                max = Math.min(max, f[i % 3]);
            }
        }
        
        return new int[]{min, max};
    }
}
