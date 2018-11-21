// https://leetcode.com/problems/subsets-ii/submissions/
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new LinkedList<>();
        dfs(nums, 0, ans, new LinkedList<>());
        return ans;
    }
    
    private void dfs(int[] nums, int start, List<List<Integer>> ans, List<Integer> cur) {
        ans.add(new LinkedList<>(cur));
        if (start >= nums.length) {
            return;
        }
        
        for (int i = start; i < nums.length; i++) {
            if (i != start && nums[i] == nums[i - 1]) {
                continue;
            }
            cur.add(nums[i]);
            dfs(nums, i + 1, ans, cur);
            cur.remove(cur.size() - 1);
        }
    }
}
