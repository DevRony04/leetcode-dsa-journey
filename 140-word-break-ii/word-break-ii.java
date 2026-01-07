class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> dict = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(0, s, dict, memo);
    }

    private List<String> dfs(int index, String s, Set<String> dict,
                             Map<Integer, List<String>> memo) {

        if (memo.containsKey(index))
            return memo.get(index);

        List<String> result = new ArrayList<>();

        // Base case: reached end of string
        if (index == s.length()) {
            result.add("");
            return result;
        }

        for (int end = index + 1; end <= s.length(); end++) {
            String word = s.substring(index, end);

            if (dict.contains(word)) {
                List<String> subSentences = dfs(end, s, dict, memo);

                for (String sub : subSentences) {
                    if (sub.isEmpty())
                        result.add(word);
                    else
                        result.add(word + " " + sub);
                }
            }
        }

        memo.put(index, result);
        return result;
    }
}