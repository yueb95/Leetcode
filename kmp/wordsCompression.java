// https://www.lintcode.com/problem/words-compression/description
public class Solution {
    
    public int[] wordsCompression(String[] s) {
        // KMP
        int n = s.length;
        int[] ans = new int[n];
        
        String str = s[0];
        for (int i = 1; i < n; i++) {
            int[] next = findNext(s[i]);
            int idx = kmp1(str, s[i], next);
            str = str += s[i].substring(idx, s[i].length());
            ans[i] = kmp2(str, s[i], next);
        }
        
        return ans;
    }
    
    private int[] findNext(String s) {
        // s: aabaaa --> next: 010122
        int n = s.length();
        int[] next = new int[n];
        int i = 0;
        int j = 1;
        
        while (j < n) {
            while (s.charAt(i) != s.charAt(j) && i != 0) {
                i = next[i - 1];
            }
            
            if (s.charAt(i) == s.charAt(j)) {
                next[j] = i + 1;
                i++;
                j++;
            } else {
                next[j] = 0;
                j++;
                i = 0;
            }
        }
        return next;
    }
    
    private int kmp1(String s, String p, int[] next) {
        // s: aabaabaaa, p: aabaaaa, next: 0101222 --> idx: 6
        //                        ^
        
        int i = s.length() > p.length() ? s.length() - p.length() : 0;
        int j = 0;
        
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1];
            }
        }
        
        return j;
    }
    
    private int kmp2(String s, String p, int[] next) {
        // s: aabaabaaa, p: aabaaa, next: 010122 --> idx: 3
        //       ^
        
        int i = 0;
        int j = 0;
        
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
            } else if (j == 0) {
                i++;
            } else {
                j = next[j - 1];
            }
        }
        
        return i - j;
    }
}
