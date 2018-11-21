// https://www.lintcode.com/problem/candy-ii/description
public class Solution {
    
    public int candyII(int[] ratings) {
        // 6 5 4 7 8 1 6 6 2
        // 1st round: 1 1 1 2 3 1 2 2 1 left -> right
        // 2nd round: 3 2 1 2 3 1 2 2 1 right -> left
        
        int n = ratings.length;
        if (n == 0) {
            return 0;
        }
        
        int[] num = new int[n];
        num[0] = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                num[i] = num[i - 1] + 1;
            } else if (ratings[i] == ratings[i - 1]) {
                num[i] = num[i - 1];
            } else {
                num[i] = 1;
            }
        }
        
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                num[i] = Math.max(num[i], num[i + 1] + 1);
            } else if (ratings[i] == ratings[i + 1]) {
                num[i] = num[i + 1];
            }
        }
        
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            //System.out.println(num[i]);
            cnt += num[i];
        }
        
        return cnt;
    }
}
