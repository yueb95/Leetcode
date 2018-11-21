// https://www.lintcode.com/problem/max-distance/description
public class Solution {
    
    class TrieNode {
        public TrieNode[] childs;
        public boolean isWord;
        public TrieNode() {
            childs = new TrieNode[2];
            isWord = false;
        }
    }
    
    public int getAns(String[] s) {
        TrieNode root = new TrieNode();
        
        int max = 0;
        for (int i = 0; i < s.length; i++) {
            if (i == 0) {
                update(s[i], 0, root);
                continue;
            }
            
            max = Math.max(max, getMax(s[i], 0, root, 0));
            System.out.println(max);
            update(s[i], 0, root);
        }
        
        return max;
    }
    
    private int getMax(String s, int start, TrieNode node, int max) {
        if (start >= s.length()) {
            return Math.max(getHeight(node), max);
        }
        
        if (node == null) {
            return Math.max(max, s.length() - start);
        }
        
        if (node.isWord) {
            max = Math.max(max, s.length() - start);
        }
        
        if (node.childs[1 - (int) (s.charAt(start) - '0')] != null) {
            max = Math.max(max, getHeight(node.childs[1 - (int) (s.charAt(start) - '0')]) + s.length() - start);
        }
        
        return getMax(s, start + 1, node.childs[s.charAt(start) - '0'], max);
    }
    
    private int getHeight(TrieNode node) {
        if (node == null) {
            return 0;
        }
        
        return Math.max(getHeight(node.childs[0]), getHeight(node.childs[1])) + 1;
    }
    
    private void update(String s, int start, TrieNode node) {
        
        while (start < s.length()) {
            if (node.childs[s.charAt(start) - '0'] == null) {
                node.childs[s.charAt(start) - '0'] = new TrieNode();
            }
            node = node.childs[s.charAt(start) - '0'];
            start++;
        }
        
        node.isWord = true;
        
        /*
         * stack overflow
        if (start >= s.length()) {
            node.isWord = true;
            return;
        }
        
        if (node.childs[s.charAt(start) - '0'] == null) {
            node.childs[s.charAt(start) - '0'] = new TrieNode();
        }
        
        update(s, start + 1, node.childs[s.charAt(start) - '0']);
        */
    }
}
