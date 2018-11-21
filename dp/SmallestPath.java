// https://www.lintcode.com/problem/smallest-path/description
public class Solution {
    
    public int smallestPath(int[][] matrix) {
        // dp
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] f = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    f[i][j] = matrix[i][j];
                    continue;
                }
                f[i][j] = f[i - 1][j] + matrix[i][j];
                if (j > 0) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j - 1] + matrix[i][j]);
                }
                if (j < n - 1) {
                    f[i][j] = Math.min(f[i][j], f[i - 1][j + 1] + matrix[i][j]);
                }
            }
        }
        
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, f[m - 1][i]);
        }
        return ans;
    }
}
